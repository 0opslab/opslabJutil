/*
 *  ${file_name} of project cpdetector, <purpose>
 *  Copyright ${year} (C) Achim Westermann, created on 03.06.2004.
 *  
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this collection are subject to the Mozilla Public License Version 
 * 1.1 (the "License"); you may not use this file except in compliance with 
 * the License. You may obtain a copy of the License at 
 * http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * The Original Code is the cpDetector code in [sub] packages info.monitorenter and 
 * cpdetector. 
 * 
 * The Initial Developer of the Original Code is
 * Achim Westermann <achim.westermann@gmx.de>.
 * 
 * Portions created by the Initial Developer are Copyright (c) 2007 
 * the Initial Developer. All Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 * 
 * ***** END LICENSE BLOCK ***** * 
 */
package info.monitorenter.cpdetector.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>
 * A proxy that delegate the codepage detection to all it's delegates. The first
 * one (added in code-order) that does not return a null {@link Charset}from
 * it's delegate method {@link #detectCodepage(URL)}wins the race and determines
 * the codpage of the document specified by the given URL.
 * </p>
 * <p>
 * If an underlying {@link ICodepageDetector}throws an
 * {@link IOException}, the delegation search will be terminated by
 * throwing this exception.
 * </p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * 
 */
public final class CodepageDetectorProxy extends AbstractCodepageDetector {

  /** serialVersionUID */
  private static final long serialVersionUID = -7389424614984024701L;

  /**
   * Singleton instance.
   */
  private static CodepageDetectorProxy instance = null;

  /**
   * The set of {@link ICodepageDetector}instances that this proxy will delegate
   * to. These instances will be invoked in order to find the codepage until the
   * first instance returns a valid codepage. If an {@link IOException}is thrown
   * the search will terminate early (assuming that the execption is related to
   * a general problem with the given URL.
   */
  private Set<ICodepageDetector> detectors = new LinkedHashSet<ICodepageDetector>();

  /**
   * Singleton constructor. For internal use only.
   */
  private CodepageDetectorProxy() {
    super();
  }

  /**
   * Singleton retrieval method.
   * <p>
   * Be sure to configure the instance returned at a single location in your
   * code to avoid unpredictable application - wide side effects.
   * <p>
   * 
   * @return the sole instance in this VM.
   */
  public static CodepageDetectorProxy getInstance() {
    if (CodepageDetectorProxy.instance == null) {
      CodepageDetectorProxy.instance = new CodepageDetectorProxy();
    }
    return CodepageDetectorProxy.instance;
  }

  /**
   * Adds the given instance to this proxie's detection capability.
   * <p>
   * 
   * Remember that the order of added ICodepageDetector instances is important
   * for the internal delegation (see class description).
   * <p>
   * 
   */
  public boolean add(ICodepageDetector detector) {
    return this.detectors.add(detector);
  }

  /**
   * @param url
   *          Should link to a file containing textual document. No check for
   *          images or other resources is made.
   * @throws IOException
   *           If a problem with the url - handling occurs.
   */
  public Charset detectCodepage(final URL url) throws IOException {
    Charset ret = null;
    Iterator<ICodepageDetector> detectorIt = this.detectors.iterator();
    while (detectorIt.hasNext()) {
      ret = detectorIt.next().detectCodepage(url);
      if (ret != null) {
        if (ret != UnknownCharset.getInstance()) {
          if (ret instanceof UnsupportedCharset) {
            // TODO: Debug logging: found illegal charset tag or encoding
            // declaration.
          } else {
            break;
          }
        }
      }
    }
    return ret;
  }

  /**
   * <p>
   * Detects the codepage by iteratively delegating the call to all internal
   * {@link ICodepageDetector} instances added by
   * {@link #add(ICodepageDetector)}.
   * </p>
   * <p>
   * The given InputStream has to support mark such that the call
   * {@link InputStream#mark(int)} with argument length does not throw an
   * exception. This is needed, as the stream has to be resetted to the
   * beginning for each internal delegate that tries to detect.
   * </p>
   * <p>
   * If this is impossible (large documents), prefer using
   * {@link #detectCodepage(URL)}.
   * </p>
   * 
   * @param in
   *          An InputStream for the document, that supports mark and a
   *          readlimit of argument length.
   * 
   * @param length
   *          The amount of bytes to take into account. This number shouls not
   *          be longer than the amount of bytes retrievable from the
   *          InputStream but should be as long as possible to give the fallback
   *          detection (chardet) more hints to guess.
   * 
   * @see cpdetector.io.ICodepageDetector#detectCodepage(InputStream,
   *      int length)
   * 
   * @throws IllegalArgumentException
   *           if more bytes had to be read from the input stream than param
   *           length or the given input stream does not support marking.
   */
  public Charset detectCodepage(final InputStream in, final int length) throws IOException, IllegalArgumentException {

    if (!in.markSupported()) {
      throw new IllegalArgumentException("The given input stream (" + in.getClass().getName() + ") has to support for marking.");
    }
    Charset ret = null;
    int markLimit = length;
    Iterator<ICodepageDetector> detectorIt = this.detectors.iterator();
    while (detectorIt.hasNext()) {
      in.mark(markLimit);
      ret = detectorIt.next().detectCodepage(in, length);
      // if more bytes have been read than marked (length) this will throw an
      // exception:
      try {
        in.reset();
      } catch (IOException ioex) {
        IllegalStateException ise = new IllegalStateException(
            "More than the given length had to be read and the given stream could not be reset. Undetermined state for this detection.");
        ise.initCause(ioex);
        throw ise;

      }
      if (ret != null) {
        if (ret != UnknownCharset.getInstance()) {
          if (ret instanceof UnsupportedCharset) {
            // TODO: Debug logging: found illegal charset tag or encoding
            // declaration.
          } else {
            break;
          }
        }
      }
    }
    return ret;
  }

  /**
   * @see Object#toString()
   */
  public String toString() {
    StringBuffer ret = new StringBuffer();
    Iterator<ICodepageDetector> it = this.detectors.iterator();
    int i = 1;
    while (it.hasNext()) {
      ret.append(i);
      ret.append(") ");
      ret.append(it.next().getClass().getName());
      ret.append("\n");
      i++;
    }
    return ret.toString();
  }

}

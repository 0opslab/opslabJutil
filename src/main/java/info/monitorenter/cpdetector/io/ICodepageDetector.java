/*
 * 
 *  ICodepageDetector.java  cpdetector
 *  Copyright (C) Achim Westermann, created on 19.07.2004, 20:13:44  
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
 *  
 * If you modify or optimize the code in a useful way please let me know.
 * Achim.Westermann@gmx.de
 */
package info.monitorenter.cpdetector.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * 
 */
public interface ICodepageDetector extends Serializable, Comparable {
  /**
   * <p>
   * High level method to open documents in the correct codepage.
   * Implementations of this method should delegate to the low-level method
   * {@link #detectCodepage(URL)}.
   * </p>
   * <p>
   * Detect the codepage of the document pointed to by the URL argument. If the
   * codepage could not be detected, null has to be returned. If the given URL
   * does not point to a document or it is not possible to open the document
   * specified by the given URL, an IOException is thrown.
   * </p>
   * 
   * @exception IOException
   *              thrown to indicate that it is was not possible to open the
   *              document specified by the given URL.
   * @return null, if the codepage of the document specified by the given URL
   *         was not detected or a Reader that reads the document in the
   *         detected codepage.
   * 
   */
  public Reader open(URL url) throws IOException;

  /**
   * <p>
   * Low-level method that detects the codepage (charset) of the document
   * specified by the given URL.
   * </p>
   * 
   * @exception IOException
   *              thrown to indicate that it is was not possible to open the
   *              document specified by the given URL.
   *              
   * @return null, if the codepage of the document specified by the given URL
   *         was not detected or the {@link java.io.Charset}that represents the
   *         document's codepage.
   * 
   */
  public Charset detectCodepage(URL url) throws IOException;

  /**
   * <p>
   * This method allows to detect the charset encoding from every source (even a
   * String, which an URL does not decorate!).
   * </p>
   * <p>
   * Note that you cannot reuse the given InputStream unless it supports marking ({@link InputStream#markSupported()} ==
   * true), you mark the initial position with a sufficient readlimit and invoke
   * reset afterwards (without getting any exception).
   * </p>
   * 
   * @param in
   *          An InputStream for the document, that supports mark and a
   *          readlimit of argument length.
   * 
   * @param length
   *          The amount of bytes to take into account. This number should not
   *          be longer than the amount of bytes retrievable from the
   *          InputStream but should be as long as possible to give the fallback
   *          detection (chardet) more hints to guess. 
   */
  public Charset detectCodepage(InputStream in, int length) throws IOException;
}

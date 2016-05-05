/*
 *  ASCIIDetector.java, a simple implementation for cpdetector,
 *  that may be used to detect plain ASCII.
 *  Copyright (C) Achim Westermann, created on 18.10.2004, 12:56:45
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
 * ***** END LICENSE BLOCK ***** 
 *  
 *  If you modify or optimize the code in a useful way please let me know.
 *  Achim.Westermann@gmx.de
 *
 */
package info.monitorenter.cpdetector.io;

import info.monitorenter.util.FileUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


/**
 * <p>
 * A simple detector that may be used to detect plain ASCII. This instance
 * should never be used as the first strategy of the
 * {@link CodepageDetectorProxy}: Many different encodings are
 * multi-byte and may be verified to be ASCII by this instance, because all
 * their bytes are in the range from 0x00 to 0x7F.
 * </p>
 * <p>
 * It is recommended to use this as a fall-back, if all different strategies
 * (e.g. {@link JChardetFacade},
 * {@link ParsingDetector}) fail. This is most often the case for
 * ASCII data, as guessing and exclusion based on the content is especially hard
 * for ASCII: almost all character sets define the ASCII range (compatibility).
 * Therefore this is a good fall-back.
 * </p>
 * <p>
 * It is a singleton for performance-reasons: The constructor is private. Use
 * {@link #getInstance()}or <code>
 * {@link info.monitorenter.cpdetector.reflect.SingletonLoader#getInstance()}
 * and
 * {@link info.monitorenter.cpdetector.reflect.SingletonLoader#newInstance(String)}
 * on the result.
 * </code>
 * </p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * 
 */
public final class ASCIIDetector
    extends AbstractCodepageDetector {
  /**
   * Generated <code>serialVersionUID</code>.
   */
  private static final long serialVersionUID = 3760841259903824181L;

  private static ICodepageDetector instance;

  /**
   * Singleton constructor
   */
  private ASCIIDetector() {
    super();
    // TODO Auto-generated constructor stub
  }

  public static ICodepageDetector getInstance() {
    if (instance == null) {
      instance = new ASCIIDetector();
    }
    return instance;
  }

  /**
   * @see ICodepageDetector#detectCodepage(InputStream,
   *      int)
   */
  public Charset detectCodepage(final InputStream in, final int length) throws IOException {
    Charset ret = UnknownCharset.getInstance();
    InputStream localin;
    if (!(in instanceof BufferedInputStream)) {
      localin = new BufferedInputStream(in, 4096);
    } else {
      localin = in;
    }
    if (FileUtil.isAllASCII(localin)) {
      ret = Charset.forName("US-ASCII");
    }
    return ret;

  }

}

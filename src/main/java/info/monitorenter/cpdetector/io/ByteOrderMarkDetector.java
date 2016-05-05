/*
 * ByteOrderMarkDetector.java, <enter purpose here>. Copyright (C) 2005 Achim Westermann, Achim.Westermann@gmx.de
 * 
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this collection are subject to the Mozilla Public License Version 1.1 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations under the License.
 * 
 * The Original Code is the cpDetector code in [sub] packages info.monitorenter and cpdetector.
 * 
 * The Initial Developer of the Original Code is Achim Westermann <achim.westermann@gmx.de>.
 * 
 * Portions created by the Initial Developer are Copyright (c) 2007 the Initial Developer. All Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Alternatively, the contents of this file may be used under the terms of either the GNU General Public License Version 2 or later (the
 * "GPL"), or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which case the provisions of the GPL or the LGPL
 * are applicable instead of those above. If you wish to allow use of your version of this file only under the terms of either the GPL or
 * the LGPL, and not to allow others to use your version of this file under the terms of the MPL, indicate your decision by deleting the
 * provisions above and replace them with the notice and other provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the terms of any one of the MPL, the GPL or the LGPL.
 * 
 * ***** END LICENSE BLOCK ***** *
 * 
 * If you modify or optimize the code in a useful way please let me know. Achim.Westermann@gmx.de
 */
package info.monitorenter.cpdetector.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/**
 * <p>
 * This detector identifies byte order marks of the following codepages to give a 100 %
 * deterministic result in case of detection.
 * </p>
 * <p>
 * <table border="1">
 * <tr>
 * <td>00 00 FE FF</td>
 * <td>UCS-4, big-endian machine (1234 order)</td>
 * </tr>
 * <tr>
 * <td>FF FE 00 00</td>
 * <td>UCS-4,little-endian machine (4321 order)</td>
 * </tr>
 * <tr>
 * <td>00 00 FF FE</td>
 * <td>UCS-4, unusual octet order (2143)</td>
 * </tr>
 * <tr>
 * <td>FE FF 00 00</td>
 * <td>UCS-4, unusual octet order (3412)</td>
 * </tr>
 * <tr>
 * <td>FE FF ## ##</td>
 * <td>UTF-16, big-endian</td>
 * </tr>
 * <tr>
 * <td>FF FE ## ##</td>
 * <td>UTF-16, little-endian</td>
 * </tr>
 * <tr>
 * <td>EF BB BF</td>
 * <td>UTF-8</td>
 * </tr>
 * </table>
 * </p>
 * <p>
 * Note that this detector is very fast as it only has to read a maximum of 8 bytes to provide a
 * result. Nevertheless it is senseless to add it to the configuration if the documents to detect
 * will have a low rate of documents in the codepages that will be detected. If added to the
 * configuration of {@link CodepageDetectorProxy} it should be at
 * front position to save computations of the following detection processses.
 * <p>
 * <p>
 * This implementation is based on: <br>
 * <a target="_blank" title="http://www.w3.org/TR/2004/REC-xml-20040204/#sec-guessing-no-ext-info"
 * href="http://www.w3.org/TR/2004/REC-xml-20040204/#sec-guessing-no-ext-info">W3C XML Specification
 * 1.0 3rd Edition, F.1 Detection Without External Encoding Information </a>.
 * </p>
 * This implementation does the same as <code>{@link UnicodeDetector}</code> but with a different
 * read strategy (read each byte separately) and a switch case tree (-> bytecode tableswitch). Would
 * be great to have a performance comparison. Maybe the read of 4 bytes in a row combined with the
 * switch could make this implementation the winner.
 * <p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * @version $Revision: 1.3 $
 */
public class ByteOrderMarkDetector
    extends AbstractCodepageDetector implements ICodepageDetector {

  /**
   * Generated <code>serialVersionUID</code>.
   */
  private static final long serialVersionUID = 3618977875919778866L;

  /**
   * @see ICodepageDetector#detectCodepage(InputStream, int)
   */
  public Charset detectCodepage(final InputStream in, final int length) throws IOException {
    // dumbest pragmatic code ever written (nearly a code generator would have
    // been faster). But it's proven fast.
    Charset result = UnknownCharset.getInstance();
    int readByte = 0;
    readByte = in.read();
    switch (readByte) {
      case (0x00): {
        // 0x 00
        readByte = in.read();
        switch (readByte) {
          case (0x00): {
            // 0x 00 00
            readByte = in.read();
            switch (readByte) {
              case (0xFE): {
                // 0x 00 00 FE
                // UCS-4, big-endian machine (1234 order)
                try {
                  result = Charset.forName("UCS-4BE");
                } catch (UnsupportedCharsetException uce) {
                  result = UnsupportedCharset.forName("UCS-4BE");
                }
                return result;

              }
              case (0xFF): {
                // 0x 00 00 FF
                // UCS-4, unusual octet order (2143)
                try {
                  result = Charset.forName("UCS-4");
                } catch (UnsupportedCharsetException uce) {
                  result = UnsupportedCharset.forName("UCS-4");
                }
                return result;

              }
              default:
                return result;
            }

          }
          default:
            return result;
        }
      }
      case (0xFE): {
        // 0x FE
        readByte = in.read();
        switch (readByte) {
          case (0xFF): {
            // 0x FE FF
            // from here on default to UTF-16, big-endian
            readByte = in.read();
            switch (readByte) {
              case (0x00): {
                // 0x FE FF 00
                readByte = in.read();
                switch (readByte) {
                  case (0x00): {
                    // 0x FE FF 00 00
                    // UCS-4, unusual octet order (3412)
                    try {
                      result = Charset.forName("UCS-4");
                    } catch (UnsupportedCharsetException uce) {
                      result = UnsupportedCharset.forName("UCS-4");
                    }
                    return result;
                  }
                  default: {
                    try {
                      result = Charset.forName("UTF-16BE");
                    } catch (UnsupportedCharsetException uce) {
                      result = UnsupportedCharset.forName("UTF-16BE");
                    }
                    return result;
                  }
                }

              }
              default: {
                try {
                  result = Charset.forName("UTF-16BE");
                } catch (UnsupportedCharsetException uce) {
                  result = UnsupportedCharset.forName("UTF-16BE");
                }
                return result;
              }

            }

          }
          default: {
            return result;
          }
        }
      }

      case (0xFF): {
        // 0x FF
        readByte = in.read();
        switch (readByte) {
          case (0xFE): {
            // 0x FF FE
            // from here on default to UTF-16, little-endian
            readByte = in.read();
            switch (readByte) {
              case (0x00): {
                // 0x FF FE 00
                readByte = in.read();
                switch (readByte) {
                  case (0x00): {
                    // 0x FF FE 00 00
                    // UCS-4, little-endian machine (4321 order)
                    try {
                      result = Charset.forName("UCS-4LE");
                    } catch (UnsupportedCharsetException uce) {
                      result = UnsupportedCharset.forName("UCS-4LE");
                    }
                    return result;

                  }
                  default: {
                    try {
                      result = Charset.forName("UTF-16LE");
                    } catch (UnsupportedCharsetException uce) {
                      result = UnsupportedCharset.forName("UTF-16LE");
                    }
                    return result;
                  }
                }
              }
              default: {
                try {
                  result = Charset.forName("UTF-16LE");
                } catch (UnsupportedCharsetException uce) {
                  result = UnsupportedCharset.forName("UTF-16LE");
                }
                return result;
              }
            }
          }
          default: {
            return result;
          }
        }
      }
      case (0xEF): {
        // 0x EF
        readByte = in.read();
        switch (readByte) {
          case (0xBB): {
            // 0x EF BB
            readByte = in.read();
            switch (readByte) {
              case (0xBF): {
                try {
                  result = Charset.forName("utf-8");
                } catch (UnsupportedCharsetException uce) {
                  result = UnsupportedCharset.forName("utf-8");
                }
                return result;

              }
              default: {
                return result;
              }
            }

          }
          default: {
            return result;
          }
        }

      }
      default:
        return result;

    }
  }

  /**
   * <p>
   * Delegates to {@link #detectCodepage(InputStream, int)}with a buffered input stream of size 10
   * (8 needed as maximum).
   * </p>
   * 
   * @see ICodepageDetector#detectCodepage(URL)
   */
  public Charset detectCodepage(URL url) throws IOException {
    Charset result;
    BufferedInputStream in = new BufferedInputStream(url.openStream());
    result = this.detectCodepage(in, Integer.MAX_VALUE);
    in.close();
    return result;
  }
}

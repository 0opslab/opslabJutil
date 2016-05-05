/*
 *  Encoding.java, a facade to an ANTLR grammar based
 *  parser / lexer that searches for the "charset" attribute of a
 *  html page.
 *  Copyright (C) 2004 Achim Westermann, created on 20.07.2004, 10:35:46
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

import info.monitorenter.cpdetector.io.parser.EncodingLexer;
import info.monitorenter.cpdetector.io.parser.EncodingParser;
import info.monitorenter.io.LimitedInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import antlr.ANTLRException;

/**
 * <p>
 * A <a target="_blank"
 * href="http://wiki.cs.uiuc.edu/PatternStories/FacadePattern">Fa�ade</a> that
 * internally uses an <a href="http://www.antlr.org">ANTLR </a>- based parser /
 * lexer.<br>
 * </p>
 * <p>
 * The underlying lexer is more a filter: It does not verify lexical correctness
 * by the means of matching a defined order of tokens, but just filters m_out
 * certain tokens. By now the following tokens are filtered: <table border="0"
 * id="userstyle"> <colgroup> <col width="20%"/> <col width="30%"/> <col
 * width="8%"/> <col width="42%"/> </colgroup>
 * <tr>
 * <th>Token Name</th>
 * <th>Match</th>
 * <th>Lang.</th>
 * <th>Specification</th>
 * </tr>
 * <tr>
 * <td><em>META_CONTENT_TYPE</em></td>
 * <td>
 * <tt>"meta" "http-equiv" "=" '"Content-Type"' "content" "=" '"' IDENTIFIER "charset" "=" <b>&lt;EncName&gt;</b> '"'></tt>
 * </td>
 * <td>HTML</td>
 * <td> <a
 * href="http://www.w3.org/TR/1999/REC-html401-19991224/charset.html#h-5.2.2"
 * target="_blank"> W3C HTML 4.01 Specification </a> Chapter 5.2.2 </td>
 * </tr>
 * <tr>
 * <td> <em>XML_ENCODING_DECL</em> </td>
 * <td> <tt>"&lt;?xml" VersionInfo  "encoding" "=" <b>&lt;EncName&gt;</b></tt>
 * </td>
 * <td> XML </td>
 * <td> <a target="_blank"
 * href="http://www.w3.org/TR/2004/REC-xml-20040204/#sec-prolog-dtd"> Extensible
 * Markup Language (XML) 1.0 (Third Edition)</a> Chapter 2.8 </td>
 * </tr>
 * </table>
 * </p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * 
 */
public class ParsingDetector
    extends AbstractCodepageDetector {
  /**
   * Generated <code>serialVersionUID</code>.
   */
  private static final long serialVersionUID = 3618977875919778866L;

  private boolean m_verbose = false;

  public ParsingDetector() {
    this(false);
  }

  public ParsingDetector(boolean verbose) {
    super();
    this.m_verbose = verbose;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cpdetector.io.ICodepageDetector#detectCodepage(java.io.InputStream)
   */
  public Charset detectCodepage(final InputStream in, final int length) throws IOException {
    EncodingLexer lexer;
    EncodingParser parser;
    Charset charset = null;
    String csName = null;
    InputStream limitedInputStream = new LimitedInputStream(in, length);
    if (this.m_verbose) {
      System.out
          .println("  parsing for html-charset/xml-encoding attribute with codepage: US-ASCII");
    }
    try {
      lexer = new EncodingLexer(new InputStreamReader(limitedInputStream, "US-ASCII"));
      parser = new EncodingParser(lexer);
      csName = parser.htmlDocument();
      if (csName != null) {
        // TODO: prepare document with illegal value, then test: Decide to catch
        // exception and return
        // UnsupportedCharset.
        try {
          charset = Charset.forName(csName);
        } catch (UnsupportedCharsetException uce) {
          charset = UnsupportedCharset.forName(csName);
        }
      } else {
        charset = UnknownCharset.getInstance();
      }
    } catch (ANTLRException ae) {
      if (this.m_verbose) {
        System.out.println("  ANTLR parser exception: " + ae.getMessage());
      }
    } catch (Exception deepdown) {
      if (this.m_verbose) {
        System.out.println("  Decoding Exception: " + deepdown.getMessage()
            + " (unsupported java charset).");
      }
      if (charset == null) {
        if (csName != null) {
          charset = UnsupportedCharset.forName(csName);
        } else {
          charset = UnknownCharset.getInstance();
        }
      }
    }
    return charset;
  }

}

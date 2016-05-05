/*
 *
 *  HTMLCodepageDetector.java, a facade to an ANTLR grammar based
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
 */
package info.monitorenter.cpdetector.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 
 * <p>
 * <b> This class has been replaced by {@link ParsingDetector} and only exists for backward-compatibility.
 * </b> The name simply would not match any more, as parsing is not limited to html (1.1 includes xml as well). New code
 * should stick to the replacement. This class has been modified with version 1.1 and now is delegating all calls to an
 * instance of the replacement class (5 minutes with eclipse and a common interface), which introduces a small overhead
 * (minimal, as one invokevirtual is nothing compared to codepage detection by parsing).
 * </p>
 * 
 * <p>
 * Documentation may be found in the class {@link ParsingDetector}. It is valid for this class.
 * </p>
 * 
 * @version 1.1
 * @deprecated This class should not be used in new code. It has been replaced by {@link ParsingDetector}.
 *             Future versions may drop this class.
 * @see ParsingDetector
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * 
 */
public class HTMLCodepageDetector extends AbstractCodepageDetector {
    /**
     * Generated <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 3258135756131022643L;

    /**
     * Deprecation support. Instances of this class stick to the replacement.
     */
    private ParsingDetector delegate;

    public HTMLCodepageDetector() {
        this(false);
    }

    public HTMLCodepageDetector(boolean verbose) {
        super();
        this.delegate = new ParsingDetector(verbose);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cpdetector.io.AbstractCodepageDetector#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        return delegate.compareTo(o);
    }

    /**
     * 
     */
    public Charset detectCodepage(InputStream in, int length) throws IOException {
        return delegate.detectCodepage(in, length);
    }

    /**
     * 
     */
    public Charset detectCodepage(final URL url) throws IOException {
        return delegate.detectCodepage(url);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return delegate.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return delegate.toString();
    }
}

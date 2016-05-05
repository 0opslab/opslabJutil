/*
 *
 *  AbstractCharsetdetector.java, provides the default implementation
 *  for the high-level codepage detection method of interface ICodepageProcessor.
 *
 *  Copyright (C) Achim Westermann, created on 19.07.2004, 21:45:47
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
 *  If you modify or optimize the code in a useful way please let me know.
 *  Achim.Westermann@gmx.de
 *
 */
package info.monitorenter.cpdetector.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * <p>
 * This implementation provides the default implementation for the high-level codepage detection method
 * {@link #open(URL)}of the implemented interface ICodepageProcessor.
 * </p>
 * <p>
 * Also the Comparable interface implementation is provided here by comparing the class-name strings of the
 * implementations.
 * </p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * 
 */
public abstract class AbstractCodepageDetector implements ICodepageDetector {
    /**
     * 
     */
    public AbstractCodepageDetector() {
        super();
    }

    /**
     * Delegates to {@link #detectCodepage(java.io.InputStream, int)} with a buffered input stream.
     * <p>
     * 
     * @see ICodepageDetector#detectCodepage(URL)
     */
    public Charset detectCodepage(final URL url) throws IOException {
        Charset result;
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        result = this.detectCodepage(in, Integer.MAX_VALUE);
        in.close();
        return result;

    }

    /**
     * A default delegation to {@link #detectCodepage(URL)}that opens the document specified by the given URL with the
     * detected codepage.
     * <p>
     * 
     * @see ICodepageDetector#open(URL)
     */
    public final Reader open(final URL url) throws IOException {
        Reader ret = null;
        Charset cs = this.detectCodepage(url);
        if (cs != null) {
            ret = new InputStreamReader(new BufferedInputStream(url.openStream()), cs);
        }
        return ret;
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(final Object o) {
        String other = o.getClass().getName();
        String mine = this.getClass().getName();
        return mine.compareTo(other);
    }
}

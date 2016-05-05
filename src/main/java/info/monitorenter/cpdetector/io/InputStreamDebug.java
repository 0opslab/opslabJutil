/*
 *  InputStreamDebug.java  of project cpdetector. Prints out every character read. 
 *  Copyright (C) Achim Westermann, created on 19.10.2004, 22:04:36  
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

/**
 * Prints m_out every character read. Use as a proxy. Only needed for debugging the ANTLR Parser (ParsingDetector).
 * Therefore the chronological order is preserved: sun's StreamDecoder.CharsetDS (nio) replaces InputStream.read() by
 * buffer operations that fetch complete arrays. This is avoided by allowing to fetch only one char per method call.
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 * 
 */
public class InputStreamDebug extends InputStream {
    private InputStream m_delegate;

    public InputStreamDebug(InputStream delegate) {
        this.m_delegate = delegate;
    }

    public synchronized int read() throws IOException {
        int ret = this.m_delegate.read();
        System.out.print((char)ret);
        return ret;
    }

    /**
     * 
     */
    public int available() throws IOException {
        return m_delegate.available();
    }

    /**
     * 
     */
    public void close() throws IOException {
        m_delegate.close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return m_delegate.equals(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return m_delegate.hashCode();
    }

    /**
     * 
     */
    public void mark(int readlimit) {
        m_delegate.mark(readlimit);
    }

    /**
     * 
     */
    public boolean markSupported() {
        return m_delegate.markSupported();
    }

    /**
     * 
     */
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    /**
     * 
     */
    public int read(byte[] b, int off, int len) throws IOException {
        int ret = this.read();
        if (ret != -1) {
            b[off] = (byte)ret;
            ret = 1;
        }
        return ret;
    }

    /**
     * 
     */
    public void reset() throws IOException {
        m_delegate.reset();
    }

    /**
     * 
     */
    public long skip(long n) throws IOException {
        return m_delegate.skip(n);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return m_delegate.toString();
    }
}

/*
 *  UnknownCharset.java  cpdetector
 *  Copyright (C) Achim Westermann, created on 26.09.2004, 03:15:15  
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

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * A singleton Charset indicating that no encoding could be detected at all (regardless wether supported by platform or
 * not). Unlike the {@link UnsupportedCharset} singleton instances it will never equal anything.
 * <p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 * 
 */
public class UnknownCharset extends Charset {

    private static Charset instance;

    /**
     * @param canonicalName
     * @param aliases
     */
    private UnknownCharset() {
        super("void", null);
    }

    public static Charset getInstance() {
        if (instance == null) {
            instance = new UnknownCharset();
        }
        return instance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.nio.charset.Charset#contains(java.nio.charset.Charset)
     */
    public boolean contains(Charset cs) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.nio.charset.Charset#newDecoder()
     */
    public CharsetDecoder newDecoder() {
        throw new UnsupportedOperationException(
                "This is no real Charset but a flag you should test for!");
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.nio.charset.Charset#newEncoder()
     */
    public CharsetEncoder newEncoder() {
        throw new UnsupportedOperationException(
                "This is no real Charset but a flag you should test for!");
    }

}

/*
 *  ${file_name} of project cpdetector, <purpose>
 *  Copyright ${year} (C) Achim Westermann, created on ${date} ${time}.
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
package info.monitorenter.cpdetector;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;

import java.nio.charset.Charset;


/**
 * @author demian reachable (at)\@ rootring.com
 * 
 * Simple class that tries to detect the encoding of files given on the command-line.
 */
public class CharsetPrinter {
    private final CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();

    public CharsetPrinter() {
        detector.add(new ParsingDetector(false));
        detector.add(JChardetFacade.getInstance());
        detector.add(ASCIIDetector.getInstance());
    }

    public String guessEncoding(File f) throws MalformedURLException, IOException {
        Charset charset = detector.detectCodepage(f.toURL());

        if (charset == null)
            return null;

        return charset.name();
    }

    public static void main(String[] args) {
        CharsetPrinter cp = new CharsetPrinter();

        if (args.length < 1) {
            System.err
                    .println("Please provide one or more files to examine on the command line after the command.");
        }

        try {
            File f;

            for (int walk = 0; walk < args.length; walk++) {
                f = new File(args[walk]);

                if (f.exists() && f.canRead() && f.isFile()) {
                    System.out.println(args[walk] + " appears to be " + cp.guessEncoding(f));
                } else {
                    System.err.println(args[walk]
                            + " is not a file, does not exists or is not readable at this time.");
                    System.out.println(args[walk] + " appears to be UNKNOWN");
                }
            }
        } catch (MalformedURLException e) {
            System.err.println("The filename makes no sense.");
        } catch (IOException e) {
            System.err.println("Problem reading from file");
            e.printStackTrace();
        }
    }
}

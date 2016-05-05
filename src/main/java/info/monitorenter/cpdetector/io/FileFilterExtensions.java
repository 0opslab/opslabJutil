/*
 *  FileFilterExtensions.java of project cpdetector, <purpose>
 *  Copyright 2004 (C) Achim Westermann, created on 02.06.2004.
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

import java.io.File;
import java.io.FileFilter;
import java.util.StringTokenizer;


/**
 * <p>
 * Configureable implementation of {@link FileFilter} used for
 * selection of the test documents under the collection root. 
 * </p>
 * <p>
 * Kept private because no state-verification is performed: The configuration 
 * may (but shouldn't) be made at runtime. 
 * </p>
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 */
public final class FileFilterExtensions implements FileFilter {
	private String[] m_extensions;
	/**
	 * 
	 * @param extensionsWithoutDot A String[] containing extension strings without the dot like: <nobr><code>new String[]{"bat","txt","dict"}</code></nobr>. 
	 */
	public FileFilterExtensions(String[] extensionsWithoutDot) throws IllegalArgumentException {
		this.verify(extensionsWithoutDot);
		this.m_extensions = extensionsWithoutDot;
	}

	/**
	 * @param extensions The array with the Strings of m_extensions.
	 * @throws IllegalArgumentException If a String of the array is null or contains a dot ('.').
	 */
	private void verify(String[] extensions) throws IllegalArgumentException {
		String current;
		StringBuffer msg = new StringBuffer();
		for (int i = extensions.length - 1; i >= 0; i--) {
			current = extensions[i];
			if (current == null) {
				msg.append("Extension at index " + i + " is null!\n");
			} else if (current.indexOf('.') != -1) {
				msg.append("Extension \"" + current + "\" contains a dot!\n");
			}
		}
		if (msg.length() > 0) {
			throw new IllegalArgumentException(msg.toString());
		}
	}

	/* (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File pathname) {
		boolean ret = false;
		// search for extension without dot. 
		StringTokenizer tokenizer = new StringTokenizer(pathname.getAbsolutePath(), ".");
		String extension = "no.txt"; // a dot, because verify will not allow these tokens: won't accept, if no extension in pathname.
		while (tokenizer.hasMoreElements()) {
			extension = tokenizer.nextToken();
		}
		for (int i = this.m_extensions.length - 1; i >= 0; i--) {
			if (this.m_extensions[i].equals(extension)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

}
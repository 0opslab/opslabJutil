/*
 *  ${file_name} of project cpdetector, <purpose>
 *  Copyright ${year} (C) Achim Westermann, created on 11.04.2004.
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

import info.monitorenter.util.Entry;
import info.monitorenter.util.FileUtil;
import info.monitorenter.util.StringUtil;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 *  
 */
public class ClassFileFilterIsA implements IClassFileFilter, FileFilter {

  protected Set superclasses = new TreeSet();

  protected File[] classpaths;

  /**
   *  
   */
  public ClassFileFilterIsA() {
    super();
    this.scanClassPath();
  }

  private void scanClassPath() {
    URLClassLoader urlcl = (URLClassLoader) this.getClass().getClassLoader();
    URL[] urls = urlcl.getURLs();
    // TODO: remove debug printing.
    List collect = new LinkedList();
    File f;
    for (int i = 0; i < urls.length; i++) {
      f = this.urlToFile(urls[i]);
      if (f != null) {
        collect.add(f);
      }
    }
    this.classpaths = (File[]) collect.toArray(new File[collect.size()]);
  }

  private File urlToFile(URL url) {
    File ret = null;
    if (url.getProtocol().equalsIgnoreCase("file")) {
      ret = new File(url.getFile());
    }
    return ret;
  }

  /**
   * <p>
   * Adds the given argument to the acceptance filter. The filter will only
   * return true for a Class a in the following condition:
   * 
   * <pre>
   * (a instanceof c)
   * </pre>
   * 
   * </p>
   * <p>
   * This method may be called several times. The accept method will return true
   * if the given Class is derived from any of the internal super classes.
   * </p>
   * 
   * @param c
   *          A non-final Class.
   * @return true, if the given class could be added. It is not added, if
   *         argument c is final (makes no sense as a final class is never a
   *         superclass) or the given Class was already contained.
   *  
   */
  public synchronized boolean addSuperClass(Class c) {
    boolean ret = false;
    if ((c.getModifiers() & Modifier.FINAL) != 0) {
    }
    else {
      ret = this.superclasses.add(c);
    }
    return ret;
  }

  /**
   * @return true if the given Class is derived from any of the internal super
   *         classes.
   * @see #addSuperClass(Class)
   * @see IClassFileFilter#accept(Class)
   */
  public boolean accept(Class c) {
    boolean ret = false;
    Iterator it = this.superclasses.iterator();
    while (it.hasNext() && !ret) {
      ret = ((Class) it.next()).isAssignableFrom(c);
    }
    return ret;
  }

  /**
   * Tries to detect (from the file path and the Classloaders urls) and load the
   * corresponding class and delegates to {@link #accept(Class)}.
   * 
   * @see FileFilter#accept(File)
   */
  public boolean accept(File pathname) {
    boolean ret = false;
    if (pathname.isDirectory()) {
      ret = true;
    }
    else {
      String ext = FileUtil.cutExtension(pathname.getName()).getValue().toString();
      if (ext.equals("jar")) {
        // is a "directory":
        ret = true;
      }
      else if (ext.equals("class")) {
        Class cl = this.forFile(pathname);
        if (cl != null) {
          ret = this.accept(cl);
        }
      }
      else {
        ret = false;
      }
    }
    return ret;
  }

  private Class forFile(File f) {
    Class ret = null;
    Map.Entry searchpath = new Entry("", "");
    String filename = f.getAbsolutePath();
    for (int i = 0; i < this.classpaths.length && (searchpath.getKey().equals("")); i++) {
      searchpath = StringUtil.prefixIntersection(this.classpaths[i].getAbsolutePath(), filename);
    }
    if (!searchpath.getKey().equals("")) {
      // the given file might be a full match of the classpath (e.g. a jarfile)
      if (!searchpath.getValue().equals("")) {

        // remove trailing .class:
        // bad reuse of filename
        filename = (String) FileUtil.cutExtension((String) searchpath.getValue()).getKey();
        // file path to class syntax. First worked with "file.separator", but the file may be some 
        // facade (e.g. a jar entry) that sticks to unix syntax even on windows machines....
        filename = (filename).replace('/', '.');
        filename = (filename).replace('\\', '.');
        // remove potential leading dots:
        if (filename.charAt(0) == '.') {
          filename = filename.substring(1);
        }
        try {
          ret = Class.forName(filename);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    ClassFileFilterIsA test = new ClassFileFilterIsA();
    System.out.println("Adding interface: " + FileFilter.class.getName() + " to instance test (" + test.getClass().getName() + ")");
    test.addSuperClass(FileFilter.class);
    System.out.println("test.accept(" + test.getClass().getName() + " : " + test.accept(test.getClass()));
    System.out.println("test.accept(new File(\"bin/cpdetector/io/ClassfileFilterIsA.class\").getAbsoluteFile()) : "
        + test.accept(new File("bin/cpdetector/io/ClassfileFilterIsA.class").getAbsoluteFile()));
  }
}
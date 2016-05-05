/*
 * 
 *  ClassFileChooser.java  cpdetector
 *  Copyright (C) Achim Westermann, created on 23.10.2004, 00:51:45  
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

package info.monitorenter.cpdetector.test.ui;

import info.monitorenter.cpdetector.io.IClassFileFilter;
import info.monitorenter.cpdetector.io.JarArchive;
import info.monitorenter.util.FileUtil;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 *  
 */
public class ClassFileChooser extends JFileChooser {

    private File m_selected;

    private JList listHandle;

    private MouseListener mouseListener = new BugfixMouseListener(this);

    private Set classFilters = new TreeSet();

    /**
     * @param fsv
     */
    public ClassFileChooser(FileSystemView fsv) {

        super(fsv);
        this.init();
    }

    private void init() {

        this.listHandle = this.searchJList(this);
        this.killRenameAction();
        this.setFileSystemView(new URLFileSystemView());
    }

    /**
     * Disables the rename action that is triggered in the normal implementation
     * of JFileChooser (javax.swing.plaf ui) by a SingleClick listener. Called
     * from the constructor.
     */
    private void killRenameAction() {

        if (this.listHandle != null) {
            String listenerClassName;
            System.out.println(System.getProperty("java.version"));
            float version = Float.parseFloat(System.getProperty("java.version").substring(0, 3));
            String recognize = (version < 1.5) ? "SingleClick" : "sun.swing.FilePane";
            MouseListener[] mouseListeners = this.listHandle.getMouseListeners();
            for (int i = 0; i < mouseListeners.length; i++) {
                listenerClassName = mouseListeners[i].getClass().getName();
                System.out.println("MouseListener: " + listenerClassName);
                if (listenerClassName.indexOf(recognize) != -1) {
                    this.listHandle.removeMouseListener(mouseListeners[i]);
                    if (version >= 1.5) {
                        this.listHandle.addMouseListener(this.mouseListener);
                    }
                    break;
                }
            }
        }
    }

    /**
     * <p>
     * Hack to get the {@link List}of the {@link JFileChooser}instance used for
     * loading sounds: We have to remove the "Single Click" listener that allows
     * renaming files.
     * </p>
     * 
     * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
     */
    private JList searchJList(Container fileChooser) {

        JList ret = null;
        // First check, wether i am a JList:
        if (fileChooser instanceof JList) {
            ret = (JList)fileChooser;
        }
        // Ok, me not: let's ask the m_children.
        else {
            Component[] children = fileChooser.getComponents();

            for (int i = children.length - 1; i >= 0; i--) {
                if (children[i] instanceof Container) {
                    ret = searchJList((Container)children[i]);
                    if (ret != null) {
                        break;
                    }
                }
            }
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JFileChooser#getCurrentDirectory()
     */
    public File getCurrentDirectory() {

        return super.getCurrentDirectory();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JFileChooser#setCurrentDirectory(java.io.File)
     */
    public void setCurrentDirectory(File dir) {

        super.setCurrentDirectory(dir);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JFileChooser#getSelectedFile()
     */
    public File getSelectedFile() {

        return this.m_selected;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JFileChooser#setSelectedFile(java.io.File)
     */
    public void setSelectedFile(File file) {

        this.m_selected = file;
    }

    /* (non-Javadoc)
     * @see javax.swing.JFileChooser#accept(java.io.File)
     */
    public boolean accept(File f) {

        boolean ret = true;
        if (super.accept(f)) {
            Iterator it = this.classFilters.iterator();
            while (it.hasNext()) {
                ret = ((IClassFileFilter)it.next()).accept(f);
                if (ret == false)
                    break;
            }
        } else {
            ret = false;
        }
        return ret;
    }

    /**
     * <p>
     * Note that not only the FileFilter instances control 
     * the acceptance but also the added {@link IClassFileFilter} 
     * instances.
     * </p>
     * @see #addClassFileFilter(IClassFileFilter)
     * @see JFileChooser#addChoosableFileFilter(FileFilter)
     */
    public void addChoosableFileFilter(FileFilter filter) {

        super.addChoosableFileFilter(filter);
    }

    /**
     * <p>
     * Unlike the FileFilter instances in the superclass, multiple 
     * ClassFileFilters are allowd at the same time. This is 
     * eaysier to implement as it is not planned to let the UI 
     * select the ClassFileFilter to use but only the application 
     * (by now). 
     * </p>
     * <P>
     * The given classfile is accepted, if all ClassFileFilter instances 
     * accept it (logical AND).
     * </p>
     * @see #addChoosableFileFilter(FileFilter)
     *
     */
    public void addClassFileFilter(IClassFileFilter cff) {

        this.classFilters.add(cff);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JFileChooser#setSelectedFiles(java.io.File[])
     */
    public void setSelectedFiles(File[] selectedFiles) {

        super.setSelectedFiles(selectedFiles);
    }

    static class URLFileSystemView extends FileSystemView {

        private File[] roots;

        URLFileSystemView() {

            URLClassLoader urlcl = (URLClassLoader)this.getClass().getClassLoader();
            URL[] urls = urlcl.getURLs();
            System.out.println(this.getClass().getClassLoader() + " urls: ");
            File f;
            List l = new LinkedList();
            for (int i = 0; i < urls.length; i++) {
                System.out.println(urls[i]);
                f = new File(urls[i].getFile());
                if (f.exists()) {
                    String ext = FileUtil.cutExtension(f.getName()).getValue().toString();
                    if (ext.equals("jar")) {
                        try {
                            f = new JarArchive(f.getAbsolutePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    l.add(f);
                }
            }
            // to array:
            this.roots = (File[])l.toArray(new File[l.size()]);
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#createNewFolder(java.io.File)
         */
        public File createNewFolder(File containingDir) throws IOException {

            throw new UnsupportedOperationException("This FileSystemView is read only!");
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getRoots()
         */
        public File[] getRoots() {

            return this.roots;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getHomeDirectory()
         */
        public File getHomeDirectory() {

            return this.roots[0];
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getDefaultDirectory()
         */
        public File getDefaultDirectory() {

            return this.roots[0];
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getFiles(java.io.File,
         *      boolean)
         */
        public File[] getFiles(File dir, boolean useFileHiding) {

            File[] ret;
            if ((dir instanceof JarArchive) || (FileUtil.cutExtension(dir.getName()).getValue().equals("jar"))) {
                ret = dir.listFiles();
            } else {
                ret = super.getFiles(dir, useFileHiding);
            }
            // jar - support:
            for (int i = 0; i < ret.length; i++) {
                if (FileUtil.cutExtension(ret[i].getName()).getValue().equals("jar")) {
                    try {
                        ret[i] = new JarArchive(ret[i].getAbsolutePath());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return ret;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getParentDirectory(java.io.File)
         */
        public File getParentDirectory(File dir) {

            File ret = null;
            if (dir instanceof JarArchive) {
                ret = dir.getParentFile();
            } else {
                ret = super.getParentDirectory(dir);
            }
            return ret;
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getSystemDisplayName(java.io.File)
         */
        public String getSystemDisplayName(File f) {

            // TODO Auto-generated method stub
            return super.getSystemDisplayName(f);
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getSystemIcon(java.io.File)
         */
        public Icon getSystemIcon(File f) {

            // TODO Auto-generated method stub
            return super.getSystemIcon(f);
        }

        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#getSystemTypeDescription(java.io.File)
         */
        public String getSystemTypeDescription(File f) {

            // TODO Auto-generated method stub
            return super.getSystemTypeDescription(f);
        }


        /*
         * (non-Javadoc)
         * 
         * @see javax.swing.filechooser.FileSystemView#isRoot(java.io.File)
         */
        public boolean isRoot(File f) {

            for (int i = 0; i < this.roots.length; i++) {
                if (this.roots[i].equals(f)) {
                    return true;
                }
            }
            return false;
        }
    }

    class BugfixMouseListener implements MouseListener {

        private ClassFileChooser m_peer;

        private boolean clickToggle = false;

        private long tstamp;

        private int ClickLatency = 800;

        /**
         * Decon with the peer. 
         * 
         * @param peer the classfilechooser to notify with events.
         */
        public BugfixMouseListener(final ClassFileChooser peer) {

            this.m_peer = peer;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        public void mouseClicked(MouseEvent e) {

            if (!this.clickToggle || (System.currentTimeMillis() - this.tstamp > this.ClickLatency)) {
                this.clickToggle = true;
                this.tstamp = System.currentTimeMillis();
            } else {
                File selected = ((File)m_peer.listHandle.getSelectedValue());
                if (selected == null) {

                } else if (selected.isDirectory()) {
                    ClassFileChooser.this.setCurrentDirectory(selected);
                } else {
                    this.m_peer.approveSelection();
                }
                this.clickToggle = false;
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
         */
        public void mouseEntered(MouseEvent e) {

            // TODO Auto-generated method stub
            int i = 0;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
         */
        public void mouseExited(MouseEvent e) {

            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
         */
        public void mousePressed(MouseEvent e) {

            int i = 0;

        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
         */
        public void mouseReleased(MouseEvent e) {

            int i = 0;
        }
    }

    //  public class DefaultListModel extends AbstractListModel {
    //
    //    private List list = new ArrayList(40);
    //
    //    /*
    //     * (non-Javadoc)
    //     * 
    //     * @see javax.swing.ListModel#getElementAt(int)
    //     */
    //    public Object getElementAt(int index) {
    //      return this.list.get(index);
    //    }
    //
    //    /**
    //     *  
    //     */
    //    public synchronized void clear() {
    //      int size = this.list.size();
    //      if (size > 0)
    //        size--;
    //      this.list.clear();
    //      this.fireIntervalRemoved(this, 0, size);
    //    }
    //
    //    /*
    //     * (non-Javadoc)
    //     * 
    //     * @see javax.swing.ListModel#getSize()
    //     */
    //    public int getSize() {
    //      return this.list.size();
    //    }
    //
    //    public synchronized void addAll(File[] farr) {
    //      if (farr == null || farr.length == 0)
    //        return;
    //      int start = this.list.size();
    //      for (int i = 0; i < farr.length; i++) {
    //        this.list.add(farr[i]);
    //      }
    //
    //      int stop = this.list.size() - 1;
    //      this.fireIntervalAdded(this, start, stop);
    //    }
    //
    //  }

}
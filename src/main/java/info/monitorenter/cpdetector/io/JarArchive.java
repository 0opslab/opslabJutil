/*
 * 
 *  JarArchive.java  cpdetector
 *  Copyright (C) Achim Westermann, created on 30.10.2004, 01:39:02  
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

import info.monitorenter.cpdetector.util.collections.ITreeNode;
import info.monitorenter.cpdetector.util.collections.TreeNodeUniqueChildren;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * A jar file that pretends to be a simple file by extending {@link File}.
 * <p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 * 
 */
public class JarArchive extends File {

    protected JarFile jar;

    protected Set childs = new TreeSet();

    /**
     * @param pathname
     * @throws IOException
     */
    public JarArchive(String pathname)
    throws IOException {

        super(pathname);
        this.jar = new JarFile(pathname);
        ITreeNode root = this.parseTree();
        System.out.println("tree:");
        System.out.println(root.toString() + '\n');
        this.buildTree(root, this);
    }

    /**
     * Only for the subclass to avoid double-parsing.
     */
    private JarArchive(File f) {

        super(f.getAbsolutePath());
    }

    /**
     * Build a Tree from the entries.
     * 
     */
    private ITreeNode parseTree() {

        ITreeNode root = new TreeNodeUniqueChildren();
        ITreeNode newnode, oldnode;
        Enumeration entries = this.jar.entries();
        String entry;
        while (entries.hasMoreElements()) {
            newnode = root;
            oldnode = root;
            entry = ((JarEntry)entries.nextElement()).getName();
            System.out.println("Entry: " + entry);
            StringTokenizer tokenizer = new StringTokenizer(entry, "/");
            while (tokenizer.hasMoreElements()) {
                String path = tokenizer.nextToken();
                newnode = new TreeNodeUniqueChildren(path);
                oldnode.addChildNode(newnode);
                oldnode = newnode;
            }
        }
        return root;
    }

    protected void buildTree(ITreeNode node, JarArchive element) throws IOException {

        List childNodes = node.getAllChildren();
        Iterator childNodesIt = childNodes.iterator();
        ITreeNode childNode;
        JarElement child;
        JarEntry entry;
        String search;
        while (childNodesIt.hasNext()) {
            childNode = (ITreeNode)childNodesIt.next();
            search = getSearchPath(childNode);
            System.out.println("Searching for: " + search);
            entry = this.jar.getJarEntry(search);
            if (entry == null) {
                System.err.println("Entry for " + search + " (" + this.jar.getName() + ")is null!!!");
            } else {
                System.out.println("Entry: " + entry.toString());
                child = new JarElement(entry, this);
                child.buildTree(childNode, this);
                this.childs.add(child);
            }

        }
        System.out.println(this + " has finished building...");
    }

    /**
     * Little helper that transforms (back) the nodes of the tree that was parsed from the entries during initialization
     * to canonical strings for entries.
     */
    private String getSearchPath(ITreeNode node) {

        List l = new LinkedList();
        node.getUserObjectPathFromRoot(l);
        Iterator it = l.iterator();
        StringBuffer ret = new StringBuffer();
        int i = 0;
        String token;
        while (it.hasNext()) {
            // avoid the "root" token.
            token = it.next().toString();
            if (i != 0) {
                ret.append(token);
                if (it.hasNext()) {
                    ret.append('/');
                }
            }

            i++;
        }
        return ret.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#canRead()
     */
    public boolean canRead() {

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#canWrite()
     */
    public boolean canWrite() {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#compareTo(java.io.File)
     */
    public int compareTo(File pathname) {

        return super.compareTo(pathname);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#createNewFile()
     */
    public boolean createNewFile() throws IOException {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#delete()
     */
    public boolean delete() {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#deleteOnExit()
     */
    public void deleteOnExit() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        return super.equals(obj);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#exists()
     */
    public boolean exists() {

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getAbsoluteFile()
     */
    public File getAbsoluteFile() {

        return super.getAbsoluteFile();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getAbsolutePath()
     */
    public String getAbsolutePath() {

        String ret = super.getAbsolutePath();
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getCanonicalFile()
     */
    public File getCanonicalFile() throws IOException {

        return super.getCanonicalFile();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getCanonicalPath()
     */
    public String getCanonicalPath() throws IOException {

        return super.getCanonicalPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getName()
     */
    public String getName() {

        return super.getName();

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getParent()
     */
    public String getParent() {

        return super.getParent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getParentFile()
     */
    public File getParentFile() {

        return super.getParentFile();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#getPath()
     */
    public String getPath() {

        return super.getPath();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {

        return super.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#isAbsolute()
     */
    public boolean isAbsolute() {

        return super.isAbsolute();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#isDirectory()
     */
    public boolean isDirectory() {

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#isFile()
     */
    public boolean isFile() {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#isHidden()
     */
    public boolean isHidden() {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#lastModified()
     */
    public long lastModified() {

        return super.lastModified();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#length()
     */
    public long length() {

        return super.length();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#list()
     */
    public String[] list() {

        String[] ret = new String[this.childs.size()];
        Iterator it = this.childs.iterator();
        for (int i = 0; it.hasNext(); i++) {
            ret[i] = it.next().toString();
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#list(java.io.FilenameFilter)
     */
    public String[] list(FilenameFilter filter) {

        List ret = new LinkedList();
        Iterator it = this.childs.iterator();
        File next;
        while (it.hasNext()) {
            next = (File)it.next();
            if (filter.accept(next.getParentFile(), next.getName())) {
                ret.add(next.toString());
            }
        }
        return (String[])ret.toArray(new String[ret.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#listFiles()
     */
    public File[] listFiles() {

        File[] ret = new File[this.childs.size()];
        Iterator it = this.childs.iterator();
        for (int i = 0; it.hasNext(); i++) {
            ret[i] = (File)it.next();
        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#listFiles(java.io.FileFilter)
     */
    public File[] listFiles(FileFilter filter) {

        List ret = new LinkedList();
        Iterator it = this.childs.iterator();
        File next;
        while (it.hasNext()) {
            next = (File)it.next();
            if (filter.accept(next)) {
                ret.add(next);
            }
        }
        return (File[])ret.toArray(new File[ret.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#listFiles(java.io.FilenameFilter)
     */
    public File[] listFiles(FilenameFilter filter) {

        List ret = new LinkedList();
        Iterator it = this.childs.iterator();
        File next;
        while (it.hasNext()) {
            next = (File)it.next();
            if (filter.accept(next.getParentFile(), next.getName())) {
                ret.add(next);
            }
        }
        return (File[])ret.toArray(new File[ret.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#mkdir()
     */
    public boolean mkdir() {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#mkdirs()
     */
    public boolean mkdirs() {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#renameTo(java.io.File)
     */
    public boolean renameTo(File dest) {

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#setLastModified(long)
     */
    public boolean setLastModified(long time) {

        return super.setLastModified(time);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#setReadOnly()
     */
    public boolean setReadOnly() {

        return super.setReadOnly();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {

        return super.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#toURI()
     */
    public URI toURI() {

        return super.toURI();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.File#toURL()
     */
    public URL toURL() throws MalformedURLException {

        return super.toURL();
    }

    class JarElement extends JarArchive {

        private File m_parent;

        private JarEntry m_entry;

        /**
         * Creates an element with the given entry and parent file.<p>
         * 
         * @param entry the contained entry.  
         * 
         * @param parent the parent file. 
         * 
         * @throws IOException if sth goes wrong. 
         */
        JarElement(JarEntry entry, File parent)
        throws IOException {

            super(new File(JarArchive.this.jar.getName()));
            this.m_parent = parent;
            if (entry == null) {
                System.err.println("Entry is null.");
            }
            this.m_entry = entry;
            this.jar = JarArchive.this.jar;
        }

        /*
         * protected void buildTree(ITreeNode node,JarArchive element) throws IOException{ List childNodes =
         * node.getAllChildren(); Iterator childNodesIt = childNodes.iterator(); ITreeNode childNode; JarElement child;
         * JarEntry entry; String search; while(childNodesIt.hasNext()){ childNode = (ITreeNode)childNodesIt.next();
         * search = this.entry.getName()+"/"+childNode.getUserObject().toString(); entry = this.jar.getJarEntry(search);
         * child = new JarElement( entry, this ); this.childs.add(child); child.buildTree(childNode,this); } }
         * 
         */
        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#getAbsoluteFile()
         */
        public File getAbsoluteFile() {

            return this;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#getAbsolutePath()
         */
        public String getAbsolutePath() {

            String prefix = JarArchive.this.jar.getName();
            String postfix = this.m_entry.getName();
            return prefix + "/" + postfix;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#getName()
         */
        public String getName() {

            return this.m_entry.getName();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#getParent()
         */
        public String getParent() {

            return this.m_parent.getAbsolutePath();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#getParentFile()
         */
        public File getParentFile() {

            return this.m_parent;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#isDirectory()
         */
        public boolean isDirectory() {

            // would not work, don't know why (false, even if is a dir):
            // this.entry.isDirectory();
            return this.childs.size() != 0;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#isFile()
         */
        public boolean isFile() {

            return !this.isDirectory();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#lastModified()
         */
        public long lastModified() {

            return this.m_entry.getTime();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#length()
         */
        public long length() {

            return this.m_entry.getSize();
        }

        public String toString() {

            return this.m_entry.toString();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.io.File#getPath()
         */
        public String getPath() {

            return this.m_entry.getName();
        }
    }

}

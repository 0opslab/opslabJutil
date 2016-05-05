/*
 * ITreenode, a basic interface for a tree containing an inner default implementation.
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
 *
 **/

package info.monitorenter.cpdetector.util.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Basic interface for a tree node. It is an approach at node-level instead of a
 * procedural interface from outside. Working with a tree from outside very
 * often is not as fast and incurs additional memory constumption for saving
 * states and partial results (no recursion may keep traversal-related data on
 * the heap, but the current positions for step-operations has to saved).
 * Supporting an "outside-interface" for a tree is recommended by using a
 * visitor pattern but may also be done in a classical way.
 * <p>
 * Every node of the tree may carry a so-called "user-Object". While the
 * structure of the tree models the relation of these objects, they themselve
 * contain the data needed.
 * <p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 */
public interface ITreeNode {
  /**
   * Returned from {@link #getParent()} of the root node of the tree.
   * <p>
   */
  public static ITreeNode ROOT = new DefaultTreeNode();

  /**
   * Returns the "user" Object that may be carried by the <tt>ITreeNode</tt>,
   * or null, if no one was assigned.
   * <p>
   * 
   * @return The user Object carried by this treenode or null, if no one was
   *         assigned.
   */
  public Object getUserObject();

  /**
   * Assigns the "user" Object that may be carried by the <tt>ITreeNode</tt>.
   * <p>
   * 
   * @return The previous user Object that was carried or null, if no one was
   *         assigned.
   */
  public Object setUserObject(Object store);

  /**
   * <p>
   * Marks this <tt>ITreeNode</tt> instance (e.g. for being visited or
   * invisible). Marking may be used to traverse the tree in an asynchronous
   * manner from outside (TreeIterator) or for other desireable tasks.
   * </p>
   * <p>
   * Subsequent calls to this method should not change the state to "unmarked".
   * </p>
   * 
   */
  public void mark();

  /**
   * <p>
   * Unmarks this <tt>ITreeNode</tt> instance (e.g. for being visited or
   * invisible). Marking may be used to traverse the tree in an asynchronous
   * manner from outside (TreeIterator) or for other desireable tasks.
   * </p>
   * <p>
   * Subsequent calls to this method should not change the state to "marked".
   * </p>
   */
  public void unmark();

  /**
   * <p>
   * Check, wether this <tt>ITreeNode</tt> is marked.
   * </p>
   * 
   * @return True, if this <tt>ITreeNode</tt> is marked, false else.
   */
  public boolean isMarked();

  /**
   * <p>
   * Returns the amount of child <tt>ITreeNodes</tt> of this
   * <tt>ITreeNode</tt>.
   * </p>
   */
  public int getChildCount();

  /**
   * <p>
   * Get the amount of direct and indirect childs of this <tt>ITreeNode</tt>.
   * </p>
   * 
   * @return The total amount of all <tt>ITreeNode</tt> instances of this
   *         subtree (including this node).
   */
  public int getSubtreeCount();

  /**
   * <p>
   * The {@link Iterator}returned will not traverse the whole subtree
   * but only the direct child nodes of this <tt>ITreeNode</tt>.
   * </p>
   * 
   * @return An {@link Iterator}over the direct childs of this
   *         <tt>ITreeNode</tt>.
   */
  public Iterator getChilds();

  /**
   * <p>
   * Get the parent node of this <tt>ITreeNode</tt>. If the TreeNode has no
   * parent node (e.g. a member of an implementation for the parent node is
   * null), {@link #ROOT}has to be returned.
   * </p>
   * 
   * @return The parent <tt>ITreeNode</tt> of this node or {@link #ROOT}, if
   *         this node is the root.
   */
  public ITreeNode getParent();

  /**
   * <p>
   * This method should not be called from outside. It is a callback for the
   * method {@link #addChildNode(ITreeNode)}and should be used by
   * implementations of that method but not any further. Else inconsistencies
   * could occur: A node thinks it is the father of another node which itself
   * does not know about that relation any more (remove itself from the old
   * parent member's list could avoid it).
   * </p>
   * 
   * @param parent
   *          The new parental node.
   */
  public void setParent(ITreeNode parent);

  /**
   * <p>
   * Adds the given <tt>ITreeNode</tt> to the set of this instances childs.
   * Note, that the given node has to get to know, that it has a new parent
   * (implementation detail).
   * </p>
   * 
   * @param node
   *          The new child <tt>ITreeNode</tt> whose parent this instance will
   *          become.
   * @return True, if the operation was succesful: For example some
   *         implementations (e.g. with support for unique user Objects in
   *         childs on the same level with the same parent node) may dissallow
   *         duplicate child nodes.
   */
  public boolean addChildNode(ITreeNode node);

  /**
   * <p>
   * Adds all given <tt>ITreeNode</tt> instances to the set of this instances
   * childs. This operation should delegate to {@link #addChildNode(ITreeNode)}.
   * </p>
   * 
   * @param nodes
   *          An arry of <tt>ITreeNode</tt> instances.
   * @return True, if all childs could be added (null instances are skipped),
   *         false else.
   */
  public boolean addChildNodes(ITreeNode[] nodes);

  /**
   * <p>
   * Comfortable method for adding child nodes. Could be expressed as: <font
   * color="gray">
   * 
   * <pre>
   * ...
   * ITreeNode ret = new &lt;TreeNodeImpl&gt;(m_userObject);
   * if(this.addChildNode(ret){
   *   return ret;
   * }
   * else{
   *   return null;
   * }
   * ...
   * </pre>
   * 
   * </font>
   * </p>
   * 
   * @param userObject
   *          The Object that should be carried by the new <tt>ITreeNode</tt>
   *          or null, if no one is desired.
   * @return The newly allocated <tt>ITreeNode</tt> or <b>null, if the
   *         operation failed </b>.
   */
  public ITreeNode addChild(Object userObject);

  /**
   * Adds all given Objects to newly instanciated <tt>ITreeNode</tt> instances
   * that are added to the set of this instances childs. This operation should
   * delegate to {@link #addChild(Object)}.
   * <p>
   * 
   * @param userObjects
   *          An arry of <tt>Objects</tt> instances which will become the
   *          m_userObject members of the newly created <tt>ITreeNode</tt>
   *          instances.
   * 
   * @return An array containing all new <tt>ITreeNode</tt> instances created
   *         (they contain the user objects).
   */

  public ITreeNode[] addChildren(Object[] userObjects);

  /**
   * <p>
   * Remove the given <tt>ITreeNode</tt> from this node. If the operation is
   * successful the given node will not have any parent node (e.g. null for
   * parent member) but be the root node of it's subtree.
   * </p>
   * <p>
   * The operation may fail, if the given <tt>ITreeNode</tt> is no child of
   * this node, or the implementation permits the removal.
   * </p>
   * 
   * @param node
   *          A child of this <tt>ITreeNode</tt> <b>(by the means of the
   *          {@link #equals(Object)}operation) </b>.
   * @return True, if the removal was successful, false if not.
   */
  public boolean removeChild(ITreeNode node);

  /**
   * Remove the <tt>ITreeNode</tt> <b>and it's whole subree! </b> in this
   * node's subtree, that contains a user Object equal to the given argument.
   * The search is performed in a recursive manner quitting when the first
   * removal could be performed.
   * <p>
   * 
   * @param userObject
   *          The user Object identifying the treenode to remove.
   * 
   * @return The <tt>TreeNode</tt> (being the new root of it's subtree) that
   *         was removed from this node's subtree or null, if no
   *         <tt>ITreeNode</tt> contained an equal user Object.
   */
  public ITreeNode remove(Object userObject);

  /**
   * Remove all child nodes of the <tt>ITreeNode</tt>.
   * <p>
   * 
   * @return A {@link List}containing all removed child nodes. An
   *         empty List should be provided instead of null!
   */
  public List removeAllChildren();

  /**
   * <p>
   * Get all child nodes of the <tt>ITreeNode</tt>.
   * </p>
   * 
   * @return A {@link List}containing all child nodes. An empty List
   *         should be provided instead of null!
   */

  public List getAllChildren();

  /**
   * Find out, wether a certain "user" Object is carried by any of this
   * <tt>ITreeNodes</tt> subtree.
   * <p>
   * 
   * @param userObject
   *          Any Object, that might be contained in this tree -
   *          <b>Identification is done by the means of the Objects
   *          {@link Object#equals(Object)}- method.
   * 
   * @return True, if any node of this subtree (including this
   *         <tt>ITreeNode</tt> contained a user Object that was equal to the
   *         given argument.
   */
  public boolean contains(Object userObject);

  /**
   * <p>
   * Find out, wether the given <tt>ITreeNode</tt> is a member of this node's
   * subtree.
   * </p>
   * 
   * @param node
   *          A <tt>ITreeNode</tt> that migth possibly linked to (or be) this
   *          instance by the means of the equals operation.
   * @return True, if an <tt>ITreeNode</tt> equal to the argument was found in
   *         the subtree started by this node.
   * 
   * @see #equals(Object)
   */
  public boolean containsNode(ITreeNode node);

  /**
   * <p>
   * Find out, wether this <tt>ITreeNode</tt> is a childless leaf.
   * </p>
   * 
   * @return True, if this <tt>ITreeNode</tt> has no child nodes.
   */
  public boolean isLeaf();

  /**
   * <p>
   * Find out, wether there is no path to a higher parental node from this
   * <tt>ITreeNode</tt>.
   * </p>
   * 
   * @return True if this poor node is an orphan.
   */
  public boolean isRoot();

  /**
   * @param l
   *          An empty List: After this call it will be filled with
   *          {@link ITreeNode}instances starting from the root node to the
   *          current node that is invoked.
   */
  public void getPathFromRoot(List l);

  /**
   * @param l
   *          An empty List: After this call it will be filled with the
   *          {@link #getUserObject()}instances starting from the root node to
   *          the current node that was invoked.
   * 
   */
  public void getUserObjectPathFromRoot(List l);

  /**
   * <p>
   * Convenience method recommended for usage by implemenations.
   * </p>
   * 
   * @param o
   *          Possibly a <tt>ITreeNode</tt> that has to be checked for
   *          equality.
   * @return True, if your implementation judges both instances equal.
   * 
   * @see #containsNode(ITreeNode node)
   * @see #removeChild(ITreeNode node)
   */
  public boolean equals(Object o);

  /**
   * <p>
   * Generic operations in default implementations may need to allocate new
   * instances but have to choose the right type to support the provided
   * invariants.
   * </p>
   * <p>
   * If you provide a subclass that has invariants you should overload this.
   * </p>
   * 
   * @return A new allocated instance of the concrete impelementation.
   */
  public ITreeNode newInstance();

  /**
   * <p>
   * Plain-forward implementation of the {@link ITreeNode}interface.
   * </p>
   * <p>
   * This implementation covers many of the algorithms that may be somewhat
   * tricky to implement in an elegant way for beginners. Subclasses may add
   * other policies and constraints to achieve a special invariant with a
   * certain behaviour.
   * </p>
   * 
   * @author <a href="mailto:Achim.Westermann@gmx.de>Achim Westermann </a>
   */
  public static class DefaultTreeNode implements ITreeNode, Comparable<ITreeNode> {
    /**
     * Flag for saving the marking-state. False by default.
     */
    protected boolean marked = false;

    /**
     * Two instances are equal, if they both are of this type and user objects
     * are equal.
     * 
     * @see #setUserObject(Object)
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj) {
      boolean ret = false;
      if (obj instanceof DefaultTreeNode) {
        DefaultTreeNode other = (DefaultTreeNode) obj;
        Object myUser = this.getUserObject();
        Object himUser = other.getUserObject();
        ret = (myUser == null) ? ((himUser == null) ? true : false) : (myUser.equals(himUser));
      }
      return ret;
    }

    /**
     * Member for saving the user Object.
     * 
     * @see #getUserObject()
     */
    protected Object m_userObject = null;

    /**
     * The parent node.
     */
    ITreeNode m_parent = null;

    /**
     * A {@link List}of child <tt>ITreeNode</tt> instances.
     */
    protected SortedSet m_children;

    /**
     * <p>
     * Create a <tt>ITreeNode</tt> without a parent, user Object and
     * m_children. After this call, this instance will be the root node (no
     * parent).
     * </p>
     */
    public DefaultTreeNode() {
      this.m_children = new TreeSet();
      this.m_userObject = "root";
    }

    /**
     * <p>
     * Create a <tt>ITreeNode</tt> without a parent that carries the given
     * user Object.
     * </p>
     * 
     * @param userObject
     *          An Object that is desired to be stored in the node.
     */
    public DefaultTreeNode(final Object userObject) {
      this();
      this.m_userObject = userObject;
    }

    /**
     * Create a <tt>ITreeNode</tt> without a parent that carries the given
     * user Object and has the given <tt>ITreeNode</tt> as child.
     * <p>
     * 
     * @param userObject
     *          An Object that is desired to be stored in the node.
     * @param child
     *          The first child of this node.
     */
    public DefaultTreeNode(final Object userObject, final ITreeNode child) {
      this(userObject);
      this.addChildNode(child);
    }

    /**
     * <p>
     * Create a <tt>ITreeNode</tt> without a parent that carries the given
     * user Object and has the given <tt>ITreeNode</tt> instances as
     * m_children.
     * </p>
     * <p>
     * Perhaps the most useful constructor. It allows to construct trees in a
     * short overviewable way:
     * 
     * <pre>
     *        new DefaultTreeNode(
     *          new Integer(1),
     *          new ITreeNode[]{
     *            new DefaultTreeNode(
     *              new Integer(2)
     *            ),
     *            new DefaultTreeNode(
     *              new Integer(3),
     *              new DefaultTreeNode(
     *                new Integer
     *              )
     *            )
     *        );
     * </pre>
     * 
     * </p>
     * 
     * @param userObject
     *          An Object that is desired to be stored in the node.
     * 
     * @param children
     *          An array of <tt>ITreeNode</tt> instances that will become
     *          childs of this node.
     * 
     */
    public DefaultTreeNode(final Object userObject, final ITreeNode[] children) {
      this(userObject);
      for (int i = 0; i < children.length; i++) {
        this.addChildNode(children[i]);
      }
    }

    /**
     * The given <tt>Object</tt> will be stored in a newly created
     * <tt>ITreeNode</tt> which will get assigned this node to be it's father,
     * while this instance will store the new node in it's list
     * (unconditionally: e.g. no test, if already contained).
     * <p>
     * 
     * @return See the {@link ITreeNode}{interface}: Null may be returned in
     *         case of failure!!!
     * 
     * @see ITreeNode#addChild(Object)
     */
    public final ITreeNode addChild(final Object userObject) {
      ITreeNode ret = this.newInstance();
      ret.setUserObject(userObject);
      if (this.addChildNode(ret)) {
        return ret;
      } else {
        return ret.getParent();
      }
    }

    /**
     * The given <tt>ITreeNode</tt> will become this node to be it's father,
     * while this instance will store the given node in it's list if not null
     * (unconditionally: e.g. no test, if already contained).
     * <p>
     * 
     * @param node
     *          the node to add as child.
     * 
     * @see ITreeNode#addChildNode(ITreeNode)
     */
    public boolean addChildNode(final ITreeNode node) {
      if (node == null) {
        return false;
      }
      node.setParent(this);
      this.m_children.add(node);
      return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#contains(java.lang.Object)
     */
    public final boolean contains(Object userObject) {
      if ((this.m_userObject != null) && (this.m_userObject.equals(userObject))) {
        return true;
      } else {
        if (!this.isLeaf()) {
          Iterator it = this.m_children.iterator();
          while (it.hasNext()) {
            if (((ITreeNode) it.next()).contains(userObject))
              return true;
          }
          return false;
        }
        return false;
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#containsNode(aw.util.collections.ITreeNode)
     */
    public final boolean containsNode(ITreeNode node) {
      if (this.equals(node)) {
        return true;
      } else {
        if (!this.isLeaf()) {
          Iterator it = this.m_children.iterator();
          while (it.hasNext()) {
            if (((ITreeNode) it.next()).contains(node)) {
              return true;
            }
          }
          return false;
        }
        return false;
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#getChildCount()
     */
    public final int getChildCount() {
      return this.m_children.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#getChilds()
     */
    public final Iterator getChilds() {
      return this.m_children.iterator();
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#getParent()
     */
    public final ITreeNode getParent() {
      return (this.m_parent == null) ? ROOT : this.m_parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#getSubtreeCount()
     */
    public final int getSubtreeCount() {
      // hehehe: clever double-use of child detection and partial result...
      int ret = this.m_children.size();
      if (ret > 0) {
        Iterator it = this.m_children.iterator();
        while (it.hasNext()) {
          ret += ((ITreeNode) it.next()).getSubtreeCount();
        }

      }
      if (this.m_parent == ROOT) {
        ret++; // root has to count itself...
      }
      return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#getUserObject()
     */
    public final Object getUserObject() {
      return this.m_userObject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#mark()
     */
    public final void mark() {
      this.marked = true;
    }

    public final boolean isMarked() {
      return this.marked;
    }

    /**
     * <p>
     * The search is a "prefix-search":
     * 
     * <pre>
     *      
     *       
     *       
     *            A
     *           / \
     *          B   C
     *         / \
     *        D   E
     *       
     *        
     *       
     * </pre>
     * 
     * The search will be done in the order: <tt>A,B,D,E,C</tt>. If this
     * <tt>ITreeNode</tt> contains the user Object equal to the argument,
     * itself will be returned. This <tt>ITreeNode</tt> may be the root!
     * </p>
     * 
     * @see ITreeNode#remove(Object)
     */
    public final ITreeNode remove(final Object userObject) {
      ITreeNode ret = null;
      if ((this.m_userObject != null) && (this.m_userObject.equals(userObject))) {
        this.m_parent.removeChild(this);
        this.m_parent = null;
        ret = this;
      } else {
        if (!this.isLeaf()) {
          Iterator it = this.m_children.iterator();
          while (it.hasNext()) {
            ret = ((ITreeNode) it.next());
            if (ret != null) {
              break;
            }
          }
        } else
          return null;
      }
      return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#removeAllChilds()
     */
    public final List removeAllChildren() {
      SortedSet ret = this.m_children;
      Iterator it = ret.iterator();
      while (it.hasNext()) {
        ((ITreeNode) it.next()).setParent(null);
      }
      this.m_children = new TreeSet();
      return new LinkedList(ret);
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#removeChild(aw.util.collections.ITreeNode)
     */
    public boolean removeChild(ITreeNode node) {
      return this.m_children.remove(node);
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#setUserObject()
     */
    public final Object setUserObject(Object store) {
      Object ret = this.m_userObject;
      this.m_userObject = store;
      return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#unmark()
     */
    public final void unmark() {
      this.marked = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#setParent(aw.util.collections.ITreeNode)
     */
    public final void setParent(final ITreeNode parent) {
      if (this.m_parent != null) {
        // will call: node.setParent(null);
        this.m_parent.removeChild(this);
      }
      this.m_parent = parent;

    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#isLeaf()
     */
    public final boolean isLeaf() {
      return this.m_children.size() == 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#isRoot()
     */
    public final boolean isRoot() {
      return this.m_parent == null;
    }

    public String toString() {

      StringBuffer ret = new StringBuffer();
      this.toStringInternal(ret, 1);
      return ret.toString();
    }

    protected void toStringInternal(StringBuffer buf, int depth) {
      if (this.isLeaf()) {
        buf.append("-> ");
      }
      buf.append('(').append(String.valueOf(this.m_userObject)).append(')');
      StringBuffer spaceCollect = new StringBuffer();
      for (int i = depth; i > 0; i--) {
        spaceCollect.append("  ");
      }
      String indent = spaceCollect.toString();
      Iterator it = this.getChilds();
      while (it.hasNext()) {
        buf.append("\n").append(indent);
        ((DefaultTreeNode) it.next()).toStringInternal(buf, depth + 1);
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#addChildNodes(aw.util.collections.ITreeNode[])
     */
    public final boolean addChildNodes(ITreeNode[] nodes) {
      boolean ret = true;
      for (int i = 0; i < nodes.length; i++) {
        ret &= this.addChildNode(nodes[i]);
      }
      return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#addChildren(java.lang.Object[])
     */
    public final ITreeNode[] addChildren(Object[] userObjects) {
      List treeNodes = new LinkedList(); // can't know the size, as they might
      // contain null.
      ITreeNode newNode = null;
      for (int i = 0; i < userObjects.length; i++) {
        newNode = this.addChild(userObjects[i]);
        if (newNode != null) {
          treeNodes.add(newNode);
        }
      }

      return (ITreeNode[]) treeNodes.toArray(new ITreeNode[treeNodes.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#getAllChildren()
     */
    public final List getAllChildren() {
      return new LinkedList(this.m_children);
    }

    /*
     * (non-Javadoc)
     * 
     * @see aw.util.collections.ITreeNode#newInstance()
     */
    public ITreeNode newInstance() {
      return new DefaultTreeNode();
    }

    public void getPathFromRoot(List l) {
      if (this.isRoot()) {
        l.add(this);
      } else {
        this.getParent().getPathFromRoot(l);
        l.add(this);
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cpdetector.util.collections.ITreeNode#getUserObjectPathFromRoot(java.util.List)
     */
    public void getUserObjectPathFromRoot(List l) {
      List collect = new LinkedList();
      this.getPathFromRoot(collect);
      Iterator it = collect.iterator();
      while (it.hasNext()) {
        l.add(((ITreeNode) it.next()).getUserObject());
      }
    }

    public int compareTo(final ITreeNode o) throws ClassCastException {
      ITreeNode other = (ITreeNode) o;
      return ((Comparable) this.m_userObject).compareTo(other.getUserObject());
    }

  }
}

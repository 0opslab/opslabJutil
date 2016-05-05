/*
 * TreeNodeUniqueChildren.java , an implementation
 * of the ITreeNode interface allowing only unique nodes at a level.
 * Copyright (C) 2002  Achim Westermann, Achim.Westermann@gmx.de
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
 **/
package info.monitorenter.cpdetector.util.collections;

import java.util.Iterator;
import java.util.List;

/**
 * An {@link ITreeNode} implementation, that does
 * not allow equal m_children of one common parent node. Common elements in the
 * path from an arbitrary node (seen as the root) to different leaves will share
 * the same <tt>ITreeNode</tt> instances at runtime.
 * <p>
 * This behaviour may be used to create the smallest possible tree containing
 * all given serialized paths.
 * <p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
 */
public class TreeNodeUniqueChildren
    extends ITreeNode.DefaultTreeNode {

  /**
   * 
   */
  public TreeNodeUniqueChildren() {
    super();
  }

  /**
   * @param userObject
   */
  public TreeNodeUniqueChildren(final Object userObject) {
    super(userObject);
  }

  /**
   * @param userObject
   * @param child
   */
  public TreeNodeUniqueChildren(final Object userObject, final ITreeNode child) {
    super(userObject, child);
  }

  /**
   * @param userObject
   * @param children
   */
  public TreeNodeUniqueChildren(final Object userObject,final  ITreeNode[] children) {
    super(userObject, children);
  }

  /**
   * If the given argument is already a child node of this one (by the means of
   * the equals method), it will replace the old node but gets the childs of the
   * old node.
   * <p>
   * 
   * @see ITreeNode#addChildNode(ITreeNode)
   */
  public boolean addChildNode(final ITreeNode node) {
    boolean ret = true;
    if (node == null) {
      throw new IllegalArgumentException("Argument node is null!");
    }
    Object nodeObject = node.getUserObject();
    Object childObject = null;
    ITreeNode child = null;
    Iterator childIt = this.getChilds();
    while (childIt.hasNext()) {
      child = (ITreeNode) childIt.next();
      childObject = child.getUserObject();

      if (child.equals(node)) {
        // add all childs of nodeObject to child:
        List childChilds = child.getAllChildren();
        node.addChildNodes((ITreeNode[]) childChilds.toArray(new ITreeNode[childChilds.size()]));
        node.setParent(this);
        // childIt.remove() throws concurrentmod...
        this.removeChild(child);
        break;
      }
    }
    ret = super.addChildNode(node);
    return ret;
  }

  /**
   * 
   * <p>
   * Construction of a tree with the user Objects (java.lang.Integer) and use
   * the toString() method.
   * 
   * <pre>
   * 
   * 
   *              0
   *             /|\
   *            / | \
   *           1  2  1
   *          / \    |\
   *         /   \   | \
   *        4     5  6  7
   *                /|\
   *               / | \
   *              8  9  10
   * 
   *  </pre>
   *  As only unique nodes are supported, the paths have to be flattended to:
   *  <pre>
   * 
   *              0
   *             / \
   *            /   \
   *           1     2
   *          /|\
   *         / | \
   *        /  | |\
   *       /   | | \
   *      4    5 6  7
   *            /|\
   *           / | \
   *          8  9  10
   * 
   *  </pre>
   * 
   * 
   * 
   *
   */
  public static void main(String[] args) throws Exception {
    StringBuffer prettyPrint = new StringBuffer();
    prettyPrint.append("             0\n");
    prettyPrint.append("            /|\\\n");
    prettyPrint.append("           / | \\\n");
    prettyPrint.append("          1  2  1\n");
    prettyPrint.append("         / \\    |\\ \n");
    prettyPrint.append("        /   \\   | \\ \n");
    prettyPrint.append("      4     5   6  7 \n");
    prettyPrint.append("               /|\\ \n");
    prettyPrint.append("              / | \\ \n");
    prettyPrint.append("             8  9  10 \n");

    System.out.println("Constructing tree:\n" + prettyPrint.toString());

    prettyPrint.delete(0, prettyPrint.length());
    prettyPrint.append("             0 \n");
    prettyPrint.append("            / \\ \n");
    prettyPrint.append("           /   \\ \n");
    prettyPrint.append("          1     2 \n");
    prettyPrint.append("         /|\\  \n");
    prettyPrint.append("        / | \\ \n");
    prettyPrint.append("       /  | |\\ \n");
    prettyPrint.append("      /   | | \\ \n");
    prettyPrint.append("     4    5 6  7 \n");
    prettyPrint.append("           /|\\ \n");
    prettyPrint.append("          / | \\ \n");
    prettyPrint.append("         8  9  10 \n");

    System.out.println("Assuming tree:\n" + prettyPrint.toString());

    ITreeNode root = new TreeNodeUniqueChildren(new Integer(0), new ITreeNode[] {
        new DefaultTreeNode(new Integer(1), new ITreeNode[] {new DefaultTreeNode(new Integer(4)),
            new DefaultTreeNode(new Integer(5)) }),
        new DefaultTreeNode(new Integer(2)),
        new DefaultTreeNode(new Integer(1), new ITreeNode[] {
            new DefaultTreeNode(new Integer(6), new ITreeNode[] {
                new DefaultTreeNode(new Integer(8)), new DefaultTreeNode(new Integer(9)),
                new DefaultTreeNode(new Integer(10)) }), new DefaultTreeNode(new Integer(7)) })

    });
    System.out.println("The tree:");
    System.out.println(root.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see aw.util.collections.ITreeNode#newInstance()
   */
  public ITreeNode newInstance() {
    return new TreeNodeUniqueChildren();
  }

}
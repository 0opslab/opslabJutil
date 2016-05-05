/*
 *  FitAuthoringClient of project cpdetector, 
 *  support for creation of fit-based tests for cpdetector. 
 *  Copyright (C) 2005 Achim Westermann
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
 */package info.monitorenter.cpdetector.test.ui;

import info.monitorenter.cpdetector.io.ClassFileFilterIsA;
import info.monitorenter.cpdetector.test.ui.ClassFileChooser.URLFileSystemView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class FitAuthoringClient extends JFrame implements ActionListener{

  
  public FitAuthoringClient() {

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    // The menu bar
    JMenuBar bar = new JMenuBar();

    //  Build the first menu.
    JMenu menu = new JMenu("File");
    menu.setMnemonic(KeyEvent.VK_A);
    menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");

    //  a group of JMenuItems
    JMenuItem menuItem = new JMenuItem("A text-only menu item");
    //menuItem.addActionListener(this);
    menuItem.setAction(new ActionLoadFixture("ActionName","Demo Action",KeyEvent.VK_0));
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.ALT_MASK));
    menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");

    menu.add(menuItem);
    bar.add(menu);
    this.setJMenuBar(bar);

  }
  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e) {
    System.out.println(this.getClass().getName() + ".actionPerformed()");
  }
  /**
   * <p>
   * This action opens a filechooser an requests a Fixture classfile that is 
   * instantiated and assigned to the client.
   * </p>
   * <p>
   * The given classfile has to be in the classpath, the filechooser will 
   * filter out every classfile that is a subclass of {@link fit.ColumnFixture}.
   * </p>
   * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
   *
   */
  class ActionLoadFixture extends AbstractAction {
    
    /**
     * @param name
     */
    public ActionLoadFixture(String name,String description,int mnemonic) {
      super(name);
      putValue(SHORT_DESCRIPTION, description);
      putValue(MNEMONIC_KEY, new Integer(mnemonic));
    }
    
    public void actionPerformed(ActionEvent e) {
      System.out.println(this.getClass().getName() + ".actionPerformed()");
      ClassFileChooser chooser = new ClassFileChooser(new URLFileSystemView());
      ClassFileFilterIsA filter = new ClassFileFilterIsA();
      //TODO: comment in the filter for fit client.
      //filter.addSuperClass(ColumnFixture.class);
      filter.addSuperClass(Throwable.class);
      chooser.addClassFileFilter(filter);
      chooser.showOpenDialog(FitAuthoringClient.this);
    }
  }

  public static void main(String[] args) {
    JFrame frame = new FitAuthoringClient();
    frame.setSize(new Dimension(800, 600));
    frame.setVisible(true);
  }
  
}
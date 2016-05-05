/*
 *  TableRendererHTML.java  
 *  Copyright (C) 2004 Achim Westermann, created on 21.10.2004, 23:18:10  
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
package info.monitorenter.cpdetector.util.collections.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Only one instance per call to render, or output will be appended. 
 * No time...
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 *
 */
public class TableRendererHTML extends StreamTableRenderer{
 
  public TableRendererHTML(Writer out){
    super(out);
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#CellStartTag(boolean)
   */
  protected String CellStartTag(boolean rowstart) {
    return "<TD>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#CellStopTag(boolean)
   */
  protected String CellStopTag(boolean rowend) {
    return "</TD>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#HeadCellStartTag(boolean)
   */
  protected String HeadCellStartTag(boolean firstOrLast) {
    return "<TH>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#HeadCellStopTag(boolean)
   */
  protected String HeadCellStopTag(boolean firstOrLast) {
    return "</TH>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#HeadRowStartTag()
   */
  protected String HeadRowStartTag() {
    return "<TR>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#HeadRowStopTag()
   */
  protected String HeadRowStopTag() {
    return "</TR>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#RowStartTag()
   */
  protected String RowStartTag() {
    return "<TR>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#RowStopTag()
   */
  protected String RowStopTag() {
    return "</TR>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#TableStartTag()
   */
  protected String TableStartTag() {
    return "<TABLE>";
  }
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.StreamTableRenderer#TableStopTag()
   */
  protected String TableStopTag() {
    return "</TABLE>";
  }
  
  public static void main(String[]args)throws Throwable{
    TableModel model = new DefaultTableModel(new String[]{"One","Two","Three"},10);
    Writer out = new OutputStreamWriter(new FileOutputStream(new File("test.html")));
    for(int rows=0;rows<10;rows++){
      for(int cols = 0;cols<3;cols++){
        model.setValueAt("Test("+rows+","+cols+")",rows,cols);
      }
    }
    new TableRendererHTML(out).render(model);
    out.close();
    
  }
}

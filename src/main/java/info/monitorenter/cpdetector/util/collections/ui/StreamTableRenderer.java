/*
 *  StreamTableRenderer.java  
 *  Copyright (c) 2007 Achim Westermann, created on 21.10.2004, 23:22:23  
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

import java.io.IOException;
import java.io.Writer;

import javax.swing.table.TableModel;

/**
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 *
 */
public abstract class StreamTableRenderer implements ITableRenderer {

  final static int FIRST_CELL_IN_ROW = 0;
  final static int LAST_CELL_IN_ROW = 1;
  
  protected Writer m_out; 
  public StreamTableRenderer(Writer out){
    this.m_out = out;
  }
  
  protected final void renderHeader(TableModel model) throws IOException{
    int cols = model.getColumnCount();
    for(int i=0;i<cols;i++){
      this.renderHeaderCell(model.getColumnName(i),(i==0)?FIRST_CELL_IN_ROW:(i==cols-1)?LAST_CELL_IN_ROW:2);
    }
  }

  /**
   * @throws IOException
   * 
   */
  private final void renderHeaderCell(String columnName, int firstOrLast) throws IOException {
    m_out.write(this.HeadCellStartTag((firstOrLast==FIRST_CELL_IN_ROW)));
    m_out.write(columnName);
    m_out.write(this.HeadCellStopTag((firstOrLast==LAST_CELL_IN_ROW)));
  }

  protected abstract String HeadRowStartTag();
  protected abstract String HeadRowStopTag();
  
  protected abstract String HeadCellStopTag(boolean firstOrLast);

  protected abstract String HeadCellStartTag(boolean firstOrLast);

  protected final void renderRow(TableModel model, int row) throws IOException{
    m_out.write(RowStartTag());
    int cols = model.getColumnCount();
    for(int i=0;i<cols;i++){
      this.renderCell(model.getValueAt(row,i),(i==0)?FIRST_CELL_IN_ROW:(i==cols-1)?LAST_CELL_IN_ROW:2);
    }
    m_out.write(RowStopTag());
  }
  
  protected abstract String RowStartTag();
  protected abstract String RowStopTag();

  protected final void renderCell(Object content,int firstOrLast) throws IOException{
    m_out.write(this.CellStartTag((firstOrLast==FIRST_CELL_IN_ROW)));
    if(content instanceof TableModel){
      this.render((TableModel)content);
    }
    else{
      m_out.write(content.toString());
    }
    m_out.write(this.CellStopTag((firstOrLast==LAST_CELL_IN_ROW)));
  }
  
  protected abstract String CellStartTag(boolean rowstart);
  protected abstract String CellStopTag(boolean rowend);
 
  /* (non-Javadoc)
   * @see cpdetector.util.collections.ui.ITableRenderer#render(javax.swing.table.TableModel)
   */
  public final void render(TableModel model) throws IOException {
    this.m_out.write(this.TableStartTag());
    int rows = model.getRowCount();
    // write header
    m_out.write(this.HeadRowStartTag());
    this.renderHeader(model);
    m_out.write(this.HeadRowStopTag());

    for(int i=0;i<rows;i++){
      this.renderRow(model,i);
    }
    this.m_out.write(this.TableStopTag());
  }
  
  protected abstract String TableStartTag();
  protected abstract String TableStopTag();
}

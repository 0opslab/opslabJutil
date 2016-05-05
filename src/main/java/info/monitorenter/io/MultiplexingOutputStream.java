/*
 * MultiplexingOutputStream.java, an OutputStream that allows to multiplex everything that
 * is written to it to all contained output streams (multiplexing).
 *
 * Copyright 2011 (C) Achim Westermann, 
 * created on Nov 27, 2011 6:07:13 PM.
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
 * ***** END LICENSE BLOCK *****
 *
 * If you modify or optimize the code in a useful way please let me know.
 * Achim.Westermann@gmx.de
 *
 */
package info.monitorenter.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * An <code>{@link OutputStream}</code> that allows to multiplex everything that
 * is written to it to all contained output streams (multiplexing).
 * <p>
 * This is not optimized for performance (by supporting batch writes) but just
 * minimal code by now.
 * <p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 * @version $Revision: 1.3 $
 */
public class MultiplexingOutputStream extends OutputStream {
  /** The multiplex sources. */
  private List<OutputStream> m_delegates;

  /**
   * Constructor ensuring that at least two targets are needed (thus use of this
   * class makes sense).
   * <p>
   * 
   * @param streamOne
   *          the 1st stream to delegate writes to.
   * @param streamTwo
   *          the 2nd stream to delegate writes to.
   */
  public MultiplexingOutputStream(final OutputStream streamOne, final OutputStream streamTwo) {
    this.m_delegates = new LinkedList<OutputStream>();
    this.m_delegates.add(streamOne);
    this.m_delegates.add(streamTwo);
  }

  /**
   * Adds the given output stream to the list of delegates multiplexed to.
   * 
   * @param delegate
   *          another stream that will receive any write that is done on this
   *          instance.
   */
  public void addOutputStream(final OutputStream delegate) {
    this.m_delegates.add(delegate);
  }

  /**
   * Removes the given output stream from the list of delegates multiplexed to.
   * 
   * @param delegate
   *          a stream that should not receive any write that is done on this
   *          instance any more.
   * @return true if remove was successful, false if remove failed or - more
   *         likely - the given stream was not on the list of delegates before.
   */
  public boolean removeOutputStream(final OutputStream delegate) {
    boolean result = this.m_delegates.remove(delegate);
    return result;
  }

  /**
   * @see OutputStream#write(int)
   */
  @Override
  public void write(int b) throws IOException {
    for (OutputStream delegate : this.m_delegates) {
      delegate.write(b);
    }
  }
}

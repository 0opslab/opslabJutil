/*
 *  ExceptionUtil, utility class for exceptions.
 *  Copyright (C) 2004 - 2011 Achim Westermann.
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 * 
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *  If you modify or optimize the code in a useful way please let me know.
 *  Achim.Westermann@gmx.de
 *
 */
package info.monitorenter.util;

import info.monitorenter.io.MultiplexingOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * Nice static helpers for working with Strings.
 * <p>
 * Maybe not always the fastest solution to call in here, but working. Also usable for seeing
 * examples and cutting code for manual inlining.
 * <p>
 * 
 * @author Achim.Westermann@gmx.de
 * @version $Revision: 1.1 $
 */
public final class ExceptionUtil {

  /** Singleton instance. */
  private static ExceptionUtil instance = null;

  /**
   * Returns an input stream that contains what will written in this Application to
   * {@link System#err}.
   * <p>
   * <b>Caution</b><br/>
   * If you do not consume the bytes to read from the result you may block the whole application. Do
   * only use this for debugging purposes or end to end test code!
   * <p>
   * Attempting to read from the result in the same thread that called here is not recommended as it
   * may deadlock the thread. Also the thread reading from the stream result should not write
   * anything to {@link System#err}.
   * <p>
   * Prefer using {@link #findMatchInSystemErr(String)} to avoid deadlocks.
   * <p>
   * 
   * @param teeToOriginalSysErr
   *          if true, all writes to the newly installed {@link System#err} will also sent to the
   *          original one (thus be visible on the console if the original one was untouched
   *          before).
   * @return an input stream that contains what will written in this Application to
   *         {@link System#err}.
   * @throws IOException
   *           if something goes wrong.
   */
  public static InputStream captureSystemErrForDebuggingPurposesOnly(
      final boolean teeToOriginalSysErr) throws IOException {
    PipedOutputStream pipeOut = new PipedOutputStream();
    PipedInputStream pipeIn = new PipedInputStream(pipeOut);
    OutputStream out = pipeOut;

    if (teeToOriginalSysErr) {
      out = new MultiplexingOutputStream(System.err, pipeOut);
    }
    PrintStream streamOut = new PrintStream(out);

    System.setErr(streamOut);
    return pipeIn;
  }

  /**
   * Returns an input stream that contains what will written in this Application to
   * {@link System#out}.
   * <p>
   * <b>Caution</b><br/>
   * If you do not consume the bytes to read from the result you may block the whole application. Do
   * only use this for debugging purposes or end to end test code!
   * <p>
   * Attempting to read from the result in the same thread that called here is not recommended as it
   * may deadlock the thread. Also the thread reading from the stream result should not write
   * anything to {@link System#out}.
   * <p>
   * Prefer using {@link #findMatchInSystemOut(String)} to avoid deadlocks.
   * <p>
   * 
   * @param teeToOriginalSysOut
   *          if true, all writes to the newly installed {@link System#out} will also sent to the
   *          original one (thus be visible on the console if the original one was untouched
   *          before).
   * @return an input stream that contains what will written in this Application to
   *         {@link System#out}.
   * @throws IOException
   *           if something goes wrong.
   */
  public static InputStream captureSystemOutForDebuggingPurposesOnly(
      final boolean teeToOriginalSysOut) throws IOException {
    PipedOutputStream pipeOut = new PipedOutputStream();
    PipedInputStream pipeIn = new PipedInputStream(pipeOut);
    OutputStream out = pipeOut;
    if (teeToOriginalSysOut) {
      out = new MultiplexingOutputStream(System.out, pipeOut);
    }
    PrintStream streamOut = new PrintStream(out);
    System.setOut(streamOut);
    return pipeIn;
  }

  /**
   * Returns an instance to a runnable running in a separate Thread that tries to match the expected
   * output in {@link System#out}.
   * <p>
   * Ensure that you found the expected String in the input stream given by calling
   * {@link InputStreamTracer#isMatched()}. But take into account that it is time -
   * critical (concurrency) if your result was found.
   * <p>
   * Prefer this instead of {@link #captureSystemOutForDebuggingPurposesOnly(boolean)} as this will
   * avoid blocking your application.
   * <p>
   * 
   * @return an instance to a runnable running in a separate Thread that tries to match the expected
   *         output in {@link System#out}.
   * @throws IOException
   *           if something goes wrong.
   */
  public static InputStreamTracer findMatchInSystemOut(final String expectMatch) throws IOException {
    InputStream systemout = captureSystemOutForDebuggingPurposesOnly(true);
    InputStreamTracer result = new InputStreamTracer(systemout, expectMatch,
        Charset.defaultCharset());
    Thread traceThread = new Thread(result);
    traceThread.setDaemon(true);
    traceThread.start();
    return result;
  }

  /**
   * Returns an instance to a runnable running in a separate Thread that tries to match the expected
   * output in {@link System#err}.
   * <p>
   * Ensure that you found the expected String in the input stream given by calling
   * {@link InputStreamTracer#isMatched()}. But take into account that it is time -
   * critical (concurrency) if your result was found.
   * <p>
   * Prefer this instead of {@link #captureSystemErrForDebuggingPurposesOnly(boolean)} as this will
   * avoid blocking your application.
   * <p>
   * 
   * @return an instance to a runnable running in a separate Thread that tries to match the expected
   *         output in {@link System#out}.
   * @throws IOException
   *           if something goes wrong.
   */
  public static InputStreamTracer findMatchInSystemErr(final String expectMatch) throws IOException {
    InputStream systemout = captureSystemErrForDebuggingPurposesOnly(true);
    InputStreamTracer result = new InputStreamTracer(systemout, expectMatch,
        Charset.defaultCharset());
    Thread traceThread = new Thread(result);
    traceThread.setDaemon(true);
    traceThread.start();
    return result;
  }

  /**
   * Helper runnable to find a String within an output stream.
   * <p>
   * This should be run in a separate thread.
   * <p>
   * Ensure that you found the expected String in the input stream given by calling
   * {@link InputStreamTracer#isMatched()}. But take into account that it is time -
   * critical (concurrency) if your result was found.
   * <p>
   * 
   * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
   * @version $Revision: 1.1 $
   */
  public static class InputStreamTracer implements Runnable {

    /** The input stream to search for occurrence of word. */
    private InputStream m_streamToTrace;

    /** The string that is tried to be matched. */
    private String m_match;

    /** The encoding of the input stream to use for detecting the match. */
    private Charset m_charset;

    /** If true the output was matched. */
    private boolean m_matched;

    /**
     * Returns true if the expected String was matched in the input stream.
     * <p>
     * Note that it may be time - critical when to call this and take for granted that the match was
     * not made in the input stream (concurrency).
     * <p>
     * 
     * @return true if the expected String was matched in the input stream.
     */
    public boolean isMatched() {
      return this.m_matched;
    }

    public InputStreamTracer(final InputStream toTrace, final String match, final Charset charset) {
      this.m_streamToTrace = toTrace;
      this.m_match = match;
      this.m_charset = charset;
    }

    /**
     * @see Runnable#run()
     */
    public void run() {

      BufferedReader reader = new BufferedReader(new InputStreamReader(this.m_streamToTrace,
          this.m_charset));
      String line;
      try {
        do {
          line = reader.readLine();
          if (line != null && line.contains(this.m_match)) {
            this.m_matched = true;
            break;
          }
        } while (line != null);
      } catch (IOException ioex) {
        throw new RuntimeException(ioex);
      }
    }

  }

  /**
   * Prints out the current Thread stack to the given stream.
   * <p>
   * 
   * @see Thread#getStackTrace()
   * @param outprint
   *          the stream to print to (e.g. <code>{@link System#err}</code>).
   */
  public static void dumpThreadStack(PrintStream outprint) {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    String stackTraceString = StringUtil.arrayToString(stackTrace, "\n");
    outprint.println(stackTraceString);
  }

  /**
   * Returns the singleton instance of this class.
   * <p>
   * This method is useless for now as all methods are static. It may be used in future if VM-global
   * configuration will be put to the state of the instance.
   * <p>
   * 
   * @return the singleton instance of this class.
   */
  public static ExceptionUtil instance() {
    if (ExceptionUtil.instance == null) {
      ExceptionUtil.instance = new ExceptionUtil();
    }
    return ExceptionUtil.instance;
  }

}

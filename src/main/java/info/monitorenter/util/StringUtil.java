/*
 * StringUtil.java, helpers for strings. 
 * Copyright (C) 2001 Achim Westermann
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
 * Achim.Westermann@gmx.de * 
 */package info.monitorenter.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

/**
 * Nice static helpers for working with Strings.
 * <p>
 * Maybe not always the fastest solution to call in here, but working. Also
 * usable for seeing examples and cutting code for manual inlining.
 * <p>
 * 
 * @author Achim.Westermann@gmx.de
 * 
 * @version $Revision: 1.3 $
 */
public final class StringUtil {
  
  /** Singleton instance. */
  private static StringUtil instance = null;

  /**
   * Appends the given amount of spaces to the String.
   * <p>
   * 
   * Not intended for big append -operations because in a loop alway just one
   * space is added.
   * <p>
   * 
   * @param s
   *          the base String to append spaces to.
   * 
   * @param count
   *          the amount of spaces to append.
   * 
   * @return a String consisting of s and count trailing whitespaces.
   */
  public static final String appendSpaces(final String s, final int count) {
    StringBuffer tmp = new StringBuffer(s);
    for (int i = 0; i < count; i++) {
      tmp.append(" ");
    }
    return tmp.toString();
  }

  /**
   * If the given Object is no Array, it's toString - method is invoked.
   * Primitive type - Arrays and Object - Arrays are introspected using
   * java.lang.reflect.Array. Convention for creation fo String -
   * representation: <p>
   * 
   * @see StringUtil#arrayToString(Object, String)
   * 
   * @param isArr
   *          The Array to represent as String.
   * 
   * @return a String-representation of the Object.
   * 
   * 
   */
  public static final String arrayToString(final Object isArr) {
    String result = StringUtil.arrayToString(isArr, ",");
    return result;
  }

  /**
   * If the given Object is no Array, it's toString - method is invoked.
   * Primitive type - Arrays and Object - Arrays are introspected using
   * java.lang.reflect.Array. Convention for creation for String -
   * representation: <br>
   * 
   * <code>
   * // Primitive arrays: 
   * &quot;[&quot;+isArr[0]+&quot;&lt;separator&gt;&quot;+isArr[1]+.. ..+isArr[isArr.length-1]+&quot;]&quot;
   *     
   *     
   * //Object arrays :  
   * &quot;[&quot;+isArr[0].toString()+&quot;&lt;separator&gt;&quot;+.. ..+isArr[isArr.length-1].toString+&quot;]&quot;
   * // Two or three - dimensional Arrays are not supported
   * //(should be reflected in a special output method, e.g.as a field)
   * 
   * // other Objects:    
   * toString()
   * </code>
   * 
   * @param separator put in-between each array element in the resulting string. 
   * 
   * @param isArr
   *          The Array to represent as String.
   * 
   * @return a String-representation of the Object.
   * 
   * 
   */
  public static final String arrayToString(final Object isArr, final String separator) {
    String result;
    if (isArr == null) {
      result = "null";
    } else {
      Object element;
      StringBuffer tmp = new StringBuffer();
      try {
        int length = Array.getLength(isArr);
        tmp.append("[");
        for (int i = 0; i < length; i++) {
          element = Array.get(isArr, i);
          if (element == null) {
            tmp.append("null");
          } else {
            tmp.append(element.toString());
          }
          if (i < length - 1) {
            tmp.append(separator);
          }
        }
        tmp.append("]");
        result = tmp.toString();
      } catch (ArrayIndexOutOfBoundsException bound) {
        // programming mistake or bad Array.getLength(obj).
        tmp.append("]");
        result = tmp.toString();

      } catch (IllegalArgumentException noarr) {
        result = isArr.toString();
      }
    }
    return result;
  }
  
  /**
   * Returns the system - dependent line separator.
   * <p>
   * 
   * Only call this method once (not in a loop) and keep the result.
   * <p>
   * 
   * @return the system - dependent line separator.
   */
  public static String getNewLine() {
    return  System.getProperty("line.separator");
  }

  /**
   * Returns the singleton instance of this class.
   * <p>
   * 
   * This method is useless for now as all methods are static. It may be used in
   * future if VM-global configuration will be put to the state of the instance.
   * <p>
   * #
   * 
   * @return the singleton instance of this class.
   */
  public static StringUtil instance() {
    if (StringUtil.instance == null) {
      StringUtil.instance = new StringUtil();
    }
    return StringUtil.instance;
  }

  /**
   * Returns true if the argument is null or consists of whitespaces only.
   * <p>
   * 
   * @param test
   *          the <code>String</code> to test.
   * 
   * @return true if the argument is null or consists of whitespaces only.
   */
  public static boolean isEmpty(final String test) {
    boolean result;
    if (test == null) {
      result = true;
    } else {
      result = test.trim().length() == 0;
    }
    return result;
  }

  /**
   * Little String output - helper that modifies the given LinkedList by getting
   * it's Objects and replace them by their toString() - representation.
   * <p>
   * 
   * What is special? <br>
   * If an Object in the given List is an Array (of Objects or primitive data
   * types) reflection will be used to create a String - representation of them.
   * The changes are reflected in the Objects that are in the given List. So
   * keep a reference to it. If you are sure, that your List does not contain
   * Arrays do not use this method to avoid overhead.
   * <p>
   * 
   * Avoid structural modifications (remove) of the list while using this
   * method. This method or better: the given List is only thread - safe if the
   * list is synchronized.
   * <p>
   * 
   * A clever VM (hotspot) will be able to inline this function because of void
   * return.
   * <p>
   * 
   * @param objects
   *          the List of objects that will be changed to a list of the String
   *          representation of the Objects with respect to special array
   *          treatment.
   * 
   */
  public static final void listOfArraysToString(final List<Object> objects) {
    if (objects == null) {
      return;
    }
    int stop = objects.size();
    for (int i = 0; i < stop; i++) {
      objects.add(i, StringUtil.arrayToString(objects.remove(i)));
    }
  }

  /**
   * Returns the maximum length of a {@link Object#toString()} result in
   * characters within the given List.
   * <p>
   * 
   * No data is changed in the given List at all. But the String -
   * representation of all Objects, even Arrays is build to inspect. <br>
   * Convention for creation fo String - representation: <br>
   * 
   * <pre>
   *        Primitive Arrays : as performed by this classes @see #ArrayToString.
   *        Object Arrays    : as performed by this classes @see #ArrayToString
   *        other Objects    : toString()  (as performed by this classes @see #ArrayToString).
   * </pre>
   * 
   * @param objects
   *          the <code>List&lt;Object&gt;</code> to inspect for the maximum
   *          length of a {@link Object#toString()} result.
   * 
   * @return The length of the longest String - representation of an Object in
   *         the List <b>or 0 if objects was null or of size 0. </b>
   */
  public static final int longestStringRepresentation(final List<Object> objects) {
    int result;
    if (objects == null) {
      result = 0;
    } else {
      int maxsize = 0;
      int tint = 0;
      String tmp;
      int stop = objects.size();
      for (int i = 0; i < stop; i++) {
        tmp = StringUtil.arrayToString(objects.get(i));
        tint = tmp.length();
        if (tint > maxsize) {
          maxsize = tint;
        }
      }
      // maximum size known.
      result = maxsize;
    }
    return result;
  }
 
  /**
   * This may be useful for comparison of canonical paths. For two strings a,b the prefix
   * intersection of a and b is returned on the key side and the tail of b that did not match on the
   * value side. <br>
   * Example:
   * <table border="1">
   * <tr>
   * <th>a</th>
   * <th>b</th>
   * <th>.getKey()</th>
   * <th>.getValue()</th>
   * </tr>
   * <tr>
   * <td>computer</td>
   * <td>compare</td>
   * <td>comp</td>
   * <td>are</td>
   * </tr>
   * <tr>
   * <td>compare</td>
   * <td>computer</td>
   * <td>comp</td>
   * <td>uter</td>
   * </tr>
   * <tr>
   * <td>computer</td>
   * <td>put</td>
   * <td>""</td>
   * <td>""</td>
   * </tr>
   * <tr>
   * <td>c:/work/myproject/bin/</td>
   * <td>c:/work/myproject/bin/cpdetector/io/ClassFileFilter.class</td>
   * <td>c:/work/myproject/bin/</td>
   * <td>cpdetector/io/ClassFileFilter.class</td>
   * </tr>
   * <tr>
   * <td>c:/work/myproject/bin/cpdetector/io/ClassFileFilter.class</td>
   * <td>c:/work/myproject/bin/</td>
   * <td>c:/work/myproject/bin/</td>
   * <td>""</td>
   * </tr>
   * </table>
   * Note that the prefix is not searched within the strings. Both strings have to have a common
   * prefix (no skipping until identical parts are found).
   */
  public static Map.Entry<String, String> prefixIntersection(String a, String b) {
    Map.Entry<String,String> ret = null;
    String key;
    String value;
    int index = b.indexOf(a);
    if (index != -1) {
      key = a;
      value = b.substring(a.length());
    } else {
      key = "";
      value = "";
    }
    ret = new Entry<String,String>(key, value);
    return ret;
  }

  /**
   * Appends the necessary amount of spaces to the string until it has the givn
   * length. No Exception is thrown, if the length of the String is shorter than
   * the given length, but nothing will happen and a message will be printed to
   * the System.out.
   * 
   * 
   * @param s
   *          the String to expand.
   * @param length
   *          the desired length of the String to be returned.
   * @return A String that represents the content of the old String including
   *         extra whitespaces.
   */
  public static final String setSize(final String s, final int length) {
    String result = s;
    int oldlen = s.length();
    if (oldlen > length) {
      System.err.println("greenpeace.util.setSize(String s,int length): length (" + length
          + ") is smaller than s.length(" + oldlen + ") : " + s);
    } else {
      int tofill = length - oldlen;
      result = StringUtil.appendSpaces(s, tofill);
    }
    return result;
  }
 
  /**
   * Modifies the given LinkedList by getting it's Objects and replace them by
   * their toString() - representation concatenated with the necessary amount of
   * white spaces that every String in the List will have the same amount of
   * characters.
   * <p>
   * 
   * Only use this method in following case: <br>
   * <ul>
   * <li>You have got an AbstractList or subclass containing Objects you do not
   * know.</li>
   * <li>You want to transform all Elements in the List to Strings.</li>
   * <li>There might be Array's in the Object (whose toString method will return
   * only their hashcode).</li>
   * </ul>
   * <p>
   * 
   * What happens?
   * <ul>
   * <li>All Objects, even primitive Arrays in your List will be turned to
   * String- representation.</li>
   * <li>The result will replace the old position of the Object in the given
   * List. The old Objects will get lost!</li>
   * <li>All Strings will be filled with whitespaces until each String has the
   * same length.</li>
   * </ul>
   * At least this method will be speeded up by a hotspot VM by inlining this
   * method. <br>
   * <i>An example: <br>
   * You just retrieved data from a db using jdbc and a generic class
   * (resultset.getObject()). You want to print the data to System.out or to
   * save it to a file, but do not know, if Arrays are contained. You want the
   * output to be formatted (each line). </i>
   * <p>
   * 
   * @param objects
   *          contains the Objects to replace by their toString()
   *          representation.
   */
  public static final void toLongestString(final List<Object> objects) {
    if (objects == null) {
      return;
    }
    int maxsize = 0;
    int tint = 0;
    String tmp;
    int stop = objects.size();
    for (int i = 0; i < stop; i++) {
      StringUtil.arrayToString(objects.get(i));
      tmp = (String) objects.get(i);
      tint = tmp.length();
      if (tint > maxsize) {
        maxsize = tint;
      }
      objects.add(i, tmp);
    }
    // maximum size known.
    for (int i = 0; i < stop; i++) {
      objects.add(i, StringUtil.setSize((String) objects.remove(i), maxsize));
    }
  }
  /**
   * Avoids creation from outside for singleton support.
   * <p>
   * 
   */
  private StringUtil() {
    // nop
  }
}
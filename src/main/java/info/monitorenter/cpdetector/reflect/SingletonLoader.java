/*
 *  SingletonLoader.java a dynamic class instantiation algorithm 
 *  with singleton instantiation support. 
 * 
 *  Copyright (C) Achim Westermann, created on 20.07.2004, 11:14:49  
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
package info.monitorenter.cpdetector.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * <p>
 * A stateless helper that performs dynamic class loading and instantiation with support for invoking
 * Singleton-retrieval methods of classes if detected.
 * </p>
 * <p>
 * Before invoking <code>someClass.newInstance()</code> the loaded class is inspected for methods, that are public,
 * static, parameterless, contain the substring "intance" in their name (case-insensitive) and return an instance of the
 * same type.
 * </p>
 * <p>
 * If such a method is found, it is considered to be a singleton retrieval method and will be invoked instead of trying
 * to use the default constructor triggered by <code>newInstance()</code> (which then should be private if it is
 * really a singleton.
 * </p>
 * <p>
 * This class is in turn a singleton as it only contains procedural code. For possible desireable future configuration
 * requirements, the single public method is not declared static but bound to the singleton instance.
 * </p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 * 
 */
public final class SingletonLoader {

    private static SingletonLoader instance = null;

    private Object[] dummyParameters = new Object[0];

    /**
     * Singleton retrieval
     */
    private SingletonLoader() {
        super();
    }

    public static SingletonLoader getInstance() {
        if (instance == null) {
            instance = new SingletonLoader();
        }
        return instance;
    }

    /**
     * High-level method for instantiation of a new instance of the class specified by the given fully qualified class
     * name with singleton retrieval support. Delegates to {@link #newInstance(Class)}.
     * 
     */
    public Object newInstance(String fullyQualifiedClassName) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        Object ret = this.newInstance(Class.forName(fullyQualifiedClassName));
        return ret;
    }

    /**
     * Dynamic instantiation of the given type with singleton retrieval support as described in this class description.
     * 
     */
    public Object newInstance(Class c) throws InstantiationException, IllegalAccessException {
        Object ret = null;
        Method[] methods = c.getDeclaredMethods();
        Method m;
        int modifiers;
        // searching for static methods:
        for (int i = 0; i < methods.length; i++) {
            m = methods[i];
            modifiers = m.getModifiers();
            if ((modifiers & Modifier.STATIC) != 0) {
                // searching for public access:
                if ((modifiers & Modifier.PUBLIC) != 0) {
                    // searching for no parameters:
                    if (m.getParameterTypes().length == 0) {
                        // searching for return type:
                        if (m.getReturnType() == c) {
                            // searching for substring "instance" in method name:
                            if (m.getName().toLowerCase().indexOf("instance") != -1) {
                                try {
                                    // Finally we found a singleton method:
                                    // we are static and don't need an instance.
                                    ret = m.invoke(null, dummyParameters);
                                } catch (IllegalArgumentException e) {
                                    // This will not happen:
                                    // we ensured that no arguments are needed.
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    // This will not happen (only in applet context perhaps or with some
                                    // SecurityManager):
                                    // we ensured public access.
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        // check, if we found a singleton:
        if (ret == null) {
            // try to invoke the default constructor:
            Constructor[] constructors = c.getConstructors();
            Constructor con = null;
            // search for a parameterless constructor:
            for (int i = 0; i < constructors.length; i++) {
                con = constructors[i];
                if (con.getParameterTypes().length == 0) {
                    // see, if public:
                    modifiers = con.getModifiers();
                    try {
                        if ((modifiers & Modifier.PUBLIC) == 0) {
                            // try to set accessible:
                            con.setAccessible(true);
                        }
                        // invokes the default constructor
                        ret = c.newInstance();
                    } catch (SecurityException se) {
                        // damn
                    }
                }
            }
        }
        if (ret == null) {
            System.err.println("Unable to instantiate: " + c.getName()
                    + ": no singleton method, no public default constructor.");
        }
        return ret;
    }

}

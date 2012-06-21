/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  copyright (C) 2012 nambi sankaran.
 */

package org.runway.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringDependencyInjectionServlet extends HttpServlet {

	private static final long serialVersionUID = 8224671450280967474L;

	private static final Logger logger = Logger.getLogger(SpringDependencyInjectionServlet.class.getName());


    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        Enumeration<?> attributeNames = getServletContext().getAttributeNames();

        while (attributeNames.hasMoreElements()) {

            String name = (String) attributeNames.nextElement();
            logger.log(Level.INFO, "attempting to autowire " + name);

            autowire(name);
        }

        super.service(request, response);
    }

    protected boolean autowireByType() {
        return true;
    }

    private void autowire(String name) {
        if (name != null) {
            Object attribute = getServletContext().getAttribute(name);
            Class<?> c = getClass();
            while (c != null && c != c.getSuperclass()) {
                try {
                    if (autowireByType()) {
                        if (byType(c, attribute)) {
                            break;
                        }
                        else {
                            c = c.getSuperclass();
                        }
                    }
                    else {
                        if (byName(c, name, attribute)) {
                            break;
                        }
                        else {
                            c = c.getSuperclass();
                        }
                    }
                }
                catch (NoSuchMethodException e) {
                    c = c.getSuperclass();
                }
            }
        }
    }

    private boolean byName(Class c, String name, Object attribute)
        throws NoSuchMethodException {
        boolean success = false;

        if (attribute != null) {

            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(getMethodName(name))) {
                    Class[] paramTypes = method.getParameterTypes();

                    if (paramTypes.length == 1) {
                        success = invokeSpringBeanSetter(method, attribute);
                    }
                }
            }
        }
        return success;
    }

    private boolean byType(Class<?> c, Object attribute) {
        boolean success = false;

        if (attribute != null) {
            Method[] methods = c.getDeclaredMethods();

            for (Method method : methods) {
                Class[] paramTypes = method.getParameterTypes();

                Class<? extends Object> attributeClass = attribute.getClass();
                if (paramTypes.length == 1 &&
                        paramTypes[0].equals(attributeClass)) {
                    boolean succeeded = invokeSpringBeanSetter(method,attribute);
                    if (!success && succeeded) {
                        success = succeeded;
                    }
                        }
            }
        }
        return success;
    }

    private boolean invokeSpringBeanSetter(Method method, Object attribute) {
        boolean success = false;
        try {
            method.invoke(this, attribute);
            success = true;
        }
        catch (Exception e) {
            // TODO do we care?
        }
        return success;
    }

    private String getMethodName(String contextName) {
        return contextName.toUpperCase();
    }
}


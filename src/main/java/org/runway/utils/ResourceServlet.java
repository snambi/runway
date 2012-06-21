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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: snambi
 * Date: Apr 4, 2009
 * Time: 10:18:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceServlet extends HttpServlet {

	private static final long serialVersionUID = 6105402349056599934L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            // Get the absolute path of the image
            ServletContext sc = getServletContext();

            //String u = req.getRequestURI()  ;
            String filename = null;
            if( req.getPathInfo() != null ){
            	filename = sc.getRealPath( "/" ) + req.getServletPath()  + req.getPathInfo();
            }else{
            	filename = sc.getRealPath( "/" ) + req.getServletPath();
            }
             

            // Get the MIME type of the image
            String mimeType = sc.getMimeType(filename);
            if (mimeType == null) {
                sc.log("Could not get MIME type of "+filename);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            // Set content type
            resp.setContentType(mimeType);

            // Set content size
            File file = new File(filename);
            resp.setContentLength((int)file.length());

            // Open the file and output streams
            FileInputStream in = new FileInputStream(file);
            OutputStream out = resp.getOutputStream();

            // Copy the contents of the file to the output stream
            byte[] buf = new byte[1024];
            int count = 0;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            in.close();
            out.close();
        }
    
}

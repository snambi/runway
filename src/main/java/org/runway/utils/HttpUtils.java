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

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	
	public static String getGetHostname( HttpServletRequest request){
		StringBuilder sb = new StringBuilder();
		String protocolVersion = request.getRequestURL().toString();
		String p[] = protocolVersion.split(":");
		String protocol = "http";
		if( p!= null && p.length > 0){
			protocol = p[0];
		}
		sb.append(  protocol );

		sb.append( "://");
		sb.append( request.getServerName());
		if( request.getServerPort() != 80 ){
			sb.append(":");
			sb.append( request.getServerPort());
		}
		//sb.append("/");
		return sb.toString();
	}
	
	public static String getFullUrl( HttpServletRequest request ){
		StringBuilder sb = new StringBuilder();
		sb.append( getGetHostname(request));
		sb.append( request.getRequestURI() );
		if( request.getQueryString() != null ){
			sb.append("?");
			sb.append( request.getQueryString() );
		}
		return sb.toString();
	}
	
/*	public static String getPostUrl(PostData post){
		StringBuilder sb = new StringBuilder();
		sb.append("/p/");
		sb.append(TextUtils.getUrlFromString( post.getTitle()) );
		sb.append("/");
		sb.append(post.getId());
		return sb.toString();
	}
*/	
	public static String getPostUrl(String title, String id){
		StringBuilder sb = new StringBuilder();
		sb.append("/p/");
		sb.append(TextUtils.getUrlFromString(title) );
		sb.append("/");
		sb.append( id );
		return sb.toString();		
	}

}

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

import java.util.Iterator;
import java.util.List;

public class DbUtils {
	
	public static String createInString( List<String> keys ){
		StringBuilder sb = new StringBuilder();
		if( keys != null && keys.size()>0  ){
			for( Iterator<String> iter = keys.iterator() ; iter.hasNext(); ){
				String key = iter.next();
				sb.append("'");
				sb.append( key );
				sb.append("'");
				if( iter.hasNext()){
					sb.append(", ");
				}
			}
		}
		
		return sb.toString();
	}

}

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

import java.util.HashMap;

public class QueryStringUtils {
	
	public static HashMap<String,String> queryStringToHash( String queryStr ){
		
		HashMap<String,String> hash = new HashMap<String,String>();
		
		if( !TextUtils.isEmpty(queryStr)){
			String[] array = queryStr.split("&");
			if( array != null && array.length > 0 ){
				for( String value : array ){
					if( !TextUtils.isEmpty(value)  && value.contains("=") ){
						String[] tt = value.split("=");
						if( tt.length ==2 &&  tt[0] != null && tt[1] !=null ){
							hash.put(tt[0], tt[1]);
						}
						
					}
				}
			}
		}
		
		return hash;
	}

}

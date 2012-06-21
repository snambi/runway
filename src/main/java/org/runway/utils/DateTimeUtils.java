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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeUtils {
	
	public static final long YEAR = 365L * 24L * 60L * 60L * 1000L;
	public static final long MONTH = 30L * 24L * 60L * 60L * 1000L;
	public static final long WEEK =   7L * 24L * 60L * 60L * 1000L;
	public static final long DAY  =   1L * 24L * 60L * 60L * 1000L;
	public static final long HOUR  =             60L * 60L * 1000L;
	public static final long MINUTE =                  60L * 1000L;
	public static final long SECOND =                   1L * 1000L;
	
	public static Timestamp getCurrentTimestamp(){
		Calendar cal = Calendar.getInstance();
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		return timestamp;
	}
	
	public static String getDate(){
		Timestamp ts = getCurrentTimestamp();
		SimpleDateFormat format =new SimpleDateFormat("MM/dd/yy");
		return  format.format(ts);
	}
	
	public static int getSecondsPassedInHour(){
		Calendar cur = GregorianCalendar.getInstance();
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(cur.get(Calendar.YEAR), 
						cur.get(Calendar.MONTH), 
						cur.get(Calendar.DATE),
						cur.get(Calendar.HOUR_OF_DAY), 
						0);
		
		long currenttime = cur.getTime().getTime();
		long hourtime = calendar.getTime().getTime();
		
		long millis = currenttime - hourtime;
		int result = (int) (millis / 1000 );
		return result;
	}
	
	public static String getTimePassedSince( Timestamp timestamp ){
		
		String result = null;
		
		Timestamp currentTime = getCurrentTimestamp();
		long diff = currentTime.getTime() - timestamp.getTime();
		
		boolean flag = false;
		if( diff >=0 ){
			if( diff >= YEAR && !flag ){
				// find how many years
				long d = diff / YEAR;
				flag = true;
				if( d == 1){
					result= d + " year ago";
				}else{
					result= d + " years ago";
				}
			}else if( diff >= MONTH && !flag){
				// find how many months
				long m = diff / MONTH;
				flag = true;
				if( m == 1 ){
					result =  m + " month ago";
				}else{
					result =  m + " months ago";
				}
			}else if( diff >= WEEK && flag == false){
				// find how many weeks ago
				long w = diff / WEEK;
				flag = true;
				if( w == 1 ){
					result = w + " week ago";
				}else{
					result = w + " weeks ago";
				}
			}else if( diff >= DAY && flag ==false){
				// find how many days ago
				long d = diff / DAY;
				flag = true;
				if( d == 1){
					result = d + " day ago";
				}else{
					result = d + " days ago";
				}
			}else if( diff >= HOUR && flag == false){
				// find how many hours ago
				long h = diff / HOUR;
				flag = true;
				if( h == 1 ){
					result = h + " hour ago";
				}else{
					result = h + " hours ago";
				}
			}else if( diff >= MINUTE && flag == false){
				// find how many minutes ago
				long m = diff / MINUTE;
				flag = true;
				if( m == 1){
					result = m + " minute ago";
				}else{
					result = m + " minutes ago";
				}
			}else if( diff >= SECOND && flag == false){
				// find how many seconds ago
				long s = diff / SECOND;
				flag = true;
				if( s == 1){
					result = s + " second ago";
				}else{
					result = s + " seconds ago";
				}
			}	
			
		}else{
			result = "in future";
		}
		
		return result;
	}

}

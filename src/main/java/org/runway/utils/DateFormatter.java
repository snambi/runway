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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SIMPLE_TIME_FORMAT = "dd MMM, yyyy HH:mm";
	
	public static String getDateString( Timestamp datetime){
		
		SimpleDateFormat formatter = new SimpleDateFormat( "MMM dd yyyy");
		String formattedDate = formatter.format(datetime);
		
		return formattedDate;
	}
	
	public static String getSimpleDate( Timestamp timestamp ){
		SimpleDateFormat formatter = new SimpleDateFormat( SIMPLE_TIME_FORMAT);
		String formattedDate = formatter.format(timestamp);
		return formattedDate;
	}
	
	public static Timestamp convertStringToTimestamp( String timestr , String timeformat) throws ParseException{
		
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		SimpleDateFormat dateFormat = new SimpleDateFormat( timeformat );
		Date parsedDate = dateFormat.parse( timestr );
		Timestamp timestamp = new Timestamp(parsedDate.getTime());
		return timestamp;
	}
	
	public static String convertTimestampToString( Timestamp timestamp, String timeformat ){
		SimpleDateFormat dateFormat = new SimpleDateFormat(timeformat);
		return dateFormat.format(timestamp);
	}

}

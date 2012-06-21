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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * LogFormatter Description.
 *
 * @author : snambi
 */
public class LogFormatter extends Formatter {

    //private static Logger logger = Logger.getLogger( LogFormatter.class.getName() );
    //private final static String format = "{0,date} {0,time,long}";
    private SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss.SSS");

    // Line separator string.  This is the value of the line.separator
    //private String lineSeparator = AccessController.doPrivileged( (PrivilegedAction<String>) new GetPropertyAction("line.separator"));
    // above line is difficult to understand.

    /**
     * Format the given LogRecord.
     * @param record the log record to be formatted.
     * @return a formatted log record
     */
    public synchronized String format(LogRecord record)
    {
        StringBuffer sb = new StringBuffer();

        Date date = new Date();
        date.setTime(record.getMillis());

        // add date and time with millisecond
        sb.append( formatter.format(date) );
        sb.append(" ");

        // add log level
        sb.append(record.getLevel().getLocalizedName());
        sb.append(" ");

        // add thread name
        String thrname = Thread.currentThread().getName();
        sb.append( "["+ thrname +"]" );
        sb.append(" ");

        // add class name
        if (record.getSourceClassName() != null) {
            sb.append(record.getSourceClassName());
        } else {
            sb.append(record.getLoggerName());
        }

        // add method name
        if (record.getSourceMethodName() != null) {
            sb.append(".");
            sb.append(record.getSourceMethodName());
            sb.append("()");
        }

        sb.append(" ");

        //sb.append(lineSeparator);

        String message = formatMessage(record);
        sb.append("- ");
        sb.append(message);
        //sb.append(lineSeparator);
        if (record.getThrown() != null) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch (Exception ex) {
            }
        }

        return sb.toString();

    }//format
}

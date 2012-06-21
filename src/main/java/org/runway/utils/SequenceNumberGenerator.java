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

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class SequenceNumberGenerator {
	
	public static int getNext( String sequenceName, SimpleJdbcTemplate jdbcTemplate ){
		
		int nextValue = 0;
		
		String updateSql = "UPDATE EM_SEQUENCE_NUMBER_TABLE set SEQ_NO=SEQ_NO+1 where ID = ?";
		jdbcTemplate.update( updateSql,sequenceName);
		
		String selectSql = "SELECT SEQ_NO FROM EM_SEQUENCE_NUMBER_TABLE where ID = ?";
		nextValue = jdbcTemplate.queryForInt( selectSql, sequenceName);
		
		return nextValue;
	}

}

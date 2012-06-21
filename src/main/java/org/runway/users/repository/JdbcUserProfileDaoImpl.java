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

package org.runway.users.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.runway.users.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("JdbcUserProfileDaoImpl")
public class JdbcUserProfileDaoImpl implements IUserProfileDao {
	
	private SimpleJdbcTemplate jdbcTemplate;
	
    @Autowired
    public void createTemplate(@Qualifier("jdbcDataSource") DataSource jdbcDatasource){
        this.jdbcTemplate = new SimpleJdbcTemplate(jdbcDatasource);
    }

	public void addUserProfile(UserProfile profile) {
        jdbcTemplate.update("INSERT INTO EM_USER_PROFILE  (id, key1, value, em_time ) VALUES (?,?,?, CURRENT_TIMESTAMP) ",
        		profile.getUserId(),
        		profile.getKey(),
        		profile.getValue()
                );
	}

	public void updateUserProfile(UserProfile profile) {
        String updateSql = "update EM_USER_PROFILE set VALUE=?, EM_TIME= CURRENT_TIMESTAMP " +
        								"where ID =?  and KEY = ?";

		jdbcTemplate.update( updateSql,
												profile.getValue(),
												profile.getUserId(),
												profile.getKey()
		                     				);
	}
	
	public void deleteUserProfile(String userid, String key) {
        String deleteSql = "DELETE FROM EM_USER_PROFILE WHERE id =? AND key1 =?";

        jdbcTemplate.update( deleteSql, userid, key);
	}

	public void deleteUserProfiles(String userid) {
        String deleteSql = "DELETE FROM EM_USER_PROFILE WHERE id =?";

        jdbcTemplate.update( deleteSql, userid);
	}

	public UserProfile getUserProfile(String userid, String key) {
		
        MapSqlParameterSource mapSql = new MapSqlParameterSource();
        mapSql.addValue("id", userid);
        
        String querySql = "SELECT id, key1, value, em_time from EM_USER_PROFILE where id=:id AND key1=:key";

        UserProfile profile = jdbcTemplate.queryForObject(querySql,
															new UserProfileMapper(),
															mapSql);

        return profile;
	}
	
	public List<UserProfile> getUserProfile(String userid) {
        MapSqlParameterSource mapSql = new MapSqlParameterSource();
        mapSql.addValue("id", userid);
        
        String querySql = "SELECT id, key1, value, em_time from EM_USER_PROFILE where id=:id";
        
        List<UserProfile> profiles = jdbcTemplate.query(querySql, 
					        							new UserProfileMapper(),
					        							mapSql);
		return profiles;
	}	
	
    private static class UserProfileMapper implements ParameterizedRowMapper<UserProfile> {

        public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {

            UserProfile userprofile = new UserProfile();
            
            userprofile.setUserId(rs.getString("id"));
            userprofile.setKey(rs.getString("key1"));
            userprofile.setValue(rs.getString("value"));
            userprofile.setTimestamp(rs.getTimestamp("em_time"));

            return userprofile;
        }
    }



}

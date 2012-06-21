/*
 *  JdbcUserDaoImpl
 *  
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

import org.runway.users.domain.User;
import org.runway.users.domain.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * JdbcUserDaoImpl Description.
 *
 * @author : snambi
 */
@Deprecated
@Repository("JdbcUserDaoImpl")
public class JdbcUserDaoImpl implements IUserDao {
//public class JdbcUserDaoImpl extends SimpleJdbcDaoSupport implements IUserDao {


    private SimpleJdbcTemplate jdbcTemplate;

    public void addUser(User user){
        
         jdbcTemplate.update("INSERT INTO EM_USERS  " +
        		 				"(id, email, email_status, password, firstname, " +
        		 				" lastname, status, last_login, em_time ) " +
        		 				" VALUES (?,?,?,?,?,  ?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ",
			        		 user.getId(),
			        		 user.getEmail(),
			        		 user.getEmailStatus(),
			                 user.getPassword(),
			                 user.getFirstName(),
			                 
			                 user.getLastName(),
			                 user.getStatus().getStatus()
			                 );

    }

    public User getUserById(String id) {

        MapSqlParameterSource mapSql = new MapSqlParameterSource();
        mapSql.addValue("id", id);

        User user = jdbcTemplate.queryForObject("SELECT * from EM_USERS where ID=:id",
													new UserMapper(),
													mapSql);

        return user;
    }
    
	public List<User> getUsersById(List<String> ids) {
		MapSqlParameterSource mapSql = new MapSqlParameterSource();
		mapSql.addValue("IDS", ids);
		
		String sql = "SELECT * from EM_USERS where ID in ( :IDS )";
		List<User> users = jdbcTemplate.query(sql, new UserMapper(), mapSql);
		return users;
	}
    
	public List<User> getUsersByEmail(String email) {
		MapSqlParameterSource args = new MapSqlParameterSource();
		args.addValue("EMAIL", email);
		
		String querySql = "select * from EM_USERS where EMAIL = :EMAIL ";
		
		List<User> users = jdbcTemplate.query(querySql, new UserMapper(), args);
		return users;
	}   

    public void updateUser(User user) {

    	String updateSql = "update EM_USERS set EMAIL=?,  PASSWORD=?, FIRSTNAME=? , LASTNAME=?, STATUS = ? , " +
    											" LAST_LOGIN = ? , EMAIL_STATUS = ? , EM_TIME= CURRENT_TIMESTAMP " +
    											" where ID =?";

       jdbcTemplate.update( updateSql,
	   							user.getEmail(),
                                user.getPassword(),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getStatus().getStatus(),
                                
                                user.getLastLogin(),
                                user.getEmailStatus(),
                                user.getId()
                                );
    }

    public void deleteUserById(User user) {

        String deleteSql = "DELETE FROM EM_USERS WHERE id =?";
        jdbcTemplate.update( deleteSql, user.getId());
    }

    private static class UserMapper implements ParameterizedRowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User();
            
            user.setId(rs.getString("id"));
            user.setEmail( rs.getString("email"));
            user.setPassword( rs.getString("password") );
            user.setFirstName( rs.getString("firstname"));
            user.setLastName( rs.getString("lastname"));
            user.setStatus(UserStatusEnum.convertToEnum(rs.getInt("STATUS")));
            user.setEmailStatus( rs.getInt("EMAIL_STATUS") );
            user.setLastLogin(rs.getTimestamp("LAST_LOGIN") );
            user.setTimestamp(rs.getTimestamp("em_time"));

            return user;
        }
    }


    @Autowired
    public void createTemplate(@Qualifier("jdbcDataSource") DataSource jdbcDatasource){
        this.jdbcTemplate = new SimpleJdbcTemplate(jdbcDatasource);
    }




}

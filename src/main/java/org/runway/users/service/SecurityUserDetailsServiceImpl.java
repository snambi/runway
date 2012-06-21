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
package org.runway.users.service;

import org.runway.users.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * <tt>SecurityUserDetailsServiceImpl<tt> calls IUserManagerService for getting User object based on the user name then creates UserDetails object that spring security needs.
 * @author snambi
 *
 */
public class SecurityUserDetailsServiceImpl implements UserDetailsService{
	
	private IUserManagerService userMgr;
	
	@Autowired
	public void setUserManagerService( IUserManagerService svc ){
		userMgr = svc;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		org.springframework.security.core.userdetails.User userDetails = null;
		
		try {
			User user = userMgr.getUserById(username);
			
			// TODO : get the "ROLE" of an user from the database
			GrantedAuthorityImpl authority = new GrantedAuthorityImpl("ROLE_USER");
			GrantedAuthority[] authorities = { authority };

			userDetails = new org.springframework.security.core.userdetails.User(user.getId(), 
																			user.getPassword(), 
																			true, true, true, true, 
																			authorities);
			
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		
		return userDetails;
	}

}

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
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class AuthenticationUtils {

	public static void autoLogin(User user, 
								HttpServletRequest request,
								AuthenticationManager authenticationManager) {

		
//			  GrantedAuthority[] grantedAuthorities = new GrantedAuthority[] { new GrantedAuthorityImpl(
//			    user.getAuthority()) };
		

			  UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					  											user.getUsername(), 
					  											user.getPassword(),
					  											user.getAuthorities());

			  // generate session if one doesn't exist
			  HttpSession session = request.getSession();

			  token.setDetails(new WebAuthenticationDetails(request));
			  Authentication authenticatedUser = authenticationManager.authenticate(token);

			  SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			  // setting role to the session
			  session.setAttribute(
			      HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
			      SecurityContextHolder.getContext());

			 }

}

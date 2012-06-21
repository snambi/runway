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

import org.runway.users.domain.UserPasswordReset;
import org.runway.utils.RunwaySecurityException;
import org.runway.utils.StringEncryptDecryptUtil;


public class UserLinkHelper {

	public static String createPasswordResetLink(UserPasswordReset resetRequest){
		return createLink(resetRequest, "/passwordreset");
	}

	public static String createLink(UserPasswordReset resetRequest, String command) {
		
		StringBuilder sb = new StringBuilder();
		try {
			String resetId = StringEncryptDecryptUtil.urlEncrypt( Integer.toString( resetRequest.getId()) );
			sb.append(resetRequest.getHostName());
			sb.append( command );
			sb.append("?");
			sb.append( resetId );
		} catch (RunwaySecurityException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

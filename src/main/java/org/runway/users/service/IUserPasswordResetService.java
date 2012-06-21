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

public interface IUserPasswordResetService {
	
	public void createPasswordResetRequest( UserPasswordReset resetRequest) throws EmailNotRegisteredException;
	public UserPasswordReset getPasswordRequest( int requestId);
	public void updatePasswordResetRequest( UserPasswordReset resetRequest );
	
	public void updatePassword( String userId, String currentPassword, String newPassword) throws UserNotFoundException, UserPasswordNotMatchedException;
	public void setPassword( String userId, String newPassword) throws UserNotFoundException;
}

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

import org.runway.users.domain.UserPasswordReset;

public interface IUserPasswordResetDao {
	
	public void addPasswordReset( UserPasswordReset passwordReset );
	public UserPasswordReset getPasswordReset( int id);
	public void updatePasswordReset( UserPasswordReset passwordReset );
	public void deletePasswordReset( int id);
	
	public long generatePasswordResetId();

}

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

package org.runway.users.domain;

public enum UserStatusEnum {
	//INVALID(-1, "Invalid"),
	INVITED(0, "Invited"),
	REGISTRATION_IN_PROGRESS(1, "Registration in progress"),
	ACTIVE(2, "Active"),
	IN_ACTIVE( 3, "In active"),
	REMOVED( 4, "Removed");
	
	private int status = 0;
	private String description;
	
	private UserStatusEnum( int status, String desc){
		this.status = status;
		this.description = desc;
	}
	
	public int getStatus(){
		return status;
	}
	public String getDescription(){
		return description;
	}

	public static UserStatusEnum convertToEnum( int value){
		UserStatusEnum result=null;
		switch( value){
		case 0:
			result = INVITED;
			break;
		case 1:
			result = REGISTRATION_IN_PROGRESS;
			break;
		case 2:
			result = ACTIVE;
			break;
		case 3:
			result = IN_ACTIVE;
			break;
		case 4:
			result =REMOVED;
			break;
//		default:
//			result = INVALID;
		}
		
		return result;
	}
}

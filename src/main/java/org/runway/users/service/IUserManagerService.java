/*
 *  IUserManager
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
package org.runway.users.service;

import java.io.Serializable;
import java.util.List;

import org.runway.users.domain.User;
import org.runway.users.domain.UserProfile;


/**
 * IUserManager Description.
 *
 * @author : snambi
 */
public interface IUserManagerService extends Serializable {

    public void addUser(User user) throws UserAlreadyExistsException, UserDataNotCompleteException;
    public void addUserInternal( User user ) throws UserAlreadyExistsException;
    public User getUserById(String id) throws UserNotFoundException;
    public List<User> getUsersById( List<String> ids);
    public User getUsersByEmail(String email) throws UserNotFoundException;
    public boolean isEmailRegistered( String email);
    public boolean isUserPasswordValid(String userid, String password);
    public void updateUser(User user) throws UserNotFoundException;
    public void deleteUserById(String id) throws UserNotFoundException;
    
    public void setUserProfile(UserProfile profile) throws UserNotFoundException;
    public UserProfile getUserProfile(String userId, String key ) throws UserNotFoundException;
    public void deleteUserProfile(String userId, String key) throws UserNotFoundException;
    public List<UserProfile> getUserProfiles(String userId) throws UserNotFoundException;

}

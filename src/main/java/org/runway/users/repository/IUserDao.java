/*
 *  IUserDao
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

import java.util.List;

import org.runway.users.domain.User;


/**
 * IUserDao Description.
 *
 * @author : snambi
 */
public interface IUserDao {

    public void addUser(User user);

    public User getUserById(String id);
    public List<User> getUsersById(List<String> ids);
    public List<User> getUsersByEmail( String email);

    public void updateUser(User user);

    public void deleteUserById(User user);
}

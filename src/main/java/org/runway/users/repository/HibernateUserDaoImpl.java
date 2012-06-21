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

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.runway.users.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;



@Repository ("HibernateUserDaoImpl")
public class HibernateUserDaoImpl  implements IUserDao {

    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(@Qualifier("hibernateSessionFactory") SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    public void addUser(User user) {
        hibernateTemplate.save(user);
    }

    public User getUserById(String id) {
		@SuppressWarnings("unchecked")
		List<User> users =  hibernateTemplate.find("from User where ID=? ", id);
        return users.get(0);
    }
	public List<User> getUsersById(List<String> ids) {
		String queryString = "from User where User.ID in (:IDS)";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(queryString);
		query.setParameterList("IDS", ids);
		List<User> users = query.list();
		session.clear();
		session.close();
		return users;
	}    

    public void updateUser(User user) {
        hibernateTemplate.saveOrUpdate(user);
    }

    public void deleteUserById(User user) {
        hibernateTemplate.delete(user);
    }

	public User getUserByEmail(String email) {
		return null;
	}

	public List<User> getUsersByEmail(String email) {
		return null;
	}


}

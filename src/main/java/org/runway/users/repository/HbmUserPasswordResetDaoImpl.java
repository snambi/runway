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
import org.runway.sequencenumber.HibernateSequenceNumberGenerator;
import org.runway.users.domain.UserPasswordReset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;


@Repository("HbmUserPasswordResetDaoImpl")
public class HbmUserPasswordResetDaoImpl implements IUserPasswordResetDao {
	
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory ){
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

	public void addPasswordReset(UserPasswordReset passwordReset) {
	       Session session = getSessionFactory().openSession();
	        session.save(passwordReset);
	        session.flush();
	        session.clear();
	        session.close();
	}

	public UserPasswordReset getPasswordReset(int id) {
        String queryString = "from UserPasswordReset where id = :ID";
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery(queryString);
        query.setLong("ID", id);
        List<UserPasswordReset> list = query.list();
        session.close();
        return  list.get(0);
	}

	public void updatePasswordReset(UserPasswordReset passwordReset) {
	      Session session = getSessionFactory().openSession();
	        session.saveOrUpdate(passwordReset);
	        session.flush();
	        session.clear();
	        session.close();
	}

	public void deletePasswordReset(int id) {
	      Session session = getSessionFactory().openSession();
	      UserPasswordReset userpasswordreset = new UserPasswordReset();
	      	userpasswordreset.setId(id);
	        session.delete(userpasswordreset);
	        session.flush();
	        session.clear();
	        session.close();	
	}

	public long generatePasswordResetId() {
		return HibernateSequenceNumberGenerator.getNext( "EM_RESET_PASSWORD_ID_SEQ",  getSessionFactory());
	}
}

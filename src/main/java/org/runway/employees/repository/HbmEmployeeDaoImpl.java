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

package org.runway.employees.repository;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.runway.employees.domain.Employee;
import org.runway.sequencenumber.HibernateSequenceNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("HbmEmployeeDaoImpl")
public class HbmEmployeeDaoImpl implements IEmployeeDao {
	
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory ){
        this.sessionFactory = sessionFactory;
    }	


	public void add(Employee cafe) {
		Session session = getSessionFactory().openSession();
        session.save(cafe);
        session.flush();
        session.clear();
        session.close();
    }

	public void delete(Employee cafe) {
		Session session = getSessionFactory().openSession();
        session.delete(cafe);
        session.flush();
        session.clear();
        session.close();	
    }

	public void update(Employee cafe) {
      Session session = getSessionFactory().openSession();
        session.saveOrUpdate(cafe);
        session.flush();
        session.clear();
        session.close();	
	}

	public Employee get(int id) {
		String queryString = "from Employee where id = :ID";
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery(queryString);
        query.setLong("ID", id);
        List<Employee> list = query.list();
        session.close();
        return  list.get(0);
	}
	
	public List<Employee> getAllEmployees() {
		String queryString = "from Employee";
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery(queryString);
        List<Employee> list = query.list();
        session.close();
        return  list;
	}

	public long generateEmployeeId() {
		return HibernateSequenceNumberGenerator.getNext( "EMPLOYEE_ID_SEQ",  getSessionFactory());
	}
	
	public List<Employee> getByName(String cafeName) {
		String queryString = "from Employee where name = :NAME";
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery(queryString);
        query.setString("NAME", cafeName);
		List<Employee> list = query.list();
        session.close();
        return  list;
	}
	
	public List<String> getAllEmployeeNames() {
		String queryString = "select distinct name from Employee";
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery(queryString);
		List<String> list = query.list();
        session.close();
        Collections.sort(list);
        return  list;
	}
	
	public List<Employee> searchByName(String term) {
		String queryString = "from Employee where name LIKE :NAME";
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery(queryString);
        query.setString("NAME", "%"+term+"%");
		List<Employee> list = query.list();
        session.close();
        return  list;
	}

	public List<Employee> searchByCity(String term) {
		String queryString = "from Employee where city LIKE :CITY";
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery(queryString);
        query.setString("CITY", "%"+term+"%");
		List<Employee> list = query.list();
        session.close();
        return  list;
	}

}

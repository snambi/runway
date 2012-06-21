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

package org.runway.employees.service;

import java.util.List;

import org.runway.employees.domain.Employee;
import org.runway.employees.repository.IEmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;
	
	public Employee add(Employee employee) {
		
		if(employee == null ){
			throw new NullPointerException("employee is null");
		}
		if( employee.getId() == 0 ){
			long id = employeeDao.generateEmployeeId();
			employee.setId(id);
		}
		
		employeeDao.add(employee);
	
		return employee;
	}

	public void delete(int id) {
		Employee employee = new Employee();
		employee.setId(id);
		employeeDao.delete(employee);
	}

	public void update(Employee employee) {
		if( employee == null ){
			throw new NullPointerException("employee is null");
		}
		
		employeeDao.update(employee);
	}

	public Employee get(int id) {
		return employeeDao.get(id);
	}

	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	public List<Employee> getByName(String employee) {
		return employeeDao.getByName(employee);
	}

	public List<String> getAllEmployeeNames() {
		return employeeDao.getAllEmployeeNames();
	}

	public List<Employee> searchEmployeeByName(String term) {
		return employeeDao.searchByName(term);
	}
	public List<Employee> searchEmployeeByCity(String term){
		return employeeDao.searchByCity(term);
	}
}

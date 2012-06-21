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

public interface IEmployeeService {

	public Employee add( Employee employee );
	public void delete( int id);
	public void update( Employee employee);
	public Employee get( int id );
	public List<Employee> getByName( String employeeName );
	public List<Employee> getAllEmployees();
	public List<String> getAllEmployeeNames();
	public List<Employee> searchEmployeeByName(String term);
	public List<Employee> searchEmployeeByCity(String term);
}

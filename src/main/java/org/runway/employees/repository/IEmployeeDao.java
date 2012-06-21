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

import java.util.List;

import org.runway.employees.domain.Employee;

public interface IEmployeeDao {

	public void add(Employee employee);

	public void delete(Employee employee);

	public void update(Employee employee);

	public Employee get(int id);

	public List<Employee> getByName(String employeeName);
	public List<Employee> searchByName(String name);
	public List<Employee> searchByCity(String city);
	
	List<Employee> getAllEmployees();
	List<String> getAllEmployeeNames();

	public long generateEmployeeId();
}

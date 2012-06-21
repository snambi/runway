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

package org.runway.employees.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
/*
+----------+--------------+------+-----+-------------------+-----------------------------+
| Field    | Type         | Null | Key | Default           | Extra                       |
+----------+--------------+------+-----+-------------------+-----------------------------+
| ID       | int(11)      | NO   |     | NULL              |                             |
| NAME     | varchar(255) | YES  | MUL | NULL              |                             |
| AGE      | int(11)      | YES  |     | NULL              |                             |
| ADDRESS  | varchar(255) | YES  |     | NULL              |                             |
| CITY     | varchar(200) | YES  |     | NULL              |                             |
| STATE    | varchar(200) | YES  |     | NULL              |                             |
| ZIP_CODE | varchar(25)  | YES  |     | NULL              |                             |
| EM_TIME  | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
+----------+--------------+------+-----+-------------------+-----------------------------+
*/
	
	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="YELP_LINK")
	private String yelpLink;
	
	@Column(name="YELP_DATA")
	private String yelpData;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="ZIP_CODE")
	private String zipcode;
	
	@Column(name="EM_TIME")
	private Timestamp emTime;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getYelpLink() {
		return yelpLink;
	}
	public void setYelpLink(String yelpLink) {
		this.yelpLink = yelpLink;
	}
	public String getYelpData() {
		return yelpData;
	}
	public void setYelpData(String yelpData) {
		this.yelpData = yelpData;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Timestamp getEmTime() {
		return emTime;
	}
	public void setEmTime(Timestamp emTime) {
		this.emTime = emTime;
	}
	
	public String getFullAddress() {
		StringBuilder sb = new StringBuilder();

		sb.append(getAddress());
		sb.append(",");
		sb.append(getCity());
		sb.append(",");
		sb.append(getState());

		return sb.toString();
	}
}

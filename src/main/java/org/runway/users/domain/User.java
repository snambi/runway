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

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.runway.utils.TextUtils;


/**
 * User Description.
 *
 * This Table contains the most used information related to an emarket user.
 * This includes eMarketId, password, first name, last name and email.
 *
 * @author : snambi
 */

@Entity
@Table(name="EM_USERS")
public class User implements Serializable {

	private static final long serialVersionUID = -452924108838376905L;

	@Id
    @Column(name="ID", length=255) 
    private String id;

    @Column(name="EM_PASSWORD",length=255)
    private String password = null;

    @Column(name="FIRSTNAME",length=255)
    private String firstName = null;

    @Column(name="LASTNAME", length=255)
    private String lastName = null;
    
    private String email =null;
    
    private int emailStatus = 0;
    
    private UserStatusEnum status;
    
    private Timestamp lastLogin;

    @Column(name="EM_TIME", length=255)
    private Timestamp timestamp = null;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getName(){
    	StringBuilder sb =  new StringBuilder();
    	if( !TextUtils.isEmpty(getFirstName())){
    		sb.append(getFirstName());
    		if(!TextUtils.isEmpty(getLastName())){
    			sb.append(" ");
    			sb.append( getLastName());
    		}
    	}else{
    		sb.append(getId());
    	}

    	return sb.toString();
    }
    public String getId() {
        return id;
    }
    public void setId(String Id) {
        this.id = Id;
    }
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEmailStatus(int status){
		this.emailStatus = status;
	}
	public int getEmailStatus(){
		return this.emailStatus;
	}
	public boolean isPrimaryEmail() {
		boolean result = false;
		if( getEmailStatus() == 0){
			result = true;
		}
		return result;
	}
	public UserStatusEnum getStatus() {
		return status;
	}
	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}    

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append( getId());
        sb.append(",");
        sb.append( getFirstName());
        sb.append(",");
        sb.append( getLastName());

        return super.toString(); 
    }

    public boolean equals(Object obj){

        if( this == obj ){
            return true;
        }

        if( obj == null || obj.getClass() != this.getClass() ){
            return false;
        }

        boolean result = false;
        User user = (User) obj;
        
        if( this.getId().equals(user.getId()) ){
        	result =true;
        }

        return result;
    }

    public int hashCode(){

        int hash = 7;
        hash = 31 * hash + ( null == id ? 0: id.hashCode() );
        return hash;
    }

}

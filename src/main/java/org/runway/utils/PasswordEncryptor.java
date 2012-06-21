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

package org.runway.utils;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.digest.config.EnvironmentStringDigesterConfig;

public class PasswordEncryptor {
	
	static String[] qaPasswords = new String[]{"admin123", 
										"test123", 
										"nambi123",
										"nambi",
										"13Peoplebk13",
										"radikit",
										"Vasu23rag12",
										"raghu",
										"viraj"};
	
	static String[] prdPasswords = new String[]{
											"admin123",
											"test123",
											"nambi123",
											"atma123",
											"dudhara",
											"john123",
											"poiulkjh",
											"livelife",
											"mpr74",
											"8159br",
											"tanman44",
											"Focus0nit",
											"Phoenix1",
											"r&sraj",
											"muffin"
										};
	

	StandardStringDigester stringDigester;
	
	public PasswordEncryptor(){
		
		EnvironmentStringDigesterConfig digesterConfig = new EnvironmentStringDigesterConfig();
		
		digesterConfig.setAlgorithm("SHA-1");
		digesterConfig.setIterations(10000);
		digesterConfig.setSaltGeneratorClassName("org.jasypt.salt.ZeroSaltGenerator");
		digesterConfig.setSaltSizeBytes(8);
		
		stringDigester = new StandardStringDigester();
		stringDigester.setConfig(digesterConfig);
	}
	
	public String encryptPassword( String plainTextPassword ){
		
		String encryptedPassword = stringDigester.digest(plainTextPassword);
		return encryptedPassword;
	}

	public static final void main( String[] args){
		
		PasswordEncryptor encryptor = new PasswordEncryptor();
		
		System.out.println("QA passwords:");
		for( String passwd: qaPasswords ){
			String encryptedPasswd = encryptor.encryptPassword(passwd);
			System.out.println("passwd: " + passwd + " , encrypted password: "+ encryptedPasswd);
		}
		
		System.out.println("PRD passwords:");
		for( String password: prdPasswords){
			String encryptedPassword = encryptor.encryptPassword(password);
			System.out.println("passwd: " + password + " , encrypted password: "+ encryptedPassword);
		}
	}
}

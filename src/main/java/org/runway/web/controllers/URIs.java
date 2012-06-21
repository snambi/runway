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

package org.runway.web.controllers;

public class URIs {

	public static final String HOME = "/home";
	public static final String ABOUT = "/about";
	public static final String ERROR = "/error";
	public static final String LOGIN = "/login";
	
	// USERS
	public static final String USER_PASWORD_RESET_REQUEST  = "/resetpassword";
	public static final String USER_PASSWORD_RESET_REQUEST_SUCCESS = "/resetRequestSuccess";
	public static final String USER_PASSWORD_RESET_FORM_REQUEST = "/passwordreset";
	public static final String USER_PASSWORD_RESET_FORM_REQUEST_SUCCESS = "/passwordresetSuccess";
	public static final String USER_MY_PROFILE = "/myprofile";
	public static final String USER_EDIT_PROFILE_FORM_REQUEST = "/editprofile";
	public static final String USER_CHANGE_PASSWORD = "/changepassword";
	public static final String USER_CHANGE_PASSWORD_SUCCESS = "/changepasswordsuccess";
	
	//HOME
	public static final String COMMON_PUBLIC_HOME = "/";
	
	// LEGAL, ABOUT, HELP
    public static final String LIST_OF_TOPICS = "/list_of_topics";
    public static final String ABOUTUS = "/aboutus";
    public static final String TERMS_OF_SERVICE = "/tos";
    public static final String CONTACTUS = "/contactus";

}

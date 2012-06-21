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

package org.runway.users.service;

import java.util.List;
import java.util.logging.Logger;

import org.jasypt.digest.StandardStringDigester;
import org.runway.system.service.IMailSenderService;
import org.runway.users.domain.User;
import org.runway.users.domain.UserProfile;
import org.runway.users.domain.UserStatusEnum;
import org.runway.users.repository.IUserDao;
import org.runway.users.repository.IUserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


/**
 * UserManagerServiceImpl Description.
 * 
 * @author : snambi
 */
@Service
public class UserManagerServiceImpl implements IUserManagerService {

	private static final long serialVersionUID = 9154954999767966663L;

	private static Logger logger = Logger.getLogger(UserManagerServiceImpl.class.getName());

	@Autowired
	@Qualifier("JdbcUserDaoImpl")
	private IUserDao userDao;

	@Autowired
	private IUserProfileDao userProfileDao;
	
	@Autowired
	private StandardStringDigester stringDigester;

	private IMailSenderService mailSender;
	private SimpleMailMessage templateMessage;

	public void setUserDao(IUserDao dao) {
		userDao = dao;
	}

	public void setUserProfileDao(IUserProfileDao dao) {
		userProfileDao = dao;
	}

	@Autowired
	public void setMailSender(IMailSenderService mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	public void addUser(User user) throws UserAlreadyExistsException,
			UserDataNotCompleteException {

		isUserDataComplete(user);
		
		// check whether the email address is already registered?
		if( isEmailRegistered(user.getEmail()) ){
			throw new UserAlreadyExistsException( user.getEmail() + " already registered");
		}

		try {

			String encryptedPassword = stringDigester.digest(user.getPassword());
			user.setPassword(encryptedPassword);
			userDao.addUser(user);
		} catch (DataIntegrityViolationException dive) {
			String msg = user.getId() + " already registered";
			throw new UserAlreadyExistsException(msg, dive);
		}

		// send email if the user creation is successful
		sendMail(user);

	}
	
	public void addUserInternal( User user ) throws UserAlreadyExistsException{
		try{
			userDao.addUser(user);
		}catch( DataIntegrityViolationException dive ){
			String msg = user.getId() + " already registered";
			throw new UserAlreadyExistsException(msg, dive);
		}
	}

	public User getUserById(String id) throws UserNotFoundException {

		User user = null;

		try {
			user = userDao.getUserById(id);
		} catch (EmptyResultDataAccessException erdae) {
			String msg = "Userid " + id + " Not found.";
			throw new UserNotFoundException(msg, erdae);
		}

		return user;
	}
	
	public List<User> getUsersById(List<String> ids){
		return userDao.getUsersById(ids);
	}
	
	/*
	 * At any point in time, one email address can associated with only one user.
	 * @see com.peoplebees.user.service.IUserManagerService#getUsersByEmail(java.lang.String)
	 */
	public User getUsersByEmail(String email) {
		
		User user = null;
		List<User> users = userDao.getUsersByEmail(email);
		
		for( User u: users){
			if( u.getStatus().equals(UserStatusEnum.ACTIVE) || 
					u.getStatus().equals( UserStatusEnum.REGISTRATION_IN_PROGRESS)){
				user = u;
			}
		}

		return user;
	}	

	public boolean isEmailRegistered(String email) {
		boolean result = false;
		User user = getUsersByEmail(email);
		
		if( user != null ){
			result = true;
		}

		return result;
	}	
	
	public boolean isUserPasswordValid(String userid, String password) {

		boolean result = false;
		User user = null;

		try {

			user = getUserById(userid);

			String encryptedPassword = stringDigester.digest(user.getPassword());
			user.setPassword(encryptedPassword);
			 
			if (user.getPassword().equals(password)) {
				result = true;
			}

		} catch (UserNotFoundException unfe) {
			unfe.printStackTrace();
		}

		return result;
	}

	public void updateUser(User user) throws UserNotFoundException {
		userDao.updateUser(user);
	}

	public void deleteUserById(String id) throws UserNotFoundException {
		User user = new User();
		user.setId(id);
		userDao.deleteUserById(user);
	}

	private void isUserDataComplete(User user)
			throws UserDataNotCompleteException {

		/*
		 * if(user.getFirstName() == null || user.getFirstName().equals("")){
		 * throw new UserDataNotCompleteException("firstname not provided"); }
		 * 
		 * if( user.getLastName() == null || user.getLastName().equals("")){
		 * throw new UserDataNotCompleteException("lastname not provided"); }
		 */

		if (user.getPassword() == null || user.getPassword().equals("")) {
			throw new UserDataNotCompleteException("password not provided");
		}

		if (user.getId() == null || user.getId().equals("")) {
			throw new UserDataNotCompleteException("Id not provided");
		}

		if (user.getEmail() == null || user.getEmail().equals("")) {
			throw new UserDataNotCompleteException("email not provided");
		}
	}

	private void sendMail(User user) {

		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);

		msg.setTo(user.getEmail());
		msg.setSubject("Re: your peoplebees account ");

		StringBuilder sb = new StringBuilder();
		// sb.append("Dear "+ user.getFirstName() + "\n");
		sb.append("your peoplebees account is created successfully.\n ");

		// the following format is correct. please don't change
		sb.append("    login id: " + user.getId() + "\n");

		sb.append("http://peoplebees.com\n");

		msg.setText(sb.toString());

		logger.info("sending message : \n" + msg.toString());
		this.mailSender.sendMail(msg);
	}

	public void deleteUserProfile(String userId, String key)
			throws UserNotFoundException {
		userProfileDao.deleteUserProfile(userId, key);
	}

	public List<UserProfile> getUserProfiles(String userId)
			throws UserNotFoundException {
		return userProfileDao.getUserProfile(userId);
	}

	public void setUserProfile(UserProfile profile)
			throws UserNotFoundException {
		
		// check whether the UserProfile exists
		UserProfile userProfile = userProfileDao.getUserProfile(profile.getUserId(), profile.getKey());
		if( userProfile == null ){
			userProfileDao.addUserProfile(profile);
		}else{
			userProfileDao.updateUserProfile(profile);
		}
	}

	public UserProfile getUserProfile(String userId, String key)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



}

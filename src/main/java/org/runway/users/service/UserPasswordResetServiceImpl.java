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

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.velocity.app.VelocityEngine;
import org.jasypt.digest.StandardStringDigester;
import org.runway.system.service.IMailSenderService;
import org.runway.users.domain.User;
import org.runway.users.domain.UserPasswordReset;
import org.runway.users.repository.IUserPasswordResetDao;
import org.runway.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;


@Service
public class UserPasswordResetServiceImpl implements IUserPasswordResetService {
	
	@Autowired
	IUserPasswordResetDao passwordResetDao = null;
	
	@Autowired
	private IUserManagerService userManagerSvc;
	
	@Autowired
	private IMailSenderService mailSender;
	
	@Autowired
	private SimpleMailMessage templateMessage;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Autowired
	private StandardStringDigester stringDigester;

	public void createPasswordResetRequest(UserPasswordReset resetRequest) 
										throws EmailNotRegisteredException {
		
		// first verify whether password can be reset.
		User user = null;
		if( !TextUtils.isEmpty( resetRequest.getEmail()) ){
			try {
				user = userManagerSvc.getUsersByEmail(resetRequest.getEmail());
				resetRequest.setUserId(user.getId());
			} catch (UserNotFoundException e) {
				e.printStackTrace();
				StringBuilder sb = new StringBuilder();
				sb.append(resetRequest.getEmail());
				sb.append(" not registered");
				throw new EmailNotRegisteredException(sb.toString(), e );
			}
		}
		
		// generate the password reset Id
		if( resetRequest.getId() == 0 ){
			long id = passwordResetDao.generatePasswordResetId();
			resetRequest.setId( (int) id);
		}
		
		if( resetRequest.getCreateTime() == null ){
			Timestamp createTime = new Timestamp(new Date().getTime());
			resetRequest.setCreateTime(createTime);
		}
		long oneday = 24 * 60 * 60 * 1000;
		Timestamp expireTime = new Timestamp(resetRequest.getCreateTime().getTime() + oneday );
		resetRequest.setExpireTime(expireTime);
		passwordResetDao.addPasswordReset( resetRequest );
		
		// send email.
		createAndSendEmail(user, resetRequest);
	}
	
	public UserPasswordReset getPasswordRequest(int requestId) {
		return passwordResetDao.getPasswordReset(requestId);
	}
	
	public void updatePasswordResetRequest(UserPasswordReset updateRequest) {
		passwordResetDao.updatePasswordReset(updateRequest);
	}
	
	public void updatePassword(String userId, String currentPassword,
			String newPassword) throws UserNotFoundException, UserPasswordNotMatchedException {
		
		User user = userManagerSvc.getUserById(userId);
		String encryptedCurrentPassword = stringDigester.digest(currentPassword);
		if( !user.getPassword().equals(encryptedCurrentPassword ) ){
			throw new UserPasswordNotMatchedException("current password does not match");
		}
		
		String encryptedNewPassword = stringDigester.digest(newPassword);
		
		user.setPassword(encryptedNewPassword);
		userManagerSvc.updateUser(user);
	}
	
	public void setPassword(String userId, 
							String newPassword) throws UserNotFoundException {
		
			User user = userManagerSvc.getUserById(userId);
			String encryptedPassword = stringDigester.digest(newPassword);
			user.setPassword(encryptedPassword);
			userManagerSvc.updateUser(user);
	}
	
	private void createAndSendEmail(User user, UserPasswordReset resetRequest) {

		//SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		MimeMessageHelper msg = new MimeMailMessage( mailSender.createMimeMailMessage() ).getMimeMessageHelper();
		
		String title = "Peoplebees password reset request ";
		
		try {
			msg.setFrom("admin@peoplebees.com");
			msg.setTo(user.getEmail());
			msg.setSubject( title);

			String resetLink= UserLinkHelper.createPasswordResetLink(resetRequest);
			String message = createMessage(title, resetRequest.getHostName(), resetLink, user);
			msg.setText( message , true);

			this.mailSender.sendMail(msg);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public String createMessage( String title,String hostUrl, String resetLink, User user ){
		
		@SuppressWarnings("rawtypes")
		Map model = new HashMap();
		model.put("hostUrl", hostUrl);
		model.put("title", title);
		model.put("resetLink", resetLink);
		model.put("userName", user.getName());
		model.put("userId", user.getId());
        
        String text = VelocityEngineUtils.mergeTemplateIntoString(
        					velocityEngine, 
        					"email/velocity/templates/users/passwordreset/user_password_reset_email.vm",
        					model);
		return text;
	}

}

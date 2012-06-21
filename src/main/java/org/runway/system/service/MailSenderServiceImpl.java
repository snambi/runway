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

package org.runway.system.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public  class MailSenderServiceImpl implements IMailSenderService{
	
	private MailSenderExecutor mailSenderExecutor = null;	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Autowired
	public void setMailSenderExecutor( MailSenderExecutor mailSender ){
		this.mailSenderExecutor = mailSender;
	}

	public void sendMail(SimpleMailMessage message) {
		mailSenderExecutor.execute(message);
	}

	public void sendMail(SimpleMailMessage message, IMailSenderCallback callback) {
		mailSenderExecutor.execute(message);
	}
	
	public void sendMail(MimeMessageHelper message) {
		mailSenderExecutor.execute(message);
	}	

	public MimeMessage createMimeMailMessage() {
		return javaMailSender.createMimeMessage();
	}
}

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

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 
 */
 public class MailSenderExecutor {
	
	private static Logger logger = Logger.getLogger( MailSenderExecutor.class.getName() );
	
	@Autowired
	@Qualifier("mailSenderTaskExecutor")
	private TaskExecutor taskExecutor;	
	
	//private MailSender mailSender;
	private JavaMailSender javaMailSender;
	
//	@Autowired
//    public void setMailSender(MailSender mailSender) {
//        this.mailSender = mailSender;
//    }
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}


	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	public void execute( SimpleMailMessage message ){
		MailSenderTask task = new MailSenderTask();
		task.setMessage(message);
		taskExecutor.execute(task);
	}
	public void execute( MimeMessageHelper message){
		MimeMailSenderTask task = new MimeMailSenderTask();
		task.setMessage(message);
		taskExecutor.execute(task);
	}
	


	private class MailSenderTask implements Runnable  {
		private SimpleMailMessage message = null;
		public SimpleMailMessage getMessage() {
			return message;
		}
		public void setMessage(SimpleMailMessage msg) {
			this.message = msg;
		}

		public void run() {	
			// send email
	        try{
	        	javaMailSender.send(getMessage() );
	        }catch(MailException ex) {
	            //System.out.println( "Error Sending message :\n" + msg.toString() +  "\n" + ex.getMessage());
	            logger.severe( "Error Sending message :\n" + getMessage().toString() +  "\n" + ex.getMessage());
	        }
		}
	}
	
	private class MimeMailSenderTask implements Runnable {
		private MimeMessageHelper message = null;
		
		public MimeMessageHelper getMessage() {
			return message;
		}
		public void setMessage(MimeMessageHelper message) {
			this.message = message;
		}
		
		public void run(){
			// send email
			try{
				javaMailSender.send(getMessage().getMimeMessage());
			}catch(MailException ex){
				logger.severe( "Error Sending message :\n" + getMessage().toString() +  "\n" + ex.getMessage());
			}
		}
	}
}

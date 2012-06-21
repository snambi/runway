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

package org.runway.system.scheduler;

import java.util.logging.Logger;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class EmailDigestJob extends QuartzJobBean{
	
	private static Logger logger = Logger.getLogger(EmailDigestJob.class.getName());

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		JobDataMap jobData = context.getJobDetail().getJobDataMap();
		//IGroupEmailDigestService groupEmailDigestSvc = (IGroupEmailDigestService) jobData.get("groupEmailDigestServiceImpl");
		
		logger.info("Beginning to send email digests");
		//groupEmailDigestSvc.sendTheDigests();
		logger.info("Completed sending email digests");
	}
}

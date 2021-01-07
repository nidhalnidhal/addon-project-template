package com.acme.jobs;

import org.exoplatform.listener.ConnecetdUsersListner;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ConnectedUsersJob implements Job {
    private static final Log log = ExoLogger.getLogger(ConnectedUsersJob.class);
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("MyJob is running!!!");
        ConnecetdUsersListner listner = new ConnecetdUsersListner();
        log.info("the number of users is "+listner.getListe().size());
    }
}

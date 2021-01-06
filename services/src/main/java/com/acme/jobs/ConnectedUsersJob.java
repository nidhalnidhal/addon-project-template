package com.acme.jobs;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ConnectedUsersJob implements Job {
    private static final Log log = ExoLogger.getLogger(ConnectedUsersJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("DumbJob is running!!!");
    }
}

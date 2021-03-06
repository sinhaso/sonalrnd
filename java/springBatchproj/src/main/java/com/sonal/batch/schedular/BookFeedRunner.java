package com.sonal.batch.schedular;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookFeedRunner {

    @Autowired
    private Job bookFeedJob;

    @Autowired
    private SimpleJobLauncher jobLauncher;

    public JobExecution startBookFeedJob() {
	JobExecution jobExecution = null;
	try {
	    jobExecution = jobLauncher.run(bookFeedJob, new JobParameters());
	    System.out.println(jobExecution.isRunning());
	} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
	    e.printStackTrace();
	}
	return jobExecution;
    }
}

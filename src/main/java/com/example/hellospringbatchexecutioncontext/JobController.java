package com.example.hellospringbatchexecutioncontext;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class JobController {

    @Inject
    private JobLauncher jobLauncher;

    @Inject
    private Job saveCustomerJob;

    @PostMapping("/runJob")
    public ExitStatus runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters  = new JobParametersBuilder()
                .addString("day", "16-sep-2022 10:22 hrs")
                .toJobParameters();


        JobExecution jobExecution = this.jobLauncher.run(saveCustomerJob, jobParameters);
        System.out.println("Saved customer data yayy!");
        return jobExecution.getExitStatus();
    }
}

package com.sonal.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.sonal.batch.tasklets.IAppTasklet;
import com.sonal.batch.tasklets.JobWrapupTasklet;

@Configuration
@ComponentScan(basePackages = { "com.sonal" })
@EnableBatchProcessing
public class BatchAppConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("taskletA")
    private IAppTasklet step1Tasklet;

    @Autowired
    @Qualifier("taskletD")
    private IAppTasklet step2Tasklet;

    @Autowired
    @Qualifier("taskletB")
    private IAppTasklet step3Tasklet;

    @Autowired
    @Qualifier("taskletC")
    private IAppTasklet step4tasklet;

    @Bean
    public SimpleJobLauncher jobLauncher() {
	SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
	simpleJobLauncher.setJobRepository(jobRepository());
	return simpleJobLauncher;
    }

    @Bean
    public ResourcelessTransactionManager transactionManager() {
	ResourcelessTransactionManager resourcelessTransactionManager = new ResourcelessTransactionManager();
	return resourcelessTransactionManager;
    }

    @Bean
    public JobRepository jobRepository() {
	MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean();
	mapJobRepositoryFactoryBean.setTransactionManager(transactionManager());
	JobRepository jobRepository = null;
	try {
	    jobRepository = mapJobRepositoryFactoryBean.getObject();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return jobRepository;
    }

    @Bean
    public Job taskletOrientedJob() {

	Job job = jobBuilderFactory.get("Job :: Tasklet_Oriented_Job").start(Step1()).next(Step2()).next(Step3()).next(Step4()).next(JobWrapupStep()).build();
	return job;
    }

    public Step Step1() {

	StepBuilder stepBuilder = stepBuilderFactory.get(" Step :: Step1");

	TaskletStep taskletofStep1 = stepBuilder.tasklet(step1Tasklet).build();

	return taskletofStep1;
    }

    @Bean
    public Step Step2() {

	StepBuilder stepBuilder = stepBuilderFactory.get(" Step :: Step2");

	TaskletStep taskletofStep2 = stepBuilder.tasklet(step2Tasklet).build();

	return taskletofStep2;
    }

    @Bean
    public Step Step3() {

	StepBuilder stepBuilder = stepBuilderFactory.get(" Step :: Step3");

	TaskletStep taskletofStep3 = stepBuilder.tasklet(step3Tasklet).build();

	return taskletofStep3;
    }

    @Bean
    public Step Step4() {

	StepBuilder stepBuilder = stepBuilderFactory.get(" Step :: Step4");

	TaskletStep taskletofStep4 = stepBuilder.tasklet(step4tasklet).build();

	return taskletofStep4;
    }

    @Bean
    public Step JobWrapupStep() {

	StepBuilder stepBuilder = stepBuilderFactory.get(" Step :: JobWrapupStep");

	TaskletStep jobWrapupStep = stepBuilder.tasklet(new JobWrapupTasklet()).build();

	return jobWrapupStep;
    }

}
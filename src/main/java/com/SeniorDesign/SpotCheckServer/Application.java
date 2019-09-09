package com.SeniorDesign.SpotCheckServer;

import com.SeniorDesign.SpotCheckServer.Quartz.FireJobs;
import com.SeniorDesign.SpotCheckServer.Quartz.SpotCheckScheduler;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	@Autowired


	public static void main(String[] args)
	{
		FireJobs fireJobs = new FireJobs();
		SpringApplication.run(Application.class, args);
		fireJobs.start();
	}

}

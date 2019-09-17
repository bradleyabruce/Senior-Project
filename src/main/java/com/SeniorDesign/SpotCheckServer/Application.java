package com.SeniorDesign.SpotCheckServer;

import com.SeniorDesign.SpotCheckServer.Quartz.SpotCheckScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args)
	{
		SpotCheckScheduler spotCheckScheduler = new SpotCheckScheduler();
		spotCheckScheduler.startScheduler();
		SpringApplication.run(Application.class, args);
	}

}

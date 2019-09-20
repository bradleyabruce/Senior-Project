package com.SeniorDesign.SpotCheckServer.Quartz;

import com.SeniorDesign.SpotCheckServer.Quartz.Jobs.UpdateParkingLotsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SpotCheckScheduler {
    Logger log = LoggerFactory.getLogger(UpdateParkingLotsJob.class);

    public void startScheduler()
    {
        try

        {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();

            JobDetail job = JobBuilder.newJob(UpdateParkingLotsJob.class)
                    .withIdentity("myJob", "group1")
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInHours(1)
                            .repeatForever())
                    .build();


            scheduler.scheduleJob(job, trigger);

        }
        catch (Exception ex)
        {
            log.error(ex.getLocalizedMessage());
        }
    }
}

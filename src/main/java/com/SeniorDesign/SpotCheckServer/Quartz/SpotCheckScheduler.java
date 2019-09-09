package com.SeniorDesign.SpotCheckServer.Quartz;

import com.SeniorDesign.SpotCheckServer.Quartz.Jobs.UpdateParkingLotsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

public class SpotCheckScheduler
{
    @Bean
    public JobDetail updateParkingLotsJobDetail()
    {
        return  JobBuilder.newJob(UpdateParkingLotsJob.class)
                .withIdentity("updateParkingLotsJob").storeDurably().build();
    }

    @Bean
    public Trigger updateParkingLotsJobTrigger()
    {
        return TriggerBuilder.newTrigger().forJob(updateParkingLotsJobDetail())
                .withIdentity("updateParkingLotsTrigger").withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1)).build();
    }
}

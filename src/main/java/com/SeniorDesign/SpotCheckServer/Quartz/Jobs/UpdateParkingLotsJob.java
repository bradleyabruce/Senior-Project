package com.SeniorDesign.SpotCheckServer.Quartz.Jobs;

import com.SeniorDesign.SpotCheckServer.Services.ParkingLotService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class UpdateParkingLotsJob implements Job
{
    @Autowired
    ParkingLotService parkingLotService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        System.out.println(parkingLotService.updateOpenParking());
    }
}

package com.SeniorDesign.SpotCheckServer.Quartz.Jobs;

import com.SeniorDesign.SpotCheckServer.Controllers.DeviceController;
import com.SeniorDesign.SpotCheckServer.Services.ParkingLotService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class UpdateParkingLotsJob implements Job
{
    @Autowired
    ParkingLotService parkingLotService;
    Logger log = LoggerFactory.getLogger(UpdateParkingLotsJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        log.info("THIS IS HTE UPDATEPARKINGLOTSJOB");
    }
}

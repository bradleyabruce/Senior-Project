package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Controllers.DeviceController;
import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DeviceService
{
    @Autowired
    DeviceRepository deviceRepository;

    Logger log = LoggerFactory.getLogger(DeviceService.class);


    public ResponseEntity getAllDevices()
    {
        return new ResponseEntity(deviceRepository.getDevices(), HttpStatus.OK);
    }

    public ResponseEntity updateDevice(String requestDto)
    {
        //Mapper used to serialize json from api request into object
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        try
        {
            //create new device object
            // ** Note: Make sure api call properties are in camel case (ex: deviceId not DeviceId)
            Device device = mapper.readValue(requestDto, Device.class);
            boolean updateResult = deviceRepository.updateDevice(device);

            if(updateResult)
            {
                return new ResponseEntity("Success", HttpStatus.OK);
            }
            else {
                return new ResponseEntity("Failure - Update Device", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex)
        {
            log.error("Error processing update device");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Exception Failure\nUpdate Device\n" + ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity createDevice(String requestDto)
    {
        //Mapper used to serialize json from api request into object
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        try
        {
            //create new device object
            // ** Note: Make sure api call properties are in camel case (ex: deviceId not DeviceId)
            Device device = mapper.readValue(requestDto, Device.class);
            Integer createResult = deviceRepository.createDevice(device);

            if(createResult != null)
            {
                return new ResponseEntity(createResult, HttpStatus.OK);
            }
            else {
                return new ResponseEntity("Failure - Create Device", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            log.error("Error processing create device");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Exception Failure\nCreate Device\n" + ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

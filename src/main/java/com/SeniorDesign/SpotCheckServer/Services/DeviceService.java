package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Controllers.DeviceController;
import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
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

    public ResponseEntity getDevicesByCompanyID(String requestDto)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            int companyID = mapper.readValue(requestDto, int.class);
            List<Device> devices = deviceRepository.getDevicesByCompanyID(companyID);

            if(devices != null)
            {
                if(devices.size() > 0)
                {
                    return new ResponseEntity(devices, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity("Failure - No devices are linked to this company.", HttpStatus.CONFLICT);
                }
            }
            else{
                return new ResponseEntity("Failure - Exception at device/getDevicesByCompanyID", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception ex)
        {
            return new ResponseEntity("Failure - Exception at device/getDevicesByCompanyID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
                return new ResponseEntity("Failure - Update Device", HttpStatus.CONFLICT);
            }
        }
        catch (Exception ex)
        {
            log.error("Error processing update device");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Exception Failure\nUpdate Device\n" + ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
                return new ResponseEntity("Failure - Create Device", HttpStatus.CONFLICT);
            }
        }
        catch(Exception ex)
        {
            log.error("Error processing create device");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Exception Failure\nCreate Device\n" + ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity adminPortalAssignDevice(String requestDto)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            Device device = mapper.readValue(requestDto, Device.class);
            Device newDevice = deviceRepository.adminPortalAssignDevice(device);

            if(newDevice != null)
            {
                return new ResponseEntity(newDevice, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Failure - Exception at device/adminPortalAssignDevice", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception ex){
            return new ResponseEntity("Failure - Exception at device/adminPortalAssignDevice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity fill(String requestDto)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            int deviceID = mapper.readValue(requestDto, int.class);
            Device filledDevice = deviceRepository.fill(deviceID);

            if(filledDevice != null)
            {
                return new ResponseEntity(filledDevice, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Failure - Exception at device/fill.", HttpStatus.CONFLICT);
            }
        }
        catch(Exception e){
            return new ResponseEntity("Failure - Exception at device/fill.", HttpStatus.CONFLICT);
        }
    }
}

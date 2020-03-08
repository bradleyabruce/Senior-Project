package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Controllers.DeviceController;
import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DeviceService
{
    @Autowired
    DeviceRepository deviceRepository;
    Logger log = LoggerFactory.getLogger(DeviceService.class);

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

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

    public ResponseEntity updateAndReturn(String requestDto)
    {
        //Mapper used to serialize json from api request into object
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        try
        {
            Device device = mapper.readValue(requestDto, Device.class);
            Device updateDevice = deviceRepository.updateAndReturn(device);

            if(updateDevice != null)
            {
                return new ResponseEntity(updateDevice, HttpStatus.OK);
            }
            else {
                return new ResponseEntity("Failure - Exception at device/updateAndReturn", HttpStatus.CONFLICT);
            }
        }
        catch (Exception ex)
        {
            log.error("Error processing update device");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Failure - Exception at device/updateAndReturn", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity removeFromCompany(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            int deviceID = mapper.readValue(requestDto, int.class);
            boolean deleteResult = deviceRepository.removeFromCompany(deviceID);

            if(deleteResult)
            {
                return new ResponseEntity("Success", HttpStatus.OK);
            }
            else {
                return new ResponseEntity("Failure - Exception at RemoveFromCompany", HttpStatus.CONFLICT);
            }
        }
        catch (Exception ex)
        {
            return new ResponseEntity("Failure - Exception at RemoveFromCompany", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity undeploy(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            int deviceID = mapper.readValue(requestDto, int.class);
            boolean undeployResult = deviceRepository.undeploy(deviceID);

            if(undeployResult)
            {
                return new ResponseEntity("Success", HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Failure - Exception as Device/undeploy", HttpStatus.CONFLICT);
            }
        }
        catch(Exception ex)
        {
            return new ResponseEntity("Failure - Exception at Device/undeploy", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity saveImage(String encodedByteArray) {
        try {
            boolean saveResult = deviceRepository.saveImage(encodedByteArray);

            if (saveResult) {
                return new ResponseEntity("Success", HttpStatus.OK);
            } else {
                return new ResponseEntity("Failure - Exception as Device/saveImage", HttpStatus.CONFLICT);
            }
        } catch (Exception ex) {
            return new ResponseEntity("Failure - Exception at Device/saveImage", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity retrieveImageString(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            int deviceID = mapper.readValue(requestDto, int.class);
            String encodedImageString = deviceRepository.retrieveImageString(deviceID);

            if(encodedImageString != null)
            {
                return new ResponseEntity(encodedImageString, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Failure - Exception at Device/retrieveImage", HttpStatus.CONFLICT);
            }
        }
        catch(Exception ex)
        {
            return new ResponseEntity("Failure - Exception at Device/retrieveImage", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

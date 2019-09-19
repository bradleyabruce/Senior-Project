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
import java.util.List;

@Service
public class DeviceService
{
    @Autowired
    DeviceRepository deviceRepository;

    Logger log = LoggerFactory.getLogger(DeviceService.class);



    public List<Device> getAllDevices()
    {
        return deviceRepository.getDevices();
    }

    public ResponseEntity updateDevice(String requestDto)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            Device device = mapper.readValue(requestDto, Device.class);
        }
        catch (Exception ex)
        {
            log.error("Error processing update device");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("updateDevice", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("updateDevice", HttpStatus.OK);

    }
}

package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Models.Devices;
import com.SeniorDesign.SpotCheckServer.Services.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController
{
    @Autowired
    DeviceService deviceService;
    @Autowired
    Devices devices;
    Logger log = LoggerFactory.getLogger(DeviceController.class);

    @RequestMapping(value = "getDevices", method = RequestMethod.GET)
    @ResponseBody
    public List<Device> getDevices()
    {
        devices.setDeviceList(deviceService.getAllDevices());
        return devices.getDeviceList();
    }

    @RequestMapping(value = "updateDevice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateDevice(@RequestBody String requestDto)
    {
        ResponseEntity updateResult = deviceService.updateDevice(requestDto);
        return updateResult;
    }

}

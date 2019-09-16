package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Models.Devices;
import com.SeniorDesign.SpotCheckServer.Services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController
{
    @Autowired
    DeviceService deviceService;
    @Autowired
    Devices devices;

    @RequestMapping(value = "getDevices", method = RequestMethod.GET)
    @ResponseBody
    public List<Device> getDevices()
    {
        devices.setDeviceList(deviceService.getAllDevices());
        return devices.getDeviceList();
    }

}

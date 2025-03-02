package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("device")
public class DeviceController
{
    @Autowired
    DeviceService deviceService;

    Logger log = LoggerFactory.getLogger(DeviceController.class);

    @RequestMapping(value = "getDevices", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getDevices()
    {
        ResponseEntity deviceList = (ResponseEntity) deviceService.getAllDevices();
        return deviceList;
    }

    @RequestMapping(value = "getDevicesByCompanyID", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getDevicesByCompanyID(@RequestBody String requestDto)
    {
        ResponseEntity deviceList = deviceService.getDevicesByCompanyID(requestDto);
        return deviceList;
    }

    @RequestMapping(value = "updateDevice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateDevice(@RequestBody String requestDto)
    {
        ResponseEntity updateResult = deviceService.updateDevice(requestDto);
        return updateResult;
    }

    @RequestMapping(value = "createDevice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createDevice(@RequestBody String requestDto)
    {
        ResponseEntity createResult = deviceService.createDevice(requestDto);
        return createResult;
    }

    @RequestMapping(value = "fill", method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity fill(@RequestBody String requestDto)
    {
        ResponseEntity fillResult = deviceService.fill(requestDto);
        return fillResult;
    }

    @RequestMapping(value = "adminPortalAssignDevice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity adminPortalAssignDevice(@RequestBody String requestDto)
    {
        ResponseEntity assignResult = deviceService.adminPortalAssignDevice(requestDto);
        return assignResult;
    }

    @RequestMapping(value = "updateAndReturn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateAndReturn(@RequestBody String requestDto)
    {
        ResponseEntity updateDevice = deviceService.updateAndReturn(requestDto);
        return updateDevice;
    }

    @RequestMapping(value = "removeFromCompany", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity removeFromCompany(@RequestBody String requestDto)
    {
        ResponseEntity removeResult = deviceService.removeFromCompany(requestDto);
        return removeResult;
    }

    @RequestMapping(value = "undeploy", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity undeploy(@RequestBody String requestDto)
    {
        ResponseEntity undeployResult = deviceService.undeploy(requestDto);
        return undeployResult;
    }

    @RequestMapping(value = "saveImage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveImage(@RequestBody String encodedByteArray)
    {
        ResponseEntity saveResult = deviceService.saveImage(encodedByteArray);
        return saveResult;
    }

    @RequestMapping(value = "retrieveImageString", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getImage(@RequestBody String requestDto)
    {
        ResponseEntity imageString = deviceService.retrieveImageString(requestDto);
        return imageString;
    }

    @RequestMapping(value = "clearImage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity clearImage(@RequestBody String requestDto)
    {
        ResponseEntity clearResult = deviceService.clearImage(requestDto, true);
        return clearResult;
    }

    @RequestMapping(value = "saveSpots", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveSpots(@RequestBody String requestDto)
    {
        ResponseEntity saveResult = deviceService.saveSpots(requestDto);
        return saveResult;
    }

}

package com.SeniorDesign.SpotCheckServer.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parkingLot")
public class ParkingLotController
{
    @RequestMapping(value = "getParkingLot", method = RequestMethod.GET)
    @ResponseBody
    public String getParkingLot()
    {
        return "this was a successfull test";
    }

}

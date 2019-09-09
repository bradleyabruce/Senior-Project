package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLots;
import com.SeniorDesign.SpotCheckServer.Services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parkingLot")
public class ParkingLotController
{
    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    ParkingLots parkingLots;

    @RequestMapping(value = "getParkingLots", method = RequestMethod.GET)
    @ResponseBody
    public List<ParkingLot> getParkingLot()
    {
        parkingLots.setParkingLotList(parkingLotService.getAllParkingLots());
        return parkingLots.getParkingLotList();
    }

}

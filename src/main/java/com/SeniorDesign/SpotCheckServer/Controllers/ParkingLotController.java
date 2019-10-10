package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLots;
import com.SeniorDesign.SpotCheckServer.Services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public ParkingLots getParkingLots()
    {
        parkingLots = parkingLotService.getAllParkingLots();
        return parkingLots;
    }

    @RequestMapping(value = "getNearbyParkingLots", method = RequestMethod.POST)
    @ResponseBody
    public ParkingLots getNearbyParkingLots(@RequestBody String requestDto)
    {
        return parkingLotService.getNearbyParkingLots(requestDto);
    }

    @RequestMapping(value = "getNearbyParkingLotsIos", method = RequestMethod.POST)
    @ResponseBody
    public List<ParkingLot> getNearbyParkingLotsIos(@RequestBody String requestDto)
    {
        ParkingLots lots =  parkingLotService.getNearbyParkingLots(requestDto);
        return lots.getParkingLotList();
    }

}

package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLots;
import com.SeniorDesign.SpotCheckServer.Services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
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
    public ParkingLots getParkingLots()
    {
        parkingLots = parkingLotService.getAllParkingLots();
        return parkingLots;
    }

    //This method returns the exact same data as getParkingLot but in a better format for swift to parse in our iOS app
    @RequestMapping(value = "getParkingLotsIOS", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getParkingLotsIOS()
    {
        return parkingLotService.getAllParkingLotsIOS();
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

    @RequestMapping(value="getParkingLotsByCompanyId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getParkingLotsByCompanyId(@RequestBody String requestDto)
    {
        return parkingLotService.getParkingLotsByCompanyId(requestDto);
    }
}

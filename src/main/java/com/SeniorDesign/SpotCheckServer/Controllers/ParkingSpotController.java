package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import com.SeniorDesign.SpotCheckServer.Services.ParkingSpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parkingSpot")
public class ParkingSpotController {
    Logger log = LoggerFactory.getLogger(ParkingSpotController.class);

    @Autowired
    ParkingSpotService parkingSpotService;
    @Autowired
    ParkingSpot parkingSpot;

    @RequestMapping(value = "updateParkingSpot", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getParkingLot(@RequestBody String requestDto)
    {
       return parkingSpotService.updateParkingSpot(requestDto);
    }
    @RequestMapping(value = "getParkingSpotsByLotId", method = RequestMethod.POST)
    @ResponseBody
    public List<ParkingSpot> getParkingSpotsByLotId(@RequestBody String requestDto)
    {
        return parkingSpotService.getParkingSpotsByLotId(requestDto);
    }

    @RequestMapping(value = "getParkingSpotsByDeviceId", method = RequestMethod.POST)
    @ResponseBody
    public List<ParkingSpot> getParkingSpotsByDeviceId(@RequestBody String requestDto)
    {
        List<ParkingSpot> list = parkingSpotService.getParkingSpotsByDeviceId(requestDto);
        return list;
    }

    @RequestMapping(value = "updateMultipleParkingSpotsAvailabilityBySpotId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateMultipleParkingSpotsAvailabilityBySpotId(@RequestBody String requestDto)
    {
        return parkingSpotService.updateMultipleParkingSpotsAvailabilityBySpotId(requestDto);
    }
}

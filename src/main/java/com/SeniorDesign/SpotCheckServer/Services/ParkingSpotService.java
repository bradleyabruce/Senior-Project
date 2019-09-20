package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Controllers.ParkingSpotController;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingSpotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {
    Logger log = LoggerFactory.getLogger(ParkingSpotService.class);

    @Autowired
    ParkingSpotRepository parkingSpotRepository;
    @Autowired
    ParkingLotService parkingLotService;


    public List<ParkingSpot> getParkingSpotsByLotId(String requestDto)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            ParkingSpot spot = mapper.readValue(requestDto, ParkingSpot.class);
            return parkingSpotRepository.getParkingSpotsByLotId(spot.getLotId());
        }
        catch (Exception ex)
        {
            log.error("Error parsing parking spot");
            log.error(ex.getLocalizedMessage());
           return null;
        }
    }


    public ResponseEntity updateParkingSpot(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        ParkingSpot spot;
        try
        {
            spot = mapper.readValue(requestDto, ParkingSpot.class);
            parkingLotService.updateOpenParkingBySpot(spot);
            parkingSpotRepository.updateParkingSpot(spot);
        }
        catch (Exception ex)
        {
            log.error("Error parsing parking spot");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("updateParking", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("updateParking", HttpStatus.OK);

    }
}

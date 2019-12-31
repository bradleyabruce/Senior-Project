package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Models.*;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingLotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService
{
    @Autowired
    ParkingLotRepository parkingLotRepository;

    public ParkingLots getAllParkingLots()
    {
        return parkingLotRepository.getParkingLots();
    }

    public ParkingLots getNearbyParkingLots(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            SearchRequest user = mapper.readValue(requestDto, SearchRequest.class);
            ParkingLots parkingLots = parkingLotRepository.getNearbyParkingLots(user);
            return parkingLots;
        }
        catch (Exception ex)
        {
            return new ParkingLots();
        }

    }


    public ResponseEntity getAllParkingLotsIOS()
    {
        return new ResponseEntity(parkingLotRepository.getParkingLotsIOS(), HttpStatus.OK);
    }

    public void updateOpenParkingBySpot(ParkingSpot spot)
    {
        //TODO: Somewhere in this block of code is an error that needs to be fixed. Not updating lot usage or parking lot correctly
        int openSpots = getOpenSpotsChange(spot);
        parkingLotRepository.updateOpenParking(spot.getLotId(), openSpots);
        parkingLotRepository.insertLotUsage(spot);

    }

    private int getOpenSpotsChange(ParkingSpot spot) {
        int openSpots = parkingLotRepository.getOpenParkingSpotsByLotId(spot);

        if(spot.isOpen())
        {
            openSpots += 1;
        }
        else
        {
            openSpots -= 1;
        }
        return openSpots;
    }
}

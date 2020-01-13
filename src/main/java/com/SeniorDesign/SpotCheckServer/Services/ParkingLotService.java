package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Models.*;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingLotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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

    public ResponseEntity getParkingLotsByCompanyId(String requestDto)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            int lotID = mapper.readValue(requestDto, int.class);
            List<ParkingLot> list = parkingLotRepository.getParkingLotsByCompanyId(lotID);

            if(list != null)
            {
                if(list.size() > 1)
                {
                    return new ResponseEntity(list, HttpStatus.OK);
                }
                else {
                return new ResponseEntity("Failure - No devices are linked to this company.", HttpStatus.CONFLICT);
                }
            }
            else{
                return new ResponseEntity("Failure - Exception at parkingLots/getParkingLotsByCompanyId", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception ex)
        {
            return new ResponseEntity("Failure - Exception at parkingLots/getParkingLotsByCompanyId", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity updateParkingLot(String requestDto)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            ParkingLot lotToUpdate = mapper.readValue(requestDto, ParkingLot.class);
            ParkingLot updatedLot = parkingLotRepository.updateParkingLot(lotToUpdate);

            if(updatedLot != null){
                return new ResponseEntity(updatedLot, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Failure - Exception at parkingLots/updateParkingLot", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(Exception e){
            return new ResponseEntity("Failure - Exception at parkingLots/updateParkingLot", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

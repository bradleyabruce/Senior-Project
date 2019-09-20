package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService
{
    @Autowired
    ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> getAllParkingLots()
    {
        return parkingLotRepository.getParkingLots();
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

        if(spot.isOpenFlag())
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

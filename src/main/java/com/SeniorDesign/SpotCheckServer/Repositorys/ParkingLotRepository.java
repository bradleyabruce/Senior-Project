package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParkingLotRepository
{
     List<ParkingLot> getParkingLots();

     ParkingLot getParkingLot(int lotId);

     int getOpenParkingSpotsByLotId(ParkingSpot spot);

     void updateParkingLot(ParkingLot lot);

     void updateOpenParking(int lotId, int change);

     void insertLotUsage(ParkingSpot spot);
}

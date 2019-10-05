package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLots;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParkingLotRepository
{
     ParkingLots getParkingLots();

     //for ios return
     List<ParkingLot> getParkingLotsIOS();

     ParkingLot getParkingLot(int lotId);

     int getOpenParkingSpotsByLotId(ParkingSpot spot);

     void updateParkingLot(ParkingLot lot);

     void updateOpenParking(int lotId, int change);

     void insertLotUsage(ParkingSpot spot);
}

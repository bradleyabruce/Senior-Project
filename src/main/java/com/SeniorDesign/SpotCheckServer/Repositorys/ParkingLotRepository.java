package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLots;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import com.SeniorDesign.SpotCheckServer.Models.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParkingLotRepository
{
     ParkingLots getParkingLots();

     ParkingLot getParkingLot(int lotId);

     ParkingLots getNearbyParkingLots(Users user);

     List<ParkingLot> getParkingLotsIOS();

     int getOpenParkingSpotsByLotId(ParkingSpot spot);

     void updateParkingLot(ParkingLot lot);

     void updateOpenParking(int lotId, int change);

     void insertLotUsage(ParkingSpot spot);
}

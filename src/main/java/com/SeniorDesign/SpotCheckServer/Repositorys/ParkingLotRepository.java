package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.*;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.xml.ws.Response;
import java.util.List;

@Component
public interface ParkingLotRepository
{
     ParkingLots getParkingLots();

     //for ios return
     List<ParkingLot> getParkingLotsIOS();

     ParkingLot getParkingLot(int lotId);

     ParkingLots getNearbyParkingLots(SearchRequest user);

     int getOpenParkingSpotsByLotId(ParkingSpot spot);

     void updateParkingLot(ParkingLot lot);

     ResponseEntity createNewParkingLot(ParkingLot lot);

     void updateOpenParking(int lotId, int change);

     void insertLotUsage(ParkingSpot spot);
}

package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.*;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

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

     ParkingLot updateParkingLot(ParkingLot lot);

     void updateOpenParking(int lotId, int change);

     void insertLotUsage(ParkingSpot spot);

     public List<ParkingLot> getParkingLotsByCompanyId(int CompanyID);

     ParkingLot fill(int lotID);

     List<Device> getCamerasDeployedAtParkingLot(ParkingLot lot);

     Boolean delete(ParkingLot lot);
}

package com.SeniorDesign.SpotCheckServer.Models;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ParkingSpots
{
    List<ParkingSpot> parkingSpotList;

    public List<ParkingSpot> getParkingSpotList(){ return parkingSpotList; }

    public void setParkingSpotList(List<ParkingSpot> parkingSpotList){ this.parkingSpotList = parkingSpotList; }

}

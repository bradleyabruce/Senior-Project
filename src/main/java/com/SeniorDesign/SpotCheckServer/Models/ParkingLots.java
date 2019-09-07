package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingLots
{
    List<ParkingLot> parkingLotList;

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }
}

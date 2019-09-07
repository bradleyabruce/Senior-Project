package com.SeniorDesign.SpotCheckServer.Repository;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParkingLotRepository
{
    public List<ParkingLot> getParkingLots();

    public ParkingLot getParkingLot(int lotId);
}

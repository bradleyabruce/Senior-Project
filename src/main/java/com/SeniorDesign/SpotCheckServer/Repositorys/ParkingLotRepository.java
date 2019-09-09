package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParkingLotRepository
{
    public List<ParkingLot> getParkingLots();

    public ParkingLot getParkingLot(int lotId);
}

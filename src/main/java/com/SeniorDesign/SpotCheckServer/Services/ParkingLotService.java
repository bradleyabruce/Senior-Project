package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
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

    public String updateOpenParking()
    {
        return "This was a quartz test";
    }
}

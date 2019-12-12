package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParkingSpotRepository {
    List<ParkingSpot> getParkingSpotsByLotId(int lotId);

    List<ParkingSpot> getParkingSpotsByDeviceId(int deviceID);

    Boolean updateMultipleParkingSpotsAvailabilityBySpotId(ParkingSpot[] spots);

    void updateParkingSpot(ParkingSpot spot);
}

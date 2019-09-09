package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.ParkingLotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcParkingLotRepository implements ParkingLotRepository
{

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    ParkingLotMapper parkingLotMapper;

    private final String  GET_PARKING_LOTS = "SELECT LOT_ID, ADDRESS, CITY, ZIP_CODE, STATE, LOT_NAME, LAT, LONG FROM PARKING_LOT";
    private final String  GET_PARKING_LOTS_BY_ID  = "";

    @Override
    public List<ParkingLot> getParkingLots() {
        try {
            List<ParkingLot> lots = jdbctemplate.query(GET_PARKING_LOTS, parkingLotMapper);
            return lots;
             }
            catch (Exception ex)
            {
                System.out.println(ex.getLocalizedMessage());
                return null;
            }
        }


    @Override
    public ParkingLot getParkingLot(int lotId) {
        return null;
    }
}

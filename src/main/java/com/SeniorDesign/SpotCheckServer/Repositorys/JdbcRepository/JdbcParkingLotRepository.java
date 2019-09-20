package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.OpenSpotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.ParkingLotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.List;

@Component
public class JdbcParkingLotRepository implements ParkingLotRepository
{

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    ParkingLotMapper parkingLotMapper;
    @Autowired
    OpenSpotMapper openSpotMapper;


    private final String  GET_PARKING_LOTS = "SELECT LotId, Address, ZipCode, City, State, LotName, ContactId, Latitude, Longitude, OpenSpots from tParkingLot";
    private final String GET_OPEN_SPOTS_BY_LOT_ID = "SELECT COUNT(LotId) as OpenSpots FROM tSpot where LotId = ? and OpenFlag = 0";
    private final String  GET_PARKING_LOT_BY_ID  = "";
    private final String UPDATE_OPEN_PARKING = "";
    private final String INSERT_LOT_USAGE = "INSERT INTO tLotUsage(SpotId, TimeStamp, ChangeType)" +
                                        " VALUES(?, CAST(CURRENT_TIMESTAMP AS DATETIME), ?)";

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
    public ParkingLot getParkingLot(int lotId)
    {
        ParkingLot lot = (ParkingLot)jdbctemplate.query(GET_PARKING_LOT_BY_ID, parkingLotMapper);
        return lot;
    }

    @Override
    public int getOpenParkingSpotsByLotId(ParkingSpot spot) {

        int spots = jdbctemplate.queryForObject(GET_OPEN_SPOTS_BY_LOT_ID, new Object[] {spot.getLotId()}, Integer.class);
        return spots;
    }

    @Override
    public void updateParkingLot(ParkingLot lot)
    {

    }

    @Override
    public void updateOpenParking(int lotId, int change)
    {
        jdbctemplate.update(UPDATE_OPEN_PARKING, lotId, change);
    }

    @Override
    public void insertLotUsage(ParkingSpot spot) {

        jdbctemplate.update(INSERT_LOT_USAGE, spot.getLotId(), spot.isOpenFlag());
    }
}

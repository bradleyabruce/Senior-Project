package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLots;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.OpenSpotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.ParkingLotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingLotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcParkingLotRepository implements ParkingLotRepository
{
    Logger log = LoggerFactory.getLogger(JdbcParkingLotRepository.class);


    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    ParkingLotMapper parkingLotMapper;
    @Autowired
    OpenSpotMapper openSpotMapper;


    private final String  GET_PARKING_LOTS = "SELECT LotId, Address, ZipCode, City, State, LotName, ContactId, Latitude, Longitude, OpenSpots from tParkingLot";
    private final String GET_OPEN_SPOTS_BY_LOT_ID = "select OpenSpots from tParkingLot where LotId = ?";
    private final String  GET_PARKING_LOT_BY_ID  = "";
    private final String UPDATE_OPEN_PARKING = "UPDATE tParkingLot " +
            "  SET OpenSpots = ?" +
            "  where LotId = ?";
    private final String INSERT_LOT_USAGE = "INSERT INTO tLotUsage(SpotId, TimeStamp, ChangeType)" +
                                        " VALUES(?, CAST(CURRENT_TIMESTAMP AS DATETIME), ?)";

    @Override
    public ParkingLots getParkingLots() {
        try {
            List<ParkingLot> lots = jdbctemplate.query(GET_PARKING_LOTS, parkingLotMapper);
            ParkingLots parkingLots = new ParkingLots();
            parkingLots.setParkingLotList(lots);
            return parkingLots;
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
        //TODO: Add logic to cancel update if we are updating a spot to a status it already exists in
        try {
            jdbctemplate.update(UPDATE_OPEN_PARKING, change, lotId);
        }
        catch (Exception ex)
        {
            log.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public void insertLotUsage(ParkingSpot spot) {

        try {
            jdbctemplate.update(INSERT_LOT_USAGE, spot.getLotId(), spot.isOpenFlag());
        }
        catch (Exception ex)
        {
            log.error(ex.getLocalizedMessage());
        }
    }
}

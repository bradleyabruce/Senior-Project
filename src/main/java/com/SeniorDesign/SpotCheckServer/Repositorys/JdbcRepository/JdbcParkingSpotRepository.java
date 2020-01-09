package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.ParkingSpotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingSpotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class JdbcParkingSpotRepository implements ParkingSpotRepository {

    private final String GET_PARKING_SPOT = "SELECT s.SpotID, s.FloorNum, s.LotID, s.deviceid, s.updatedate, sa.IsOpen, sic.TopLeftXCoordinate, sic.TopLeftYCoordinate, sic.BottomRightXCoordinate, sic.BottomRightYCoordinate FROM tSpot s LEFT JOIN tSpotAvailability sa on s.SpotID = sa.SpotID LEFT JOIN tSpotImageCoordinates sic on s.SpotID = sic.SpotID";
    private final String UPDATE_SPOT_BY_ID = "UPDATE tSpot set IsOpen = ? WHERE SpotId = ?";

    Logger log = LoggerFactory.getLogger(JdbcParkingSpotRepository.class);

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    private ParkingSpotMapper parkingSpotMapper;


    @Override
    public List<ParkingSpot>  getParkingSpotsByLotId(int lotId) {
        try
        {
            String sqlWhere = " WHERE LotID = ?";
            List<ParkingSpot> spots =  jdbctemplate.query(GET_PARKING_SPOT + sqlWhere, parkingSpotMapper, lotId);
            return spots;
        }
        catch (Exception ex)
        {
            log.error("Error getting parking spots for lotId " + lotId);
            log.error(ex.getLocalizedMessage());
            return null;
        }

    }

    @Override
    public List<ParkingSpot> getParkingSpotsByDeviceId(int deviceID) {

        String sqlWhere = " WHERE DeviceID = ?";
        try
        {
            List<ParkingSpot> spots = jdbctemplate.query(GET_PARKING_SPOT + sqlWhere, parkingSpotMapper, deviceID);
            return spots;
        }
        catch(Exception ex)
        {
            log.error("Error getting parking spots for device ID " + deviceID);
            log.error(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Boolean updateMultipleParkingSpotsAvailabilityBySpotId(ParkingSpot[] spots) {

        String sql = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try
        {
            for(ParkingSpot spot : spots)
            {
                String innerSql = "UPDATE tSpotAvailability SET IsOpen = '" + spot.isOpen() + "' WHERE SpotID = " + spot.getSpotId() + "; ";
                sql += innerSql;
            }

            jdbctemplate.update(sql);
            return true;
        }
        catch(Exception ex)
        {
            log.error("Error updating multiple parking spots from Raspberry Pi.");
            log.error(ex.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public void updateParkingSpot(ParkingSpot spot)
    {
        jdbctemplate.update(UPDATE_SPOT_BY_ID, spot.isOpen(), spot.getSpotId());
    }
}

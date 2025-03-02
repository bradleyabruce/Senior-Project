package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.*;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.DeviceMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.OpenSpotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.ParkingLotMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.ParkingLotRepository;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    DeviceMapper deviceMapper;


    private final String  GET_PARKING_LOTS_AND_COORDINATES = "SELECT lot.LotID, lot.Address, lot.ZipCode, lot.City, lot.State, lot.LotName, lot.CompanyID, lot.OpenSpots, lot.TotalSpots, lotCoord.Latitude, lotCoord.Longitude, lotCoord.Coordinates FROM tParkingLot lot INNER JOIN tParkingLotCoordinates lotCoord ON lot.LotID = lotCoord.ParkingLotID";
    private final String GET_OPEN_SPOTS_BY_LOT_ID = "select OpenSpots from tParkingLot where LotId = ?";
    private final String  GET_PARKING_LOT_BY_ID  = "";
    private final String UPDATE_OPEN_PARKING = "UPDATE tParkingLot " +
            "  SET OpenSpots = ?" +
            "  where LotId = ?";
    private final String INSERT_LOT_USAGE = "INSERT INTO tLotUsage(SpotId, TimeStamp, ChangeType)" +
                                        " VALUES(?, CAST(CURRENT_TIMESTAMP AS DATETIME), ?)";
    private final String GET_NEARBY_PARKING_LOTS = "DECLARE @CurrentLocation geography; " +  //Lat, Lon, miles to search
            "SET @CurrentLocation  = geography::Point(?, ?, 4326) " +
            "SELECT * , Coordinates.STDistance(@CurrentLocation) AS Distance FROM tParkingLot " +
            "WHERE Coordinates.STDistance(@CurrentLocation )<= (? * 1609.34) ";



    @Override
    public ParkingLots getParkingLots() {
        try {
            List<ParkingLot> lots = jdbctemplate.query(GET_PARKING_LOTS_AND_COORDINATES, parkingLotMapper);
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
    public ParkingLots getNearbyParkingLots(SearchRequest request)
    {
        try
        {
            List<ParkingLot> lots = jdbctemplate.query(GET_NEARBY_PARKING_LOTS, parkingLotMapper, request.getLat(), request.getLon(), request.getDistanceInMiles());
            ParkingLots parkingLots= new ParkingLots();
            parkingLots.setParkingLotList(lots);
            return parkingLots;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    //This is only for IOS to use
    @Override
    public List<ParkingLot> getParkingLotsIOS() {
        try {
            List<ParkingLot> lots = jdbctemplate.query(GET_PARKING_LOTS_AND_COORDINATES, parkingLotMapper);
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
    public ParkingLot updateParkingLot(ParkingLot lot)
    {
        String sql = "";

            try
            {
                sql = "Update tParkingLot Set Address ='" + lot.getAddress() + "', City = '" + lot.getCity() + "', State = '" + lot.getState() + "', ZipCode = '" + lot.getZipCode() + "', LotName = '" + lot.getLotName() + "' WHERE LotID = " + lot.getLotID() + ";";
                jdbctemplate.update(sql);

                sql = GET_PARKING_LOTS_AND_COORDINATES + " WHERE LotID = ?";
                List<ParkingLot> updatedLots = (List<ParkingLot>) jdbctemplate.query(sql, parkingLotMapper, lot.getLotID());
                if(updatedLots.size() == 1)
                {
                    return updatedLots.get(0);
                }
                else{
                    return null;
                }
            }
            catch (Exception e){
                return null;
            }
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
            jdbctemplate.update(INSERT_LOT_USAGE, spot.getLotId(), spot.isOpen());
        }
        catch (Exception ex)
        {
            log.error(ex.getLocalizedMessage());
        }
    }

    @Override
    public List<ParkingLot> getParkingLotsByCompanyId(int companyID)
    {
        try
        {
            String sqlWhere = " WHERE CompanyID = ?";
            List<ParkingLot> lots =  jdbctemplate.query(GET_PARKING_LOTS_AND_COORDINATES + sqlWhere, parkingLotMapper, companyID);
            return lots;
        }
        catch (Exception ex)
        {
            log.error("Error getting parking spots for companyId " + companyID);
            log.error(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public ParkingLot fill(int lotID)
    {
        String sql = GET_PARKING_LOTS_AND_COORDINATES;
        String where = " WHERE LotID = ?";
        try
        {
            List<ParkingLot> matchingLots = jdbctemplate.query(sql + where, parkingLotMapper, lotID);
            if(matchingLots.size() == 1)
            {
                return matchingLots.get(0);
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Device> getCamerasDeployedAtParkingLot(ParkingLot lot)
    {
        String select = "SELECT d.deviceid, d.devicename, d.localipaddress, d.externalipaddress, d.macaddress, d.lastupdatedate, d.companyid, d.takenewimage, d.deviceStatusID, d.ParkingLotID";
        String from = " FROM tDevice d";
        String join = " LEFT JOIN tParkingLot pl on (d.ParkingLotID = pl.LotID)";
        String where = " WHERE d.CompanyID = ? AND d.DeviceStatusID IN (5,4) AND pl.LotID = ?";
        String sql = select + from + join + where;

        try
        {
            List<Device> deployedCameras = jdbctemplate.query(sql, deviceMapper, lot.getCompanyID(), lot.getLotID());
            return deployedCameras;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public Boolean delete(int parkingLotID)
    {
        //determine if we can delete
        String select = JdbcDeviceRepository.GET_DEVICES;
        String where = " WHERE d.ParkingLotID = ?";
        String sql = select + where;

        try
        {
            List<Device> camerasDeployedToLot = jdbctemplate.query(sql, deviceMapper, parkingLotID);
            if(camerasDeployedToLot.size() < 1)
            {
                String deleteFromtParkingLot = "DELETE FROM tParkingLot WHERE LotID = ?";
                int parkingLotRows = jdbctemplate.update(deleteFromtParkingLot, parkingLotID);

                String deleteFromtParkingLotCoordinates = "DELETE FROM tParkingLotCoordinates WHERE ParkingLotID = ?";
                int parkingLotCoordinateRows = jdbctemplate.update(deleteFromtParkingLotCoordinates, parkingLotID);

                return true;
            }
            else{
                //return false only if cameras still exist
                return false;
            }
        }
        catch(Exception ex) {
            return null;
        }
    }

    @Override
    public ParkingLot create(ParkingLot lot)
    {
        try
        {
            SimpleJdbcInsert jdbcLotInsert = new SimpleJdbcInsert(jdbctemplate);
            jdbcLotInsert.withTableName("tParkingLot").usingGeneratedKeyColumns("LotID");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("LotName", lot.getLotName());
            parameters.put("Address", lot.getAddress());
            parameters.put("City", lot.getCity());
            parameters.put("State", lot.getState());
            parameters.put("ZipCode", lot.getZipCode());
            parameters.put("CompanyID", lot.getCompanyID());
            parameters.put("OpenSpots", 0);
            parameters.put("TotalSpots", 0);

            Number key = jdbcLotInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
            int newParkingLotID = ((Number) key).intValue();
            lot.setLotID(newParkingLotID);

            //TODO insert actual coordinates
            SimpleJdbcInsert jdbcCoordInsert = new SimpleJdbcInsert((jdbctemplate));
            jdbcCoordInsert.withTableName("tParkingLotCoordinates").usingGeneratedKeyColumns("ParkingLotCoordinateID");
            parameters = new HashMap<>();
            parameters.put("ParkingLotID", lot.getLotID());
            parameters.put("Latitude", 0);
            parameters.put("Longitude", 0);

            key = jdbcCoordInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
            int parkingLotCoordinateID = ((Number) key).intValue();
            return lot;
        }

        catch(Exception ex)
        {
            //System.out.println(ex.getMessage());
            return null;
        }
    }
}

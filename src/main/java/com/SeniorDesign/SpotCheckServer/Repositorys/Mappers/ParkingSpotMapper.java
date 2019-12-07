package com.SeniorDesign.SpotCheckServer.Repositorys.Mappers;

import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ParkingSpotMapper implements RowMapper<ParkingSpot> {
    @Override
    public ParkingSpot mapRow(ResultSet resultSet, int i) throws SQLException {
        ParkingSpot spot = new ParkingSpot();

        spot.setSpotId(resultSet.getInt("SpotID"));
        spot.setFloorNum(resultSet.getInt("FloorNum"));
        spot.setLotId(resultSet.getInt("LotID"));
        spot.setOpenFlag(resultSet.getBoolean("OpenFlag"));
        spot.setDeviceId(resultSet.getInt("DeviceID"));
        spot.setTopLeftImageCoordinate(resultSet.getDouble("TopLeftImageCoordinate"));
        spot.setBottomRightImageCoordinate(resultSet.getDouble("BottomRightImageCoordinate"));
        return spot;
    }
}

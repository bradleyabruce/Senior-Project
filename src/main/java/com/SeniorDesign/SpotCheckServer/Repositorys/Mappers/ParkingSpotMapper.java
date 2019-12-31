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
        spot.setIsOpen(resultSet.getBoolean("IsOpen"));
        spot.setDeviceId(resultSet.getInt("DeviceID"));
        spot.setTopLeftXCoordinate(resultSet.getInt("TopLeftXCoordinate"));
        spot.setTopLeftYCoordinate(resultSet.getInt("TopLeftYCoordinate"));
        spot.setBottomRightXCoordinate(resultSet.getInt("BottomRightXCoordinate"));
        spot.setBottomRightYCoordinate(resultSet.getInt("BottomRightYCoordinate"));
        spot.setUpdateDate(resultSet.getDate("UpdateDate"));
        return spot;
    }
}

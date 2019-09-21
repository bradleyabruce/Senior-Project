package com.SeniorDesign.SpotCheckServer.Repositorys.Mappers;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ParkingLotMapper implements RowMapper<ParkingLot>
{

    @Override
    public ParkingLot mapRow(ResultSet resultSet, int i) throws SQLException {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setLotId(resultSet.getLong("LotId"));
        parkingLot.setLotName(resultSet.getString("LotName"));
        parkingLot.setAddress(resultSet.getString("Address"));
        parkingLot.setState(resultSet.getString("State"));
        parkingLot.setZipCode(resultSet.getInt("ZipCode"));
        parkingLot.setLat(resultSet.getBigDecimal("Latitude"));
        parkingLot.setLon(resultSet.getBigDecimal("Longitude"));
        parkingLot.setContactId(resultSet.getInt("ContactId"));
        parkingLot.setOpenSpots(resultSet.getInt("OpenSpots"));

        return parkingLot;

    }
}

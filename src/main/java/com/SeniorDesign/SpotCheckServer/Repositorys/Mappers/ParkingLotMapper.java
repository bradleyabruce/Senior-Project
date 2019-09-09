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
        parkingLot.setLotId(resultSet.getLong("LOT_ID"));
        parkingLot.setLotName(resultSet.getString("LOT_NAME"));
        parkingLot.setAddress(resultSet.getString("ADDRESS"));
        parkingLot.setState(resultSet.getString("STATE"));
        parkingLot.setCity(resultSet.getString("CITY"));
        parkingLot.setZipCode(resultSet.getInt("ZIP_CODE"));
        parkingLot.setLat(resultSet.getLong("LAT"));
        parkingLot.setLon(resultSet.getLong("LONG"));

        return parkingLot;

    }
}

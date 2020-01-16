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
        parkingLot.setLotID(resultSet.getInt("LotId"));
        parkingLot.setLotName(resultSet.getString("LotName"));
        parkingLot.setAddress(resultSet.getString("Address"));
        parkingLot.setCity(resultSet.getString("City"));
        parkingLot.setState(resultSet.getString("State"));
        parkingLot.setZipCode(resultSet.getInt("ZipCode"));
        parkingLot.setLat(resultSet.getBigDecimal("Latitude"));
        parkingLot.setLon(resultSet.getBigDecimal("Longitude"));
        parkingLot.setCompanyID(resultSet.getInt("CompanyId"));
        parkingLot.setOpenSpots(resultSet.getInt("OpenSpots"));
        parkingLot.setTotalSpots(resultSet.getInt("TotalSpots"));

        return parkingLot;
    }
}

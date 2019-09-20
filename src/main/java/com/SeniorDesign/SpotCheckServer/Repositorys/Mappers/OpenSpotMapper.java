package com.SeniorDesign.SpotCheckServer.Repositorys.Mappers;

import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OpenSpotMapper implements RowMapper<ParkingLot> {
    @Override
    public ParkingLot mapRow(ResultSet resultSet, int i) throws SQLException {
        ParkingLot lot = new ParkingLot();
        lot.setOpenSpots(resultSet.getInt("OpenSpots"));
        return lot;

    }
}
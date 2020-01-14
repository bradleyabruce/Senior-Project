package com.SeniorDesign.SpotCheckServer.Repositorys.Mappers;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DeviceMapper implements RowMapper<Device>
{
    @Override
    public Device mapRow(ResultSet resultSet, int i) throws SQLException {
        Device device = new Device();
        device.setDeviceId(resultSet.getLong("DeviceID"));
        device.setDeviceName(resultSet.getString("DeviceName"));
        device.setLocalIpAddress(resultSet.getString("LocalIpAddress"));
        device.setExternalIpAddress(resultSet.getString("ExternalIpAddress"));
        device.setMacAddress(resultSet.getString("MacAddress"));
        device.setLastUpdateDate(resultSet.getDate("LastUpdateDate"));
        device.setCompanyID(resultSet.getInt("CompanyID"));
        device.setTakeNewImage(resultSet.getBoolean("TakeNewImage"));
        device.setIsDeployed(resultSet.getBoolean("IsDeployed"));
        device.setParkingLotID(resultSet.getInt("ParkingLotID"));
        return device;
    }
}

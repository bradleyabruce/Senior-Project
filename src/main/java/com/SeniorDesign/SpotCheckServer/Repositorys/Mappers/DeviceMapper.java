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
        device.setLotId(resultSet.getInt("LotID"));
        device.setFloorNumber(resultSet.getInt("FloorNumber"));
        device.setLastUpdateDate(resultSet.getDate("LastUpdateDate"));
        device.setCompanyId(resultSet.getInt("CompanyId"));

        return device;
    }
}

package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcDeviceRepository implements DeviceRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DeviceMapper deviceMapper;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final String GET_DEVICES_BY_ID = "";

    @Override
    public List<Device> getDevices()
    {
        try
        {
            final String GET_DEVICES = "SELECT DeviceID, DeviceName, LocalIpAddress, ExternalIpAddress, MacAddress, LotID, FloorNumber, LastUpdateDate FROM tDevice";
            List<Device> devices = jdbcTemplate.query(GET_DEVICES, deviceMapper);
            return devices;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean updateDevice(Device device)
    {
        try
        {
            String deviceId = Long.toString(device.getDeviceID());
            Date lastUpdateDate = device.getLastUpdateDate();
            String dateString = dateFormat.format(lastUpdateDate);

            final String updateQuery = "Update tDevice Set LocalIpAddress = '" + device.getLocalIpAddress() + "', ExternalIpAddress = '" + device.getExternalIpAddress() + "', LastUpdateDate = '" + dateString + "' Where DeviceId = " + deviceId + ";";

            jdbcTemplate.execute(updateQuery);
            return true;
        }

        catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public Integer createDevice(Device device)
    {
        try
        {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("tDevice").usingGeneratedKeyColumns("DeviceID");

            Date lastUpdateDate = device.getLastUpdateDate();
            String dateString = dateFormat.format(lastUpdateDate);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DeviceName", device.getDeviceName());
            parameters.put("LocalIpAddress", device.getLocalIpAddress());
            parameters.put("ExternalIpAddress", device.getExternalIpAddress());
            parameters.put("MacAddress", device.getMacAddress());
            parameters.put("LotID", device.getLotId());
            parameters.put("FloorNumber", device.getFloorNumber());
            parameters.put("LastUpdateDate", dateString);

            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
            int newDeviceID = ((Number) key).intValue();
            return newDeviceID;
        }

        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Device getDevice(int deviceId) {
        return null;
    }
}

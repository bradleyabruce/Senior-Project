package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class JdbcDeviceRepository implements DeviceRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DeviceMapper deviceMapper;

    private final String GET_DEVICES = "SELECT DeviceID, DeviceName, LocalIpAddress, ExternalIpAddress, MacAddress, LotID, FloorNumber, LastUpdateDate FROM tDevice";
    private final String GET_DEVICES_BY_ID = "";

    @Override
    public List<Device> getDevices()
    {
        try
        {
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
        String deviceId = Long.toString(device.getDeviceID());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lastUpdateDate = device.getLastUpdateDate();
        String dateString = dateFormat.format(lastUpdateDate);
        String updateQuery = "Update tDevice Set LocalIpAddress = '" + device.getLocalIpAddress() + "', ExternalIpAddress = '" + device.getExternalIpAddress() + "', LastUpdateDate = '" + dateString + "' Where DeviceId = " + deviceId + ";";

        try
        {
            jdbcTemplate.execute(updateQuery);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public Device getDevice(int deviceId) {
        return null;
    }
}

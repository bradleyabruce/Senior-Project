package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
    public Device getDevice(int deviceId) {
        return null;
    }
}

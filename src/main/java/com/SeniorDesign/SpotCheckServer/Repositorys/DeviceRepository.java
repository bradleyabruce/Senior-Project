package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceRepository
{
    public List<Device> getDevices();

    public Device getDevice(int deviceId);

    public boolean updateDevice(Device device);

    public Integer createDevice(Device device);
}

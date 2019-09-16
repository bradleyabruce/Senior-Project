package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceService
{
    @Autowired
    DeviceRepository deviceRepository;

    public List<Device> getAllDevices()
    {
        return deviceRepository.getDevices();
    }

    public String updateDevice(){ return "This is a test"; }
}

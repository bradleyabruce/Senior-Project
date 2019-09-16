package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Devices
{
    List<Device> DeviceList;

    public List<Device> getDeviceList() {
        return DeviceList;
    }
    public void setDeviceList(List<Device> deviceList) {
        this.DeviceList = deviceList;
    }
}

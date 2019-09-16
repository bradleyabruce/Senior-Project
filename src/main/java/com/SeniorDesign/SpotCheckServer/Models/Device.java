package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class Device
{
    Long DeviceId;
    String DeviceName;
    String LocalIpAddress;
    String ExternalIpAddress;
    String MacAddress;
    int LotId;
    int FloorNumber;
    Date LastUpdateDate;

    public long getDeviceID() { return DeviceId; }
    public void setDeviceId(Long deviceId) { this.DeviceId = deviceId; }

    public String getDeviceName() { return DeviceName; }
    public void setDeviceName(String deviceName) { this.DeviceName = deviceName; }

    public String getLocalIpAddress() { return LocalIpAddress; }
    public void setLocalIpAddress(String localIpAddress){ this.LocalIpAddress = localIpAddress; }

    public String getExternalIpAddress() { return ExternalIpAddress; }
    public void setExternalIpAddress(String externalIpAddress){ this.ExternalIpAddress = externalIpAddress; }

    public String getMacAddress() { return MacAddress; }
    public void setMacAddress(String macAddress) { this.MacAddress = macAddress; }

    public int getLotId() { return LotId; }
    public void setLotId(int lotId){ this.LotId = lotId; }

    public int getFloorNumber(){ return FloorNumber; }
    public void setFloorNumber(int floorNumber) { this.FloorNumber = floorNumber; }

    public Date getLastUpdateDate(){ return LastUpdateDate; }
    public void setLastUpdateDate(Date lastUpdateDate){ this.LastUpdateDate = lastUpdateDate; }
}

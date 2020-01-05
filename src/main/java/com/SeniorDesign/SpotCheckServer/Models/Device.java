package com.SeniorDesign.SpotCheckServer.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class Device
{
    private Long DeviceId;
    private String DeviceName;
    private String LocalIpAddress;
    private String ExternalIpAddress;
    private String MacAddress;
    private int LotId;
    private int FloorNumber;
    private Date LastUpdateDate;
    private int CompanyId;
    private boolean TakeNewImage;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getLastUpdateDate(){ return LastUpdateDate; }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setLastUpdateDate(Date lastUpdateDate){ this.LastUpdateDate = lastUpdateDate; }

    public int getCompanyId(){ return CompanyId; }
    public void setCompanyId(int companyId){ this.CompanyId = companyId; }

    public boolean getTakeNewImage(){return TakeNewImage; }
    public void setTakeNewImage(boolean takeNewImage){ this.TakeNewImage = takeNewImage; }
}

package com.SeniorDesign.SpotCheckServer.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class Device
{
    private Integer DeviceID;
    private String DeviceName;
    private String LocalIpAddress;
    private String ExternalIpAddress;
    private String MacAddress;
    private Date LastUpdateDate;
    private Integer CompanyID;
    private Boolean TakeNewImage;
    private Integer DeviceStatusID;
    private Integer ParkingLotID;

    public Integer getDeviceID() { return DeviceID; }
    public void setDeviceID(Integer deviceID) { this.DeviceID = deviceID; }

    public String getDeviceName() { return DeviceName; }
    public void setDeviceName(String deviceName) { this.DeviceName = deviceName; }

    public String getLocalIpAddress() { return LocalIpAddress; }
    public void setLocalIpAddress(String localIpAddress){ this.LocalIpAddress = localIpAddress; }

    public String getExternalIpAddress() { return ExternalIpAddress; }
    public void setExternalIpAddress(String externalIpAddress){ this.ExternalIpAddress = externalIpAddress; }

    public String getMacAddress() { return MacAddress; }
    public void setMacAddress(String macAddress) { this.MacAddress = macAddress; }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getLastUpdateDate(){ return LastUpdateDate; }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setLastUpdateDate(Date lastUpdateDate){ this.LastUpdateDate = lastUpdateDate; }

    public Integer getCompanyID(){ return CompanyID; }
    public void setCompanyID(Integer companyId){ this.CompanyID = companyId; }

    public Boolean getTakeNewImage(){return TakeNewImage; }
    public void setTakeNewImage(Boolean takeNewImage){ this.TakeNewImage = takeNewImage; }

    public Integer getDeviceStatusID(){ return DeviceStatusID; }
    public void setDeviceStatusID(Integer deviceStatusID){ this.DeviceStatusID = deviceStatusID; }

    public Integer getParkingLotID(){ return this.ParkingLotID; }
    public void setParkingLotID(Integer parkingLotID){ this.ParkingLotID = parkingLotID;}
}

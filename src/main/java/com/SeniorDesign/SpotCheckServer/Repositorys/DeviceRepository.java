package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Models.ParkingSpot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceRepository
{
    public List<Device> getDevices();

    public Device getDevice(int deviceId);

    public boolean updateDevice(Device device);

    public Integer createDevice(Device device);

    public List<Device> getDevicesByCompanyID(int companyID);

    public Device adminPortalAssignDevice(Device device);

    public Device fill(int deviceID);

    public Device updateAndReturn(Device device);

    public boolean removeFromCompany(int deviceID);

    public boolean undeploy(int deviceID);

    public boolean saveImage(String encodedByteArray);

    public String retrieveImageString(int deviceID);

    public boolean clearImage(int deviceID, boolean setTakeNewImageToTrue);

    public boolean saveSpots(ParkingSpot[] spots);
 }

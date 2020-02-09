package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Enums.eDeviceStatusID;
import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Models.ParkingLot;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.DeviceMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlType;
import java.sql.Connection;
import java.sql.ResultSet;
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
    final String GET_DEVICES = "SELECT d.deviceid, d.devicename, d.localipaddress, d.externalipaddress, d.macaddress, d.lastupdatedate, d.companyid, d.takenewimage, d.deviceStatusID, d.ParkingLotID FROM tDevice d";

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

    //allows update of local IP, external IP, CompanyID, TakeNewImage, DeviceStatusID, ParkingLotID
    @Override
    public boolean updateDevice(Device device)
    {
        try
        {
            String deviceId = Long.toString(device.getDeviceID());
            Date currentDate = new Date();
            String dateString = dateFormat.format(currentDate);

            String sql = "UPDATE tDevice SET ";
            if(device.getDeviceName() != null){ sql += "DeviceName = '" + device.getDeviceName() + "', "; }
            if(device.getLocalIpAddress() != null){ sql += "LocalIpAddress = '" + device.getLocalIpAddress() + "', "; }
            if(device.getExternalIpAddress() != null){ sql += "ExternalIpAddress = '" + device.getExternalIpAddress() + "', "; }
            sql += "LastUpdateDate = '" + dateString + "', ";
            if(device.getCompanyID() != null){ sql += "CompanyID = '" + device.getCompanyID() + "', "; }
            if(device.getTakeNewImage() != null){ sql+= "TakeNewImage = '" + device.getTakeNewImage() + "', "; }
            if(device.getDeviceStatusID() != null){ sql += "DeviceStatusID = '" + device.getDeviceStatusID() + "', "; }
            if(device.getParkingLotID() != null){ sql += "ParkingLotID = '" + device.getParkingLotID() + "', "; }
            sql = sql.substring(0, sql.length() - 2);
            sql += " WHERE DeviceId = " + deviceId + ";";

            int affectedRows = jdbcTemplate.update(sql);
            if(affectedRows == 1)
            {
                return true;
            }
            else{
                return false;
            }
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

            Date currentDate = new Date();
            String dateString = dateFormat.format(currentDate);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DeviceName", device.getDeviceName());
            parameters.put("LocalIpAddress", device.getLocalIpAddress());
            parameters.put("ExternalIpAddress", device.getExternalIpAddress());
            parameters.put("MacAddress", device.getMacAddress());
            parameters.put("LastUpdateDate", dateString);   //current datetime
            //parameters.put("CompanyID", device.getCompanyID());   null value
            parameters.put("TakeNewImage", false);     //default value
            parameters.put("DeviceStatusID", "1");    //default value
            //parameters.put("ParkingLotID, device.getParkingLotID());    null value

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
    public List<Device> getDevicesByCompanyID(int companyID)
    {
        try
        {
            String sql = GET_DEVICES + " WHERE d.CompanyID = ?";
            List<Device> matchingDevices = jdbcTemplate.query(sql, deviceMapper, companyID);
            return matchingDevices;
        }
        catch(Exception ex){
            return null;
        }
    }

    @Override
    public Device adminPortalAssignDevice(Device device)
    {
        try
        {
            //find device that is not currently assigned to other companies
            String sql = "SELECT TOP 1 d.deviceid, d.devicename, d.localipaddress, d.externalipaddress, d.macaddress, d.lastupdatedate, d.companyid, d.takenewimage, d.deviceStatusID, d.ParkingLotID FROM tDevice d WHERE d.CompanyID IS NULL";
            List<Device> unassignedDevices = jdbcTemplate.query(sql, deviceMapper);

            if(unassignedDevices.size() > 0)
            {
                //update this device with the device info sent to this method
                Device deviceToUpdate = unassignedDevices.get(0);
                deviceToUpdate.setCompanyID(device.getCompanyID());
                deviceToUpdate.setDeviceName(device.getDeviceName());
                deviceToUpdate.setDeviceStatusID(eDeviceStatusID.Undeployed.deviceStatusID);

                Boolean updateResult = updateDevice(deviceToUpdate);
                if(updateResult)
                {
                    //return device with new information set
                    return fill(deviceToUpdate.getDeviceID());
                }
                else{   //update failed
                    return null;
                }
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Device fill(int deviceID) {
        String sql = GET_DEVICES + " WHERE DeviceID = ?";
        try
        {
            List<Device> matchingDevices = jdbcTemplate.query(sql, deviceMapper, deviceID);
            if(matchingDevices.size() == 1)
            {
                return matchingDevices.get(0);
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Device getDevice(int deviceId)
    {
        return null;
    }
}

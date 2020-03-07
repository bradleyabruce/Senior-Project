package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Enums.eDeviceStatusID;
import com.SeniorDesign.SpotCheckServer.Models.Device;
import com.SeniorDesign.SpotCheckServer.Repositorys.DeviceRepository;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    public Device updateAndReturn(Device device)
    {
        Boolean updateResult = updateDevice(device);
        if(updateResult)
        {
            String sql = GET_DEVICES + " WHERE DeviceID = ?";
            try
            {
                List<Device> matchingDevices = jdbcTemplate.query(sql, deviceMapper, device.getDeviceID());
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
        else{   //update failed
            return null;
        }
    }

    @Override
    public boolean removeFromCompany(int deviceID)
    {
        try
        {
            Date currentDate = new Date();
            String dateString = dateFormat.format(currentDate);

            String sql = "UPDATE tDevice SET ";
            sql += "DeviceName = 'Camera', ";
            sql += "DeviceStatusID = '" + eDeviceStatusID.NoCompany.deviceStatusID + "', ";
            sql += "CompanyID = null, ";
            sql += "LocalIpAddress = 'Unset', ";
            sql += "ExternalIpAddress = 'Unset', ";
            sql += "LastUpdateDate = '" + dateString + "', ";
            sql += "TakeNewImage = '0', ";
            sql += "ParkingLotID = null ";

            sql += "WHERE DeviceID = " + deviceID + ";";

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
    public boolean undeploy(int deviceID)
    {
        try
        {
            Date currentDate = new Date();
            String dateString = dateFormat.format(currentDate);

            String sql = "UPDATE tDevice SET ";
            sql += "LastUpdateDate = '" + dateString + "', ";
            sql += "TakeNewImage = '0', ";
            sql += "DeviceStatusID = '" + eDeviceStatusID.Undeployed.deviceStatusID + "', ";
            sql += "ParkingLotID = null ";
            sql += "WHERE DeviceID = " + deviceID + ";";

            boolean deviceUpdateResult = jdbcTemplate.update(sql) > 0;
            if(deviceUpdateResult)
            {
                //device was updated, get the spot ids that used that device
                sql = "SELECT SpotID FROM tSpot WHERE DeviceID = " + deviceID + ";";
                List<Integer> matchingSpotIDs = jdbcTemplate.queryForList(sql, Integer.class);

                if(matchingSpotIDs.size() > 0)
                {
                    sql = "DELETE FROM tSpots WHERE SpotID IN ( " + CreateCommaSeparatedString(matchingSpotIDs) + " );";
                    boolean deleteSpotResult = jdbcTemplate.update(sql) == matchingSpotIDs.size();

                    if(deleteSpotResult)// && deleteCoordinateResult)
                    {
                        //deletes where successful
                        return true;
                    }
                    else{
                        //deletes were not successful
                        return false;
                    }
                }
                else{
                    //no spots to remove, return true
                    return true;
                }
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean saveImage(String encodedByteArray)
    {
        Date currentDate = new Date();
        String dateString = dateFormat.format(currentDate);

        try
        {
            String deviceID = encodedByteArray.substring(2, encodedByteArray.indexOf(')'));
            encodedByteArray = encodedByteArray.substring(encodedByteArray.indexOf(')') + 1);
            encodedByteArray = encodedByteArray.substring(0, encodedByteArray.length() - 1);

            String sql = "SELECT DeviceImageID from tDeviceImages WHERE DeviceID = " + deviceID + ";";
            List<Integer> matchingImageIDs = jdbcTemplate.queryForList(sql, Integer.class);

            //delete existing if they exist
            if(matchingImageIDs.size() > 0)
            {
                sql = "DELETE FROM tDeviceImages WHERE DeviceImageID IN ( " + CreateCommaSeparatedString(matchingImageIDs) + " );";
                jdbcTemplate.update(sql);
            }

            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("tDeviceImages").usingGeneratedKeyColumns("DeviceImageID");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DeviceID", deviceID);
            parameters.put("EncodedImageString", encodedByteArray);
            parameters.put("CreateDate", dateString);

            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

            //ensure that take new device is reset
            Device currentDevice = fill(Integer.parseInt(deviceID));
            currentDevice.setTakeNewImage(false);
            updateDevice(currentDevice);

            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public String retrieveImageString(int deviceID)
    {
        //we need to retrieve the image string, but it might not exist yet so I guess keep trying the same thing until it works...
        try
        {
            String sql = "SELECT EncodedImageString FROM tDeviceImages WHERE DeviceID = ?";
            List<String> encodedImageStrings = jdbcTemplate.queryForList(sql, String.class, deviceID);
            int imageRetrievalAttemptCount = 1;

            //if we did not get the image the first time... keep trying
            while(encodedImageStrings.size() < 1)
            {
                //wait one second and try again
                TimeUnit.SECONDS.sleep(1);
                encodedImageStrings = jdbcTemplate.queryForList(sql, String.class, deviceID);
                imageRetrievalAttemptCount ++;

                if(imageRetrievalAttemptCount > 10)
                {
                    return null;
                }
            }

            //get the string
            String encodedImageString = encodedImageStrings.get(0);

            //clear from the database

            return encodedImageString;
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public Device getDevice(int deviceId)
    {
        return null;
    }

    private String CreateCommaSeparatedString(List<Integer> intList)
    {
        String commaSeparatedString = "";
        for (Integer i : intList)
        {
            commaSeparatedString += i + ",";
        }
        commaSeparatedString = commaSeparatedString.substring(0, commaSeparatedString.length() -1);
        return commaSeparatedString;
    }
}

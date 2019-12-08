package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ParkingSpot {
    private int spotId;
    private int floorNum;
    private int lotId;
    private boolean openFlag;
    private int deviceId;
    private int topLeftXCoordinate;
    private int topLeftYCoordinate;
    private int bottomRightXCoordinate;
    private int bottomRightYCoordinate;
    private Date updateDate;

    public ParkingSpot() {
    }
    //Spot ID
    public int getSpotId() {
        return spotId;
    }
    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    //Floor Number
    public int getFloorNum() {
        return floorNum;
    }
    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    //Lot ID
    public int getLotId() {
        return lotId;
    }
    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    //Is Open
    public boolean isOpenFlag() {
        return openFlag;
    }
    public void setOpenFlag(boolean openFlag) {
        this.openFlag = openFlag;
    }

    //Device ID
    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int deviceID) { this.deviceId = deviceID; }

    //Top Left X Coordinate
    public int getTopLeftXCoordinate() {
        return topLeftXCoordinate;
    }
    public void setTopLeftXCoordinate(int topLeftImageCoordinate) {this.topLeftXCoordinate = topLeftXCoordinate; }

    //Top Left Y Coordinate
    public int getTopLeftYCoordinate(){ return topLeftYCoordinate; }
    public void setTopLeftYCoordinate(int topLeftYCoordinate){ this.topLeftYCoordinate = topLeftYCoordinate; }

    //Bottom Right X Coordinate
    public int getBottomRightXCoordinate(){ return bottomRightXCoordinate; }
    public void setBottomRightXCoordinate(int bottomRightXCoordinate) { this.bottomRightXCoordinate = bottomRightXCoordinate; }

    //Bottom Right Y Coordinate
    public int getBottomRightYCoordinate(){ return bottomRightYCoordinate; }
    public void setBottomRightYCoordinate(int bottomRightYCoordinate){ this.bottomRightYCoordinate = bottomRightYCoordinate; }

    //Update Date
    public Date getUpdateDate(){ return updateDate; }
    public void setUpdateDate(Date updateDate){ this.updateDate = updateDate; }
}

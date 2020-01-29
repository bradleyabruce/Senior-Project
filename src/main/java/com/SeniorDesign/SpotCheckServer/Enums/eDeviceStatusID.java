package com.SeniorDesign.SpotCheckServer.Enums;

public enum eDeviceStatusID
{
    NoCompany (1),
    Undeployed (2),
    WaitingForImage (3),
    ReadyForSpots (4),
    Deployed (5);

    private final int deviceStatusID;

    private eDeviceStatusID(int deviceStatusID)
    {
        this.deviceStatusID = deviceStatusID;
    }
}

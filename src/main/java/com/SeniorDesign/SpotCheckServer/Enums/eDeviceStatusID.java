package com.SeniorDesign.SpotCheckServer.Enums;

public enum eDeviceStatusID
{
    NoCompany (1),
    Undeployed (2),
    WaitingForImage (3),
    ReadyForSpots (4),
    Deployed (5);

    public final int deviceStatusID;

    eDeviceStatusID(int deviceStatusID)
    {
        this.deviceStatusID = deviceStatusID;
    }
}

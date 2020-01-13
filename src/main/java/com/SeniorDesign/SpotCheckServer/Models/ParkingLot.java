package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ParkingLot {
    private Long lotId;
    private String lotName;
    private String address;
    private int zipCode;
    private String city;
    private String state;
    private BigDecimal lat;
    private BigDecimal lon;
    private int CompanyId;
    private int OpenSpots;
    private int TotalSpots;


    public Long getLotID() {
        return lotId;
    }

    public void setLotID(Long lotId) {
        this.lotId = lotId;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public int getCompanyID() {
        return CompanyId;
    }

    public void setCompanyID(int companyId) {
        CompanyId = companyId;
    }

    public int getOpenSpots(){ return OpenSpots; }
    public void setOpenSpots(int openSpots){ OpenSpots = openSpots; }

    public int getTotalSpots(){ return TotalSpots; }
    public void setTotalSpots(int totalSpots){ TotalSpots = totalSpots; }
}

package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Users {
    private String fName;
    private String lName;
    private BigDecimal lat;
    private BigDecimal lon;

    public Users() {
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
}

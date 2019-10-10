package com.SeniorDesign.SpotCheckServer.Models;

import java.math.BigDecimal;

public class SearchRequest
{
    private BigDecimal  lat;
    private BigDecimal  lon;
    private int         distanceInMiles ;


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

    public int getDistanceInMiles() {
        return distanceInMiles;
    }

    public void setDistanceInMiles(int distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }
}

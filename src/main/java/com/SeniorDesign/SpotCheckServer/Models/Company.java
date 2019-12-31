package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;

@Component
public class Company
{
    int CompanyID;
    String CompanyName;
    String Address;
    int ZipCode;
    String City;
    String State;
    String CompanyUsername;
    String CompanyPassword;

    //Company ID
    public int getCompanyID(){return this.CompanyID; }
    public void setCompanyID(int companyID){ this.CompanyID = companyID; }

    //CompanyName
    public String getCompanyName(){return this.CompanyName; }
    public void setCompanyName(String companyName){ this.CompanyName = companyName; }

    //Address
    public String getAddress(){ return this.Address; }
    public void setAddress(String address){ this.Address = address; }

    //ZipCode
    public int getZipCode(){ return this.ZipCode; }
    public void setZipCode(int zipCode){this.ZipCode = zipCode; }

    //City
    public String getCity(){ return this.City; }
    public void setCity(String city){ this.City = city; }

    //State
    public String getState(){ return this.State; }
    public void setState(String state){ this.State = state; }

    //CompanyUsername
    public String getCompanyUsername(){ return this.CompanyUsername; }
    public void setCompanyUsername(String companyUsername){ this.CompanyUsername = companyUsername; }

    //CompanyPassword
    public String getCompanyPassword(){ return this.CompanyPassword; }
    public void setCompanyPassword(String companyPassword){this.CompanyPassword = companyPassword; }

}

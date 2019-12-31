package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
    public void setCompanyPassword(String companyPassword){this.CompanyPassword = HashPassword(companyPassword); }

    private String HashPassword(String password)
    {
        final String SALT = "company-salt-text";

        //salt password before hashing
        String saltedPassword = SALT + password;

        //hash the salted password
        StringBuilder hash = new StringBuilder();

        try
        {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(saltedPassword.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

            for(byte b : hashedBytes)
            {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        }
        catch (NoSuchAlgorithmException e){ return ""; }

        return hash.toString();
    }
}

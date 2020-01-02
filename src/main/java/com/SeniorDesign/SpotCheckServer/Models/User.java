package com.SeniorDesign.SpotCheckServer.Models;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class User {

    //region Properties
    private int UserID;
    private String LastName;
    private String FirstName;
    private String Username;
    private String Password;
    private String EmailAddress;
    private BigDecimal Latitude;
    private BigDecimal Longitude;
    //endregion

    //region Getters and Setters
    //UserId
    public int getUserID(){ return this.UserID; }
    public void setUserID(int userID){ this.UserID = userID; }
    //LastName
    public String getLastName() {
        return this.LastName;
    }
    public void setLastName(String lastName) {
        this.LastName = lastName;
    }
    //FirstName
    public String getFirstName() {
        return this.FirstName;
    }
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }
    //Username
    public String getUsername() { return this.Username; }
    public void setUsername(String username){ this.Username = username; }
    //Password
    public String getPassword() { return this.Password; }
    public void setPassword(String password) { this.Password = HashPassword(password); }
    //EmailAddress
    public String getEmailAddress(){ return this.EmailAddress; }
    public void setEmailAddress(String emailAddress){ this.EmailAddress = emailAddress; }
    //Latitude
    public BigDecimal getLatitude() {
        return this.Latitude;
    }
    public void setLatitude(BigDecimal latitude) {
        this.Latitude = latitude;
    }
    //Longitude
    public BigDecimal getLongitude() {
        return this.Longitude;
    }
    public void setLon(BigDecimal longitude) {
        this.Longitude = longitude;
    }
    //endregion

    //region Methods
    private String HashPassword(String password)
    {
        final String SALT = "user-salt-text";

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
    //endregion
}

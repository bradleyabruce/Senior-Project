package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Models.User;
import com.SeniorDesign.SpotCheckServer.Repositorys.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;
    Logger log = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity signUp(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            User receivedUser = mapper.readValue(requestDto, User.class);
            User insertedUser = userRepository.signUp(receivedUser);

            if(insertedUser != null)
            {
                if(insertedUser.getUserID() != -1)
                {
                    return new ResponseEntity(insertedUser, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity("Failure - " + insertedUser.getUsername(), HttpStatus.CONFLICT);
                }
            }
            else {
                return new ResponseEntity("Failure - Sign Up Company", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            log.error("Error signing up new user");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Failure - Sign Up User", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity login(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            User receivedUser = mapper.readValue(requestDto, User.class);
            User matchedUser = userRepository.login(receivedUser);

            if(matchedUser != null)
            {
                return new ResponseEntity(matchedUser, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Failure - Login User", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            log.error("Error login user");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Failure - Login User", HttpStatus.BAD_REQUEST);
        }
    }
}

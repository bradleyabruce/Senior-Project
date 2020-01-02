package com.SeniorDesign.SpotCheckServer.Controllers;


import com.SeniorDesign.SpotCheckServer.Models.User;
import com.SeniorDesign.SpotCheckServer.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController
{
    @Autowired
    UserService userService;
    @Autowired
    User user;
    Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity signUp(@RequestBody String requestDto)
    {
        ResponseEntity newUser = (ResponseEntity) userService.signUp(requestDto);
        return newUser;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody String requestDto)
    {
        ResponseEntity user = (ResponseEntity) userService.login(requestDto);
        return user;
    }
}

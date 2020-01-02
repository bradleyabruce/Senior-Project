package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.User;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository
{
    User signUp(User user);

    User login(User user);
}

package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.User;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.UserMapper;
import com.SeniorDesign.SpotCheckServer.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcUserRepository implements UserRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    UserMapper userMapper;

    @Override
    public User signUp(User user)
    {
        try {
            //Verify that username is not already in use
            String sql = "SELECT * FROM tUserInfo WHERE UserName = ?";

            List<User> usersWithMatchingUsernames = jdbcTemplate.query(sql, userMapper, user.getUsername());

            if (usersWithMatchingUsernames.isEmpty()) {

                SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
                jdbcInsert.withTableName("tUserInfo").usingGeneratedKeyColumns("UserID");

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("FirstName", user.getFirstName());
                parameters.put("LastName", user.getLastName());
                parameters.put("EmailAddress", user.getEmailAddress());
                parameters.put("UserName", user.getUsername());
                parameters.put("Password", user.getPassword());

                Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
                int newCompanyID = ((Number) key).intValue();
                user.setUserID(newCompanyID);
                return user;
            }
            else{
                User error = new User();
                error.setUserID(-1);
                error.setUsername("User already exists with specified username.");
                return error;
            }
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public User login(User user)
    {
        String sql = "SELECT * FROM tUserInfo WHERE UserName = ? AND Password =  ?";

        List<User> matchingUsers = jdbcTemplate.query(sql, userMapper, user.getUsername(), user.getPassword());

        if(matchingUsers.size() == 1)
        {
            return matchingUsers.get(0);
        }
        else{
            return null;
        }
    }
}

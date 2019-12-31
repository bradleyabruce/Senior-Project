package com.SeniorDesign.SpotCheckServer.Repositorys.JdbcRepository;

import com.SeniorDesign.SpotCheckServer.Models.Company;
import com.SeniorDesign.SpotCheckServer.Repositorys.CompanyRepository;
import com.SeniorDesign.SpotCheckServer.Repositorys.Mappers.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JdbcCompanyRepository implements CompanyRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public Company signUp(Company company)
    {
        try
        {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("tCompany").usingGeneratedKeyColumns("CompanyID");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CompanyName", company.getCompanyName());
            parameters.put("Address", company.getAddress());
            parameters.put("ZipCode", company.getZipCode());
            parameters.put("City", company.getCity());
            parameters.put("State", company.getState());
            parameters.put("CompanyUsername", company.getCompanyUsername());
            parameters.put("CompanyPassword", company.getCompanyPassword());

            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
            int newCompanyID = ((Number) key).intValue();
            company.setCompanyID(newCompanyID);
            return company;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public Company login(Company company) {
        return null;
    }
}

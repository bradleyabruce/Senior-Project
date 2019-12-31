package com.SeniorDesign.SpotCheckServer.Repositorys.Mappers;

import com.SeniorDesign.SpotCheckServer.Models.Company;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class CompanyMapper implements RowMapper<Company>
{
    @Override
    public Company mapRow(ResultSet resultSet, int i) throws SQLException {
        Company company = new Company();
        company.setCompanyID(resultSet.getInt("CompanyID"));
        company.setCompanyName(resultSet.getString("CompanyName"));
        company.setAddress(resultSet.getString("Address"));
        company.setZipCode(resultSet.getInt("ZipCode"));
        company.setCity(resultSet.getString("City"));
        company.setState(resultSet.getString("State"));
        company.setCompanyUsername(resultSet.getString("CompanyUsername"));
        company.setCompanyPassword(resultSet.getString("CompanyPassword"));
        return company;
    }
}

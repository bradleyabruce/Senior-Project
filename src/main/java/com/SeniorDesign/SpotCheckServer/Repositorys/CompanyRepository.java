package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.Company;
import org.springframework.stereotype.Component;

@Component
public interface CompanyRepository
{
    public Company signUp(Company company);

    public Company login(Company company);
}

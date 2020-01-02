package com.SeniorDesign.SpotCheckServer.Repositorys;

import com.SeniorDesign.SpotCheckServer.Models.Company;
import org.springframework.stereotype.Component;

@Component
public interface CompanyRepository
{
    Company signUp(Company company);

    Company login(Company company);
}

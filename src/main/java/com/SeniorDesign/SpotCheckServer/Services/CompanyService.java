package com.SeniorDesign.SpotCheckServer.Services;

import com.SeniorDesign.SpotCheckServer.Models.Company;
import com.SeniorDesign.SpotCheckServer.Repositorys.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService
{
    @Autowired
    CompanyRepository companyRepository;

    Logger log = LoggerFactory.getLogger(CompanyService.class);

    public ResponseEntity signUp(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            Company receivedCompany = mapper.readValue(requestDto, Company.class);
            Company insertedCompany = companyRepository.signUp(receivedCompany);

            if(insertedCompany != null)
            {
                if(insertedCompany.getCompanyID() != -1)
                {
                    return new ResponseEntity(insertedCompany, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity("Failure - " + insertedCompany.getCompanyName(), HttpStatus.CONFLICT);
                }
            }
            else {
                return new ResponseEntity("Failure - Sign Up Company", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            log.error("Error signing up new company");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Failure - Sign Up Company", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity login(String requestDto)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            Company receivedCompany = mapper.readValue(requestDto, Company.class);
            Company matchedCompany = companyRepository.login(receivedCompany);

            if(matchedCompany != null)
            {
                return new ResponseEntity(matchedCompany, HttpStatus.OK);
            }
            else{
                return new ResponseEntity("Failure - Login Company", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            log.error("Error login company");
            log.error(ex.getLocalizedMessage());
            return new ResponseEntity("Failure - Login Company", HttpStatus.BAD_REQUEST);
        }
    }
}



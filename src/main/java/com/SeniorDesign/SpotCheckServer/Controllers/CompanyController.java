package com.SeniorDesign.SpotCheckServer.Controllers;

import com.SeniorDesign.SpotCheckServer.Models.Company;
import com.SeniorDesign.SpotCheckServer.Services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company")
public class CompanyController
{
    @Autowired
    CompanyService companyService;
    @Autowired
    Company company;
    Logger log = LoggerFactory.getLogger(CompanyController.class);

    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity signUp(@RequestBody String requestDto)
    {
        ResponseEntity newCompany = (ResponseEntity) companyService.signUp(requestDto);
        return newCompany;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody String requestDto)
    {
        ResponseEntity company = (ResponseEntity) companyService.login(requestDto);
        return company;
    }
}

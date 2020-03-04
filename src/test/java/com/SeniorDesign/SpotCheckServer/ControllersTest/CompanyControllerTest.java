package com.SeniorDesign.SpotCheckServer.ControllersTest;

import com.SeniorDesign.SpotCheckServer.Controllers.CompanyController;
import com.SeniorDesign.SpotCheckServer.Models.Company;
import com.SeniorDesign.SpotCheckServer.Services.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {

    @InjectMocks
    private CompanyController companyController = new CompanyController();
    @Mock
    private CompanyService companyService;
    @Mock
    Company company;

    @Before
    public void SetUp()
    {

    }

    @Test
    public void signUpTest()
    {

    }

    @Test
    public void loginTest() {
    }
}

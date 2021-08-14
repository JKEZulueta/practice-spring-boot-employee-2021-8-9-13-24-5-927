package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown(){
        companyRepository.deleteAll();
    }


    @Test
    void should_return_employees_when_get_employees_by_company_id_given_company() throws Exception{
        List<Employee> myEmployees = new ArrayList<>();
        Company company = new Company(1, "KyleComp", myEmployees);
        myEmployees.add(new Employee(1, "Kyle", 23, "Male", 5000));
        companyRepository.save(company);

        Integer id = company.getCompanyId();
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees").exists());

    }
}

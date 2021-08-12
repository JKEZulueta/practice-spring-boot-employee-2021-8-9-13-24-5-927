package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompanyById(@PathVariable Integer companyId){
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeeByCompanyId(@PathVariable Integer companyId){
        return companyService.getEmployeeByCompanyId(companyId);
    }
    
    @PostMapping
    public Company createNewCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

}

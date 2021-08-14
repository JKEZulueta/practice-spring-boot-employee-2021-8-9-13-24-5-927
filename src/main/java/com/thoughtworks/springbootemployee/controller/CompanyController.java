package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company findById(@PathVariable Integer companyId) {
        return companyService.findById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeeByCompanyId(@PathVariable Integer companyId){
        return companyService.getEmployeeByCompanyId(companyId);
    }

    @PostMapping
    public Company createNewCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @PutMapping(path = "/{companyId}")
    public Company updateCompanyById(@PathVariable Integer companyId, @RequestBody Company updatedCompany){
        return companyService.update(companyId, updatedCompany);
    }

    @DeleteMapping(path = "/{companyId}")
    public Company delete(@PathVariable Integer companyId){
        return companyService.delete(companyId);
    }



}

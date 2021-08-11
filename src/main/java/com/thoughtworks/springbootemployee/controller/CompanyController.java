package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final List<Company> companies = new ArrayList<>();

    public CompanyController(){
        companies.add(new Company(1,"KyleCompany"));
        companies.add(new Company(2, "MagicCompany"));
        companies.add(new Company(3, "WishWashCompany"));
    }

    @GetMapping
    public List<Company> getAllCompany(){
        return companies;
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompanyById(@PathVariable Integer companyId){
        return companies.stream()
                .filter((company -> company.getCompanyId().equals(companyId)))
                .findFirst()
                .orElse(null);
    }
}

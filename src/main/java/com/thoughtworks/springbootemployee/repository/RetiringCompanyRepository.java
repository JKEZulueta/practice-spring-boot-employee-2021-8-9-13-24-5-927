package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RetiringCompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public RetiringCompanyRepository(){
        companies.add(new Company(1, "KyleCompany", Arrays
                .asList(new Employee(1, "Kyle", 23, "Male", 3000),
                        new Employee(2, "Robert", 23, "Male", 5000))));
    }

    public List<Company> getAll(){
        return companies;
    }

    public Company getCompanyById(Integer companyId){
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

}

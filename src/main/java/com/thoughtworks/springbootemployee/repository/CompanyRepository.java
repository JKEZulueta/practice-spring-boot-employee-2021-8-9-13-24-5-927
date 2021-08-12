package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public List<Company> getAllCompany(){
        return companies;
    }

    public Company getCompanyById(Integer companyId){
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId){
        EmployeeRepository employeeRepository = new EmployeeRepository();
        return employeeRepository.getAll().stream()
                .filter(employee -> employee.getId().equals(companyId))
                .collect(Collectors.toList());
    }

}

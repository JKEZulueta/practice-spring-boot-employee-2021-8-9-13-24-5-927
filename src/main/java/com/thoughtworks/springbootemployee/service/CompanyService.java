package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompany(){
        return companyRepository.getAllCompany();
    }

    public Company getCompanyById(Integer companyId){
        return companyRepository.getCompanyById(companyId);
    }

    public Company findEmpyById(Integer companyId){
        return companyRepository.getAllCompany()
                .stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findAny()
                .orElse(null);
    }

    public List<Employee> getEmployeeByCompanyId(Integer companyId){
        return findEmpyById(companyId).getEmployees();
    }

    public Company addCompany(Company company){
        return companyRepository.addCompany(company);
    }

}

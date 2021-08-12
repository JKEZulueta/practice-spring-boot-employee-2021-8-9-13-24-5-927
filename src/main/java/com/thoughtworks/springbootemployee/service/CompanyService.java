package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Employee> getEmployeesByCompanyId(Integer companyId){
        return companyRepository.getEmployeesByCompanyId(companyId);
    }

}

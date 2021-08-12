package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private RetiringCompanyRepository retiringCompanyRepository;


    private CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company findById(Integer companyId) {
        return companyRepository.findAllByCompanyId(companyId);
    }

    public Company findEmptyById(Integer companyId){
        return retiringCompanyRepository.getAll()
                .stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findAny()
                .orElse(null);
    }

    public List<Employee> getEmployeeByCompanyId(Integer companyId){
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }



    public Company update(Integer companyId, Company updateCompanyDetails) {
        return retiringCompanyRepository.getAll().stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .map(company -> updateCompanyInformation(company, updateCompanyDetails))
                .orElse(null);
    }

    private Company updateCompanyInformation(Company company, Company companyUpdate) {
        if (companyUpdate.getCompanyName() != null) {
            company.setCompanyName(companyUpdate.getCompanyName());
        }
        if (!companyUpdate.getEmployees().isEmpty() && companyUpdate.getEmployees() != null) {
            company.setEmployees(companyUpdate.getEmployees());
        }
        return company;
    }


}

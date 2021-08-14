package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiringCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private RetiringCompanyRepository retiringCompanyRepository;
    final String companyException = "Company ID not found.";


    private CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company findById(Integer companyId) {
        return companyRepository.findById(companyId).orElseThrow(() ->
                new CompanyNotFoundException(companyException));
    }


    public List<Employee> getEmployeeByCompanyId(Integer companyId){
        Company company = companyRepository.findById(companyId).orElseThrow(() ->
                new CompanyNotFoundException(companyException));
        return company.getEmployees();
    }

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }

    public Company update(Integer companyId, Company updateCompanyDetails) {
        Company updateById = companyRepository.findById(companyId).orElseThrow(() ->
                new CompanyNotFoundException(companyException));

        return updateCompanyInformation(updateById, updateCompanyDetails);
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

    public Company delete(Integer companyId){
        Company toRemove = companyRepository.findById(companyId).orElseThrow(() ->
                new CompanyNotFoundException(companyException));
        companyRepository.deleteById(companyId);
        return toRemove;
    }


}

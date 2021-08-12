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


    public CompanyService(RetiringCompanyRepository retiringCompanyRepository){
        this.retiringCompanyRepository = retiringCompanyRepository;
    }

    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId){
        return retiringCompanyRepository.getCompanyById(companyId);
    }

    public Company findEmptyById(Integer companyId){
        return retiringCompanyRepository.getAllCompany()
                .stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findAny()
                .orElse(null);
    }

    public List<Employee> getEmployeeByCompanyId(Integer companyId){
        return findEmptyById(companyId).getEmployees();
    }

    public Company addCompany(Company company){
        return companyRepository.save(company);
    }



    public Company update(Integer companyId, Company updateCompanyDetails) {
        return retiringCompanyRepository.getAllCompany().stream()
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

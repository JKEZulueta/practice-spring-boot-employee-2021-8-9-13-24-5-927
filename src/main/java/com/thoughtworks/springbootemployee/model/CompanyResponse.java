package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public class CompanyResponse {
    private Integer companyId;
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;

    public CompanyResponse(){

    }

    public CompanyResponse(Integer companyId, String companyName, Integer employeeNumber, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;

    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

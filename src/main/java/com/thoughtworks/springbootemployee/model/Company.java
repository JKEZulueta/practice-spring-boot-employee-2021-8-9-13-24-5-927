package com.thoughtworks.springbootemployee.model;

public class Company {
    private Integer companyId;
    private String companyName;
    private Integer employeeId;
    private String employeeName;

    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Company(){

    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}

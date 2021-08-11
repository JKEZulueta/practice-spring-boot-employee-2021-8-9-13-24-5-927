package com.thoughtworks.springbootemployee.model;

public class Company {
    private Integer companyId;
    private String companyName;
    private Integer employeeId;
    private String employeeName;

    public Company(){

    }

    public Company(Integer companyId, String companyName, Integer employeeId, String employeeName) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}

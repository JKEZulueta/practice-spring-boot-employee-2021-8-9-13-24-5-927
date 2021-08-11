package com.thoughtworks.springbootemployee.model;

public class Company {
    private Integer companyId;
    private String companyName;

    public Company(){

    }
    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}

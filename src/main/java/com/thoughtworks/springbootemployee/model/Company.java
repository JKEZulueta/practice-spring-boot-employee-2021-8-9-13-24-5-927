package com.thoughtworks.springbootemployee.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    private String companyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Employee> employees = new ArrayList<>();

    public Company(Integer companyId, String companyName, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company(){

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }
}

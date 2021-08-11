package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAll();
    }

    public Employee create(Employee employee){
        return employeeRepository.saveInto(employee);
    }

    public Employee findById(Integer employeeId){
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> findByGender(String gender){
        return employeeRepository.findByGender(gender);
    }
}

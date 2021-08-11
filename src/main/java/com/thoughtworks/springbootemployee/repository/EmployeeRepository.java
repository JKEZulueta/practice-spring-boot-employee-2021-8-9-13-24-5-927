package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Bert", 25, "Male", 100));
        employees.add(new Employee(2, "Kyle", 25, "Male", 100));
    }

    public List<Employee> getAll(){
        return employees;
    }

    public Employee findById(Integer employeeId){
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public Employee add(Employee employee){
        employees.add(employee);
        return employee;
    }
}

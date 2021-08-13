package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RetiredEmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public RetiredEmployeeRepository() {
        employees.add(new Employee(1, "Bert", 25, "Male", 10000));
        employees.add(new Employee(2, "Kyle", 23, "Male", 5000));
    }

    public List<Employee> getAll(){
        return employees;
    }

//    public Employee findById(Integer employeeId){
//        return employees.stream()
//                .filter(employee -> employee.getId().equals(employeeId))
//                .findFirst()
//                .orElse(null);
//    }
//
//
//    public List<Employee> findByGender(String gender){
//        return null;
//    }
////
//    public Employee updateById(Integer employeeId, Employee updatedEmployee){
//        employees.stream()
//                .filter(employee -> employee.getId().equals(employeeId))
//                .findFirst().ifPresent(employee -> {employees.remove(employee);
//                employees.add(updatedEmployee);
//                });
//        return updatedEmployee;
//    }
////
////
//    public List<Employee> getByPage(Integer pageIndex, Integer pageSize){
//        return employees.stream()
//                .skip((pageSize - 1)* pageIndex)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
}

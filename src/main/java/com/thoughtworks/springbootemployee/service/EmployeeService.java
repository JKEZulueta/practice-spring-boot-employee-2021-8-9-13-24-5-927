package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiredEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private RetiredEmployeeRepository retiredEmployeeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(RetiredEmployeeRepository retiredEmployeeRepository){
        this.retiredEmployeeRepository = retiredEmployeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee create(Employee employee){
       return employeeRepository.save(employee);
    }

    public Employee findById(Integer employeeId){
        Optional<Employee> findById = employeeRepository.findById(employeeId);
        employeeRepository.findById(employeeId);
        return findById.orElse(null);
    }

    public Employee deleteById(Integer employeeId){
        Optional<Employee> toBeRemove = employeeRepository.findById(employeeId);
        employeeRepository.deleteById(employeeId);
        return toBeRemove.orElse(null);
    }

    public List<Employee> findAllByGender(String gender){
        return employeeRepository.findAllByGender(gender);
    }

    public Employee updateById(Integer employeeId, Employee employee){
        Employee updateById = employeeRepository.findById(employeeId).orElse(null);

        return employeeRepository.save(updateById);

    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize){
        return employeeRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
    }



//    public List<Employee> getByPage(Integer pageIndex, Integer pageSize){
//        return retiredEmployeeRepository.getByPage(pageIndex, pageSize);
//    }
}

package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.entity.Employee;
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

        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found."));

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
        if(updateById != null){
            updateEmployeeInformation(updateById, employee);
            return create(updateById);
        }
        return null;
    }

    private void updateEmployeeInformation(Employee employee, Employee employeeUpdate) {
        if(employeeUpdate.getAge() != null) {
            employee.setAge(employeeUpdate.getAge());
        }
        if (employeeUpdate.getGender() != null) {
            employee.setGender(employeeUpdate.getGender());
        }
        if (employeeUpdate.getSalary() != null) {
            employee.setSalary(employeeUpdate.getSalary());
        }
        if (employeeUpdate.getName() != null) {
            employee.setName(employeeUpdate.getName());
        }
        if (employeeUpdate.getCompanyId() != null) {
            employee.setCompanyId(employeeUpdate.getCompanyId());
        }
    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize){
        return employeeRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
    }



//    public List<Employee> getByPage(Integer pageIndex, Integer pageSize){
//        return retiredEmployeeRepository.getByPage(pageIndex, pageSize);
//    }
}

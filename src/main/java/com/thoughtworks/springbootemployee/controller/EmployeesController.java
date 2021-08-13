package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    private final List<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeesController() {
        employees.add(new Employee(1, "Bert", 25, "Male", 100));
        employees.add(new Employee(2, "Kyle", 25, "Male", 100));
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse findById(@PathVariable Integer employeeId) {
        return employeeMapper.toResponse(employeeService.findById(employeeId));
    }

    @GetMapping(params = "gender")
    public List<Employee> findByGender(@RequestParam("gender") String gender) {
        return employeeService.findAllByGender(gender);
    }

    @DeleteMapping("/{employeeId}")
    public Employee delete(@PathVariable Integer employeeId) {
        return employeeService.deleteById(employeeId);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getListByPagination(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        return employeeService.getEmployeesByPagination(pageIndex, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@RequestBody EmployeeRequest employeeRequest) {
       return employeeMapper.toResponse(employeeService.create(employeeMapper.toEntity(employeeRequest)));
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeResponse update(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.updateById(employeeId, employeeMapper.toEntity(employeeRequest)));
    }

    private Employee updateEmployeeInformation(Employee employee, Employee employeeUpdate) {
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
        return employee;
    }

}

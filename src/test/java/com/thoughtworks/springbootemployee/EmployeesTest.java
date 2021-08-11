package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeesTest {

    @InjectMocks
    EmployeeService service;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        List<Employee> expectedEmployees = Arrays.asList(new Employee(),new Employee());
        given(service.getAllEmployees()).willReturn(expectedEmployees);
        List<Employee> outputEmployees = service.getAllEmployees();
        assertEquals(outputEmployees, expectedEmployees);
    }

    @Test
    void should_return_employee_when_getEmployeeById_given_all_employees() {
        Employee employee = new Employee(3, "Mamba", 18, "Male", 1500);
        when(employeeRepository.findById(employee.getId())).thenReturn(employee);
        EmployeeService service = new EmployeeService(employeeRepository);

        Employee actualEmployee = service.findById(employee.getId());

        assertEquals(employee.getId(), actualEmployee.getId());

    }
}

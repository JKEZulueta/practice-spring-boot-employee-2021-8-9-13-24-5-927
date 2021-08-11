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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
    void should_return_employee_when_add_an_employee_given_employee() {
        Employee employee = new Employee(1, "Mamba", 18, "Male", 1500);
        EmployeeService service = new EmployeeService(employeeRepository);
        when(employeeRepository.saveInto(employee)).thenReturn(employee);

        Employee actualEmployee = service.create(employee);

        assertEquals(employee, actualEmployee);
    }

    @Test
    void should_return_employee_when_getEmployeeById_given_all_employees() {
        Employee employee = new Employee(3, "Mamba", 18, "Male", 1500);
        when(employeeRepository.findById(employee.getId())).thenReturn(employee);
        EmployeeService service = new EmployeeService(employeeRepository);

        Employee actualEmployee = service.findById(employee.getId());

        assertEquals(employee.getId(), actualEmployee.getId());

    }

    @Test
    void should_return_employee_by_gender_when_getEmployeeByGender_given_all_employees() {
        Employee firstEmployee = new Employee(3, "Mamba", 18, "Male", 1500);
        Employee secondEmployee = new Employee(4, "Mentalitee", 21, "Female", 2000);
        when(employeeRepository.findByGender("Female")).thenReturn(Arrays.asList(firstEmployee, secondEmployee));
        EmployeeService service = new EmployeeService(employeeRepository);

        List<Employee> actualEmployee = service.findByGender("Female");

        assertEquals(Arrays.asList(firstEmployee, secondEmployee), actualEmployee);
    }

    @Test
    void should_return_updated_employee_when_employee_update_by_id_given_employee_id() {
        Employee normalEmployee = new Employee(3, "Mamba", 18, "Male", 1500);
        Employee updateEmployee = new Employee(4, "Mentalitee", 21, "Female", 2000);
        when(employeeRepository.updateById(normalEmployee.getId(), normalEmployee)).thenReturn(updateEmployee);
        EmployeeService service = new EmployeeService(employeeRepository);

        Employee actualEmployee = service.updateById(normalEmployee.getId(), normalEmployee);

        assertNotEquals(normalEmployee.getSalary(), actualEmployee.getSalary());

    }

    @Test
    void should_return_nothing_when_employee_deletedById_given_employee_id() {
        Employee employee = new Employee(3, "Mamba", 18, "Male", 1500);
        EmployeeService service = new EmployeeService(employeeRepository);

        service.deleteById(employee.getId());

        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }
}

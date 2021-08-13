package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.RetiredEmployeeRepository;
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

@ExtendWith(MockitoExtension.class)
public class EmployeesTest {

    @InjectMocks
    EmployeeService service;

    @Mock
    RetiredEmployeeRepository retiredEmployeeRepository;

    @Test
    void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        List<Employee> expectedEmployees = Arrays.asList(new Employee(),new Employee());
        given(service.getAllEmployees()).willReturn(expectedEmployees); //repository
        List<Employee> outputEmployees = service.getAllEmployees();
        assertEquals(outputEmployees, expectedEmployees);
    }

    @Test
    void should_return_employee_when_add_an_employee_given_employee() {
//        Employee employee = new Employee(1, "Mamba", 18, "Male", 1500);
//        EmployeeService service = new EmployeeService(retiredEmployeeRepository);
//        when(retiredEmployeeRepository.create(employee)).thenReturn(employee);

        //Employee actualEmployee = service.create(employee);

        //assertEquals(employee, actualEmployee);
    }

    @Test
    void should_return_employee_when_getEmployeeById_given_all_employees() {
//        Employee employee = new Employee(3, "Mamba", 18, "Male", 1500);
//        when(retiredEmployeeRepository.findById(employee.getId())).thenReturn(employee);
//        EmployeeService service = new EmployeeService(retiredEmployeeRepository);
//
//        Employee actualEmployee = service.findById(employee.getId());
//
//        assertEquals(employee.getId(), actualEmployee.getId());

    }

    @Test
    void should_return_employee_by_gender_when_getEmployeeByGender_given_all_employees() {
//        Employee firstEmployee = new Employee(3, "Mamba", 18, "Male", 1500);
//        Employee secondEmployee = new Employee(4, "Mentalitee", 21, "Female", 2000);
//        when(retiredEmployeeRepository.findByGender("Female")).thenReturn(Arrays.asList(firstEmployee, secondEmployee));
//        EmployeeService service = new EmployeeService(retiredEmployeeRepository);
//
//        List<Employee> actualEmployee = service.findByGender("Female");
//
//        assertEquals(Arrays.asList(firstEmployee, secondEmployee), actualEmployee);
    }

    @Test
    void should_return_updated_employee_when_employee_update_by_id_given_employee_id() {
//        Employee normalEmployee = new Employee(3, "Mamba", 18, "Male", 1500);
//        Employee updateEmployee = new Employee(4, "Mentalitee", 21, "Female", 2000);
//        when(retiredEmployeeRepository.updateById(normalEmployee.getId(), normalEmployee)).thenReturn(updateEmployee);
//        EmployeeService service = new EmployeeService(retiredEmployeeRepository);
//
//        Employee actualEmployee = service.updateById(normalEmployee.getId(), normalEmployee);
//
//        assertNotEquals(normalEmployee.getSalary(), actualEmployee.getSalary());

    }

    @Test
    void should_return_nothing_when_employee_deletedById_given_employee_id() {
//        Employee employee = new Employee(3, "Mamba", 18, "Male", 1500);
//        EmployeeService service = new EmployeeService(retiredEmployeeRepository);
//
//        service.deleteById(employee.getId());
//
//        verify(retiredEmployeeRepository, times(1)).deleteById(employee.getId());
    }

    @Test
    void should_return_page_1_size_5_when_getBy_page_given_5_employees() {
//        List<Employee> employees = Arrays.asList(new Employee(), new Employee(), new Employee(),
//                new Employee(), new Employee());
//        when(retiredEmployeeRepository.getByPage(1, 5)).thenReturn(employees);
//        EmployeeService service = new EmployeeService(retiredEmployeeRepository);
//
//        List<Employee> actualEmployee = service.getByPage(1, 5);
//
//        assertEquals(5, actualEmployee.size());
    }
}

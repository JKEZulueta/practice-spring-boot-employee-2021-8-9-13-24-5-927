package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //Given
        final Employee employee = new Employee(1, "Kyle", 23, "male", 9999);
        employeeRepository.save(employee);

        //When

        //Then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Kyle"))
                .andExpect(jsonPath("$[0].age").value(23))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(9999));
    }

    @Test
    void should_create_employee_when_call_create_employee_api() throws Exception {
        //Given
        String employee = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\":  \"Kyle\",\n" +
                "    \"age\": 23, \n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 9999\n" +
                "}";

//        System.out.println(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(jsonPath("$.name").value("Kyle"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("9999"));
        //Then
    }

    @Test
    void should_update_employee_when_call_update_employee_api() throws Exception {
        //Given
        final Employee employee = new Employee(1, "Kyle", 23, "male", 9999);
        final Employee savedEmployee = employeeRepository.save(employee);
        String employeeWithNewInfo = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\":  \"Kyle\",\n" +
                "    \"age\": 21, \n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 8888\n" +
                "}";
        //when


        //Then
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(employeeWithNewInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kyle"))
                .andExpect(jsonPath("$.age").value("21"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("8888"));

    }
}

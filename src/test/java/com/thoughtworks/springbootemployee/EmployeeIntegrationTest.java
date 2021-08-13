package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
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
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id) //Id since only used once
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(employeeWithNewInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kyle"))
                .andExpect(jsonPath("$.age").value("21"))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value("8888"));

    }

    @Test
    void should_delete_employee_when_delete_given_employee_id_delete_api() throws Exception {
        //Given
        final Employee employee = new Employee(1, "Kyle", 23, "male", 9999);
        final Employee savedEmployee = employeeRepository.save(employee); //Delete
        //When
        //Then
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}",  id)) //Check the value if != exist $[0].id).doesNotExist())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kyle"))
                .andExpect(jsonPath("$.age").value(23))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(9999));

    }

    @Test
    void should_return_employee_when_findAllByGender_given_employee_param_gender() throws Exception{
        String gender = "female";

        final Employee firstEmployee = new Employee(1, "Kyle", 25, "male", 1000);
        final Employee secondEmployee = new Employee(2, "Kylaver", 19, "female", 500);
        final Employee thirdEmployee = new Employee(3, "Jan", 26, "male", 1000);
        employeeRepository.saveAll(Lists.list(firstEmployee, secondEmployee, thirdEmployee));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Kylaver"));

    }

    @Test
    void should_return_employee_when_findById_given_employee_with_id() throws Exception{
        final Employee firstEmployee = new Employee(1, "Kyle", 25, "male", 1000);
        final Employee secondEmployee = new Employee(2, "Kylaver", 19, "female", 500);
        final Employee thirdEmployee = new Employee(3, "Jan", 26, "male", 1000);
        employeeRepository.saveAll(Lists.list(firstEmployee, secondEmployee, thirdEmployee));

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kylaver"))
                .andExpect(jsonPath("$.age").value(19))
                .andExpect(jsonPath("$.salary").value(500))
                .andExpect(jsonPath("$.gender").value("female"));
    }
}

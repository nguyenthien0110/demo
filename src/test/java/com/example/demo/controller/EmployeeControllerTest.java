package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.model.Employee;
import com.example.demo.response.EmployeeGetAllResponse;
import com.example.demo.service.EmployeeService;

@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeControllerTest {

  private static final String URL_GETALL = "/api/employee/getAll";

  @Autowired
  protected MockMvc mvc;

  @MockBean
  private EmployeeService employeeService;

  List<Employee> listEmployee = new ArrayList<>();

  @BeforeEach
  public void setup() {
    Employee employee;
    for (int i = 0; i < 5; i++) {
      employee =
          Employee.builder().name("Name " + i).age(15).email("Name" + i + "@gmail.com").build();
      listEmployee.add(employee);
    }
  }

  @Test
  public void testGetAllNormal_1() throws Exception {
    EmployeeGetAllResponse response = EmployeeGetAllResponse.builder().status("200")
        .massage("Get data successfully.").data(listEmployee).build();
    when(employeeService.getALl()).thenReturn(ResponseEntity.ok().body(response));

    mvc.perform(get(URL_GETALL).contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.status", is("200")))
        .andExpect(jsonPath("$.massage", is("Get data successfully.")))
        .andExpect(jsonPath("$.data", samePropertyValuesAs(response.getData())));

    verify(employeeService, times(1)).getALl();
  }

}

package com.example.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.response.EmployeeGetAllResponse;
import com.example.demo.service.impl.EmployeeServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class EmployeeServiceTest {

  @Autowired
  protected MockMvc mvc;

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

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
    when(employeeRepository.getAll()).thenReturn(listEmployee);

    ResponseEntity<EmployeeGetAllResponse> listResponse = employeeService.getALl();
    
    verify(employeeRepository, times(1)).getAll();

    assertThat(listResponse.getBody().getData(), samePropertyValuesAs(listEmployee));
  }
}

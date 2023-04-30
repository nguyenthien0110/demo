package com.example.demo.response;

import java.util.List;
import com.example.demo.model.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetEmployeeByListIntResponse {
  private String status;
  private String massage;

  private List<Employee> data;
}

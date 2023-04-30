package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.request.IntegerRequest;
import com.example.demo.response.EmployeeGetAllResponse;
import com.example.demo.response.GetEmployeeByListIntResponse;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/getAll")
  public ResponseEntity<EmployeeGetAllResponse> getAllEmployee() {
    return employeeService.getALl();
  }
  
  @PostMapping("/getByListId")
  public ResponseEntity<GetEmployeeByListIntResponse> getEmployeeByListInt(@RequestBody IntegerRequest listInt){
    return employeeService.getEmployeeByListInt(listInt);
  }
}

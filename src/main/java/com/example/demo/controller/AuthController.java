package com.example.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SingupRequest;
import com.example.demo.response.CreateEmployeeResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping("/singup")
  public ResponseEntity<CreateEmployeeResponse> singUp(@Valid @RequestBody SingupRequest singupRequest) {
    return employeeService.createEmployee(singupRequest);
  }
  
  @PostMapping("/singin")
  public ResponseEntity<LoginResponse> singIn(@Valid @RequestBody LoginRequest loginRequest) {
    return employeeService.Login(loginRequest);
  }
}

package com.example.demo.service;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SingupRequest;
import com.example.demo.response.CreateEmployeeResponse;
import com.example.demo.response.EmployeeGetAllResponse;
import com.example.demo.response.GetEmployeeByListIntResponse;
import com.example.demo.response.LoginResponse;

@Service
public interface EmployeeService {

  ResponseEntity<EmployeeGetAllResponse> getALl();

  ResponseEntity<CreateEmployeeResponse> createEmployee(@Valid SingupRequest employeeRequest);

  ResponseEntity<LoginResponse> Login(@Valid LoginRequest loginRequest);

  ResponseEntity<GetEmployeeByListIntResponse> getEmployeeByListInt(IntegerRequest listInt);
}

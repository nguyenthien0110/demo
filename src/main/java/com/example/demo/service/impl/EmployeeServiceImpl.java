package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.SingupRequest;
import com.example.demo.response.CreateEmployeeResponse;
import com.example.demo.response.EmployeeGetAllResponse;
import com.example.demo.response.GetEmployeeByListIntResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  private JwtProvider jwtProvider;

  @Override
  public ResponseEntity<EmployeeGetAllResponse> getALl() {
    List<Employee> listEmployee = employeeRepository.getAll();
    EmployeeGetAllResponse response = EmployeeGetAllResponse.builder().status("200")
        .massage("Get data successfully.").data(listEmployee).build();
    return new ResponseEntity<EmployeeGetAllResponse>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CreateEmployeeResponse> createEmployee(
      @Valid SingupRequest employeeRequest) {
    employeeRequest.setPassword(encoder.encode(employeeRequest.getPassword()));
    employeeRepository.createEmployee(employeeRequest);
    CreateEmployeeResponse response =
        CreateEmployeeResponse.builder().status("201").massage("Create successful").build();
    return new ResponseEntity<CreateEmployeeResponse>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<LoginResponse> Login(@Valid LoginRequest loginRequest) {
    Optional<Employee> userDetails =
        employeeRepository.getEmployeeByUsername(loginRequest.getUsername());
    if (encoder.matches(loginRequest.getPassword(), userDetails.get().getPassword())) {
      // Generate token
      String jwt = jwtProvider.generateToken(loginRequest.getUsername());
      LoginResponse response = LoginResponse.builder().status("200").massage("Login successful")
          .username(userDetails.get().getUsername()).token(jwt).build();
      return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }
    return new ResponseEntity<LoginResponse>(HttpStatus.NO_CONTENT);
    // Authentication authentication =
    // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
    // loginRequest.getUsername(), loginRequest.getPassword()));
    //
    // SecurityContextHolder.getContext().setAuthentication(authentication);
    // String jwt = tokenProvider.generateJwtToken((CustomUserDetails)
    // authentication.getPrincipal());
    // Optional<Employee> emp =
    // employeeRepository.getEmployeeByUsername(loginRequest.getUsername());
    // LoginResponse response = LoginResponse.builder().status("200").massage("Login successful")
    // .username(emp.get().getUsername()).token(jwt).build();
    // return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<GetEmployeeByListIntResponse> getEmployeeByListInt(IntegerRequest listInt) {
    List<Employee> eml = employeeRepository.getEmployeeByListInt(listInt);
    if (listInt.getListInt().size() == eml.size()) {
      GetEmployeeByListIntResponse response = GetEmployeeByListIntResponse.builder().status("200")
          .massage("Get data successfully.").data(eml).build();
      return new ResponseEntity<GetEmployeeByListIntResponse>(response, HttpStatus.OK);
    }
    GetEmployeeByListIntResponse response = GetEmployeeByListIntResponse.builder().status("204")
        .massage("There id does not exist").build();
    return new ResponseEntity<GetEmployeeByListIntResponse>(response, HttpStatus.NO_CONTENT);
  }
}

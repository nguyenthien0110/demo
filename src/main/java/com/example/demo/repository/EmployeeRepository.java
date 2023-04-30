package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.model.Employee;
import com.example.demo.request.IntegerRequest;
import com.example.demo.request.SingupRequest;

@Mapper
public interface EmployeeRepository {
  List<Employee> getAll();

  Boolean createEmployee(SingupRequest employeeRequest);

  Optional<Employee> getEmployeeByUsername(String username);
  
  Optional<Employee> getEmployeeById(Long id);

  List<Employee> getEmployeeByListInt(IntegerRequest listInt);
}

package com.example.demo.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service("customEmployeeDetailsService")
public class CustomEmployeeDetailsService implements UserDetailsService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Employee> eml = employeeRepository.getEmployeeByUsername(username);
    if (eml.isEmpty() || !eml.get().getUsername().equals(username)) {
      throw new UsernameNotFoundException("No user present with username: " + username);
    } else {

      return new CustomEmployeeDetails(eml.get());
    }
  }
}

package com.example.demo.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<Employee> iml = employeeRepository.getEmployeeByUsername(username);
    if (iml.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }
    return new CustomUserDetails(iml.get());
  }
}

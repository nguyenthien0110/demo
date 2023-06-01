package com.example.demo.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.model.Employee;

public class CustomEmployeeDetails extends Employee implements UserDetails {
  private static final long serialVersionUID = 1L;
  private Employee employee;

  public CustomEmployeeDetails(Employee employee) {
    super();
    this.employee = employee;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getUsername() {
    return this.employee.getUsername();
  }

  @Override
  public String getPassword() {
    return this.employee.getPassword();
  }
}

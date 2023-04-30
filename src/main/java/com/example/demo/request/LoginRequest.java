package com.example.demo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
  @NotEmpty(message = "Username cannot be empty")
  @NotNull(message = "Username cannot be Null")
  private String username;

  @NotEmpty(message = "Password cannot be empty")
  @NotNull(message = "Password cannot be Null")
  private String password;
}

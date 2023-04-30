package com.example.demo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SingupRequest {

  @NotEmpty(message = "Name cannot be empty")
  private String name;

  @NotEmpty(message = "Username cannot be empty")
  @NotNull(message = "Username cannot be Null")
  private String username;

  @NotEmpty(message = "Password cannot be empty")
  @NotNull(message = "Password cannot be Null")
  private String password;

  @Min(value = 1, message = "Age cannot be empty or less than 0")
  @Max(value = 200, message = "Age cannot be more than 200")
  private int age;

  @Email(message = "Email is not valid",
      regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  @NotEmpty(message = "Email cannot be empty")
  private String email;
}

package com.example.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
  private String status;
  private String massage;
  private String username;
  private String token;
}

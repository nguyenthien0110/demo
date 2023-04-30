package com.example.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeResponse {
  private String status;
  private String massage;
}

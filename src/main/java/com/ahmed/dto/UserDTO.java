package com.ahmed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log
public class UserDTO {

  /**
   * The id.
   */
  public Long id;

  /**
   * The name .
   */
  public String name;

  /**
   * The role.
   */
  public String role;
}

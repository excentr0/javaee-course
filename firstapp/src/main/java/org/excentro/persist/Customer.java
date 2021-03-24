package org.excentro.persist;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
  private Long id;
  private String name;
  private String phone;
}

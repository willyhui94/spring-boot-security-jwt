package com.willyhui94.springbootsecurityjwt.entity.bo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonPropertyOrder({"id", "name", "price"})
public class ProductBO {

  private Integer id;
  private String name;
  private Double price;
}

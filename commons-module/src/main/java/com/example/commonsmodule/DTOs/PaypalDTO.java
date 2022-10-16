package com.example.commonsmodule.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaypalDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String secureKey;
    private Double balance;
}

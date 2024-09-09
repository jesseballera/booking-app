package com.umpisa.restoapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.umpisa.restoapp.annotations.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCustomer {

    @NotBlank(message = "Customer name must not be empty")
    @JsonProperty("customerName")
    String name;

    @NotNull(message = "Mobile number must not be empty")
    @JsonProperty("mobileNumber")
    String phoneNumber;

    @ValidEmail
    @NotNull(message = "Email must not be empty")
    String email;

}

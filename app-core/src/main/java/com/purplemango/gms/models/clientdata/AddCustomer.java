package com.purplemango.gms.models.clientdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.purplemango.gms.annotations.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record AddCustomer(
        @NotBlank(message = "Customer first name must not be empty")
        @JsonProperty("customerFirstName")
        String firstName,

        @NotBlank(message = "Customer last name must not be empty")
        @JsonProperty("customerLastName")
        String lastName,

        @NotNull(message = "Mobile number must not be empty")
        @JsonProperty("mobileNumber")
        String phoneNumber,

        @ValidEmail
        @NotNull(message = "Email must not be empty")
        String email,

        @NotNull(message = "Address must not be empty")
        AddAddress address) { }

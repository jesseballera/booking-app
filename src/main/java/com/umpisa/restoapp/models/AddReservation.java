package com.umpisa.restoapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.umpisa.restoapp.annotations.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Schema
public class AddReservation {

    @NotBlank(message = "Customer name must not be empty")
    @JsonProperty("customerName")
    String name;

    @NotNull(message = "Mobile number must not be empty")
    @JsonProperty("mobileNumber")
    String phoneNumber;

    @ValidEmail
    @NotNull(message = "Email must not be empty")
    String email;

//    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
//    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
//    @Schema(type="string", example = "dd/MM/yyyy hh:mm", pattern = "dd/MM/yyyy hh:mm")
    LocalDateTime reservationDate;

    int numberOfGuests;

    Collection<NotificationType> notificationType;
}

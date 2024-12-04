package com.purplemango.gms.models.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.purplemango.gms.annotations.ValidEmail;
import com.purplemango.gms.models.core.enums.NotificationType;
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
    @Schema(type="string", format = "date-time", example = "dd/MM/yyyy hh:mm")
    LocalDateTime reservationDate;

    int numberOfGuests;

    Collection<NotificationType> notificationType;
}

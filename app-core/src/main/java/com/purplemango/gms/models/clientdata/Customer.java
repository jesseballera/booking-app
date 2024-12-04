package com.purplemango.gms.models.clientdata;

import com.purplemango.gms.models.reservation.Reservation;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Builder
@Document(collection = "customers")
public record Customer(
        @MongoId String Id,
        @NotNull String firstName,
        @NotNull String lastName,
        String phoneNumber,
        @NotNull String email,
        @NotNull Address address
) { }

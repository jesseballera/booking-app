package com.purplemango.gms.models.payment;

import com.purplemango.gms.models.clientdata.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Document(collection = "customers")
public record Customer(
        @MongoId String Id,
        @NotNull String firstName,
        @NotNull String lastName,
        String phoneNumber,
        @NotNull String email,
        @NotNull Address address) { }

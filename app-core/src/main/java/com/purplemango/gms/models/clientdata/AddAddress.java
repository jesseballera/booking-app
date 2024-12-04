package com.purplemango.gms.models.clientdata;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public record AddAddress(
        String houseNumber,
        String street,
        String city,
        String state,
        String barangay,
        Address.Country country,
        String zipCode) { }

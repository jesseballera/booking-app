package com.purplemango.gms.models.clientdata;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@Document(collection = "addresses")
public record Address(
        @MongoId String id,
        String houseNumber,
        String street,
        String city,
        String state,
        String barangay,
        Country country,
        String zipCode) {

    @Getter
    @AllArgsConstructor
    public enum Country {
        USA("United States of America"),
        CANADA("Canada"),
        PHILIPPINES("Philippines"),
        INDONESIA("Indonesia"),
        THAILAND("Thailand"),
        VIETNAM("Vietnam"),
        INDIA("India"),
        SINGAPORE("Singapore"),
        MALAYSIA("Malaysia");

        private final String value;

        public static Map<String, String> asMap() {
            Map<String, String> map = new HashMap<>();
            for (Country e : values()) {
                map.put(e.toString(), e.getValue());
            }
            return Collections.unmodifiableMap(map);
        }
    }
}

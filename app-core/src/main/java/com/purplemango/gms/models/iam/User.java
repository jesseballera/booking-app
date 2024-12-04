package com.purplemango.gms.models.iam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collection;
import java.util.UUID;

@Document(collection = "users")
public record User(
        @MongoId String id,
        String username,
        @JsonIgnore String password,
        String email,
        Role role,
        UserStatus status) {

    public static enum UserStatus {
        ACTIVE,
        INACTIVE,
        BLOCKED,
        PENDING_VERIFICATION,
        PENDING_PASSWORD_RESET,
        PENDING_EMAIL_CHANGE,
        PENDING_PHONE_CHANGE,
        DELETED
    }
}

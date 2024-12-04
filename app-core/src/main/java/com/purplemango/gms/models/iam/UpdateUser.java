package com.purplemango.gms.models.iam;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record UpdateUser(
        @MongoId String id,
        String username,
        String password,
        String email,
        Role role,
        User.UserStatus status) {

}

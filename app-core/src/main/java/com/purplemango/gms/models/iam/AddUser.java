package com.purplemango.gms.models.iam;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record AddUser(
        String username,
        String password,
        String email,
        String roleName,
        User.UserStatus status) {

}

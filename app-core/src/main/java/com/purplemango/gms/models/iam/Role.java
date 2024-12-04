package com.purplemango.gms.models.iam;

import com.purplemango.gms.models.core.enums.Status;
import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collection;
import java.util.UUID;

@Builder
@Document(collection = "roles")
public record Role(
        @MongoId String id,
        String name,
        Collection<Permission> permissions,
        Status status) {

//    public static Builder build() {
//        return new Role();
//    }

//    public static Role addRole(AddRole addRole) {
//        return new Role(UUID.randomUUID(), addRole.name(), addRole.permissions(), Status.ACTIVE);
//    }
}

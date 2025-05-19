package com.purplemango.gms.models.iam;

import com.purplemango.gms.models.core.enums.Status;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Collection;

@Builder
@Document(collection = Role.COLLECTION_NAME)
public record Role (

        @MongoId String id,
        String name,
        String tenant,
        Collection<Permission> permissions,
        Status status) implements Serializable {

        private static final long serialVersionUID = 1L;
        public static final String COLLECTION_NAME = "roles";


//    public static Builder build() {
//        return new Role();
//    }

//    public static Role addRole(AddRole addRole) {
//        return new Role(UUID.randomUUID(), addRole.name(), addRole.permissions(), Status.ACTIVE);
//    }
}

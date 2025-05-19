package com.purplemango.gms.models;

import lombok.Builder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Builder
@Document(collection = Tenant.COLLECTION_NAME)
public record Tenant(@MongoId String id,
                     @Indexed(unique = true) String tenantName,
                     @Indexed(unique = true) String tenantCode) {

    private static final long serialVersionUID = 1L;
    public static final String COLLECTION_NAME = "tenants";

    public static Tenant build(AddTenant addTenant) {
        return Tenant.builder()
                .id(UUID.randomUUID().toString())
                .tenantName(addTenant.tenantName())
                .tenantCode(addTenant.tenantCode())
                .build();
    }

    public static Tenant upsert(UpdateTenant entity) {
        return Tenant.builder()
                .id(entity.id())
                .tenantName(entity.tenantName())
                .build();
    }
}

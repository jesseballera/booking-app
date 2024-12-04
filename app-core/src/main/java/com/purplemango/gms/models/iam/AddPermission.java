package com.purplemango.gms.models.iam;

import com.purplemango.gms.models.core.enums.Status;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

public record AddPermission(
        String permissionName){ }

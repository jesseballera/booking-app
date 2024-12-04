package com.purplemango.gms.models.iam;

import com.purplemango.gms.models.core.enums.Status;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public record AddRole(
        String name,
        List<String> permissions,
        Status status) { }

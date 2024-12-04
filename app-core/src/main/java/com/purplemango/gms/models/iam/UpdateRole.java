package com.purplemango.gms.models.iam;

import com.purplemango.gms.models.core.enums.Status;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.UUID;

public record UpdateRole(
        String uuid,
        String name,
        Collection<String> permissions,
        Status status) { }

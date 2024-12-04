package com.purplemango.gms.models.iam;

import com.purplemango.gms.models.core.enums.Status;
import org.bson.types.ObjectId;


public record UpdatePermission(
        String id,
        String permissionName,
        Status status){ }

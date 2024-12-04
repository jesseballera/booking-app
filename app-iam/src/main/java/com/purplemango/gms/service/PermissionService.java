package com.purplemango.gms.service;

import com.purplemango.gms.models.iam.AddPermission;
import com.purplemango.gms.models.iam.Permission;
import com.purplemango.gms.models.iam.UpdatePermission;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface PermissionService {

    Collection<Permission> viewAllPermissions();
    List<Permission> findAllByPermissionNameIn(List<String> lists);
    void createPermission(List<String> list);
    void createPermission(AddPermission entity);
    void updatePermission(UpdatePermission entity, String entityId);
    void deletePermission(String entityId);
}

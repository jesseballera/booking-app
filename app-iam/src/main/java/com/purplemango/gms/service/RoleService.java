package com.purplemango.gms.service;

import com.purplemango.gms.models.iam.AddRole;
import com.purplemango.gms.models.iam.Role;
import com.purplemango.gms.models.iam.UpdateRole;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.UUID;

public interface RoleService {
    Collection<Role> viewAllRoles(String tenantId);
    Role viewById(String tenantId, String entityId);
    Role viewByName(String tenantId, String name);
    void createRole(String tenantId, AddRole entity);
    void updateRole(String tenantId, UpdateRole entity, String entityId);
    void deleteRole(String tenantId, String entityId);
}

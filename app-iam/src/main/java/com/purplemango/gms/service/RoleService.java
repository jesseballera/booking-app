package com.purplemango.gms.service;

import com.purplemango.gms.models.iam.AddRole;
import com.purplemango.gms.models.iam.Role;
import com.purplemango.gms.models.iam.UpdateRole;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.UUID;

public interface RoleService {
    Collection<Role> viewAllRoles();
    Role viewById(String entityId);
    Role viewByName(String name);
    void createRole(AddRole entity);
    void updateRole(UpdateRole entity, String entityId);
    void deleteRole(String entityId);
}

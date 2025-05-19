package com.purplemango.gms.service;

import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.UpdateUser;
import com.purplemango.gms.models.iam.User;
import org.bson.types.ObjectId;
import org.mapstruct.control.MappingControl;

import java.util.Collection;
import java.util.Collections;

public interface UserService {
    void createUser(String tenantId, AddUser entity);

    Collection<User> viewAllUsers(String tenantId);

    User viewUserById(String tenantId, String entityId);

    User viewUserByUsername(String tenantId, String username);

    void updateUser(String tenantId, UpdateUser entity, String entityId);

    void deleteUser(String tenantId, String entityId);

}

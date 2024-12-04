package com.purplemango.gms.service;

import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.UpdateUser;
import com.purplemango.gms.models.iam.User;
import org.bson.types.ObjectId;
import org.mapstruct.control.MappingControl;

import java.util.Collection;
import java.util.Collections;

public interface UserService {
    void createUser(AddUser entity);

    Collection<User> viewAllUsers();

    User viewUserById(String entityId);

    User viewUserByUsername(String username);

    void updateUser(UpdateUser entity, String entityId);

    void deleteUser(String entityId);

}

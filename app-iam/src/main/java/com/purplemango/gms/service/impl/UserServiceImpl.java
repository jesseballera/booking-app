package com.purplemango.gms.service.impl;

import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.Role;
import com.purplemango.gms.models.iam.UpdateUser;
import com.purplemango.gms.models.iam.User;
import com.purplemango.gms.repository.UserRepository;
import com.purplemango.gms.service.RoleService;
import com.purplemango.gms.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final RoleService roleService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(RoleService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> viewAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(AddUser entity) {

        Role role = roleService.viewByName(entity.roleName());
        User user = new User(UUID.randomUUID().toString(), entity.username(), entity.password(), entity.email(), role, entity.status());
        userRepository.save(user);
    }

    @Override
    public User viewUserById(String entityId) {
        return userRepository.findById(entityId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User viewUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void updateUser(UpdateUser entity, String entityId) {

    }

    @Override
    public void deleteUser(String entityId) {
        viewUserById(entityId);
        userRepository.deleteById(entityId);
    }
}

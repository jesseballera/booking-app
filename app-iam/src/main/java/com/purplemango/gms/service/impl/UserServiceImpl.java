package com.purplemango.gms.service.impl;

import com.purplemango.gms.exceptions.ResultNotFoundException;
import com.purplemango.gms.models.Tenant;
import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.Role;
import com.purplemango.gms.models.iam.UpdateUser;
import com.purplemango.gms.models.iam.User;
import com.purplemango.gms.repository.UserRepository;
import com.purplemango.gms.service.RoleService;
import com.purplemango.gms.service.TenantService;
import com.purplemango.gms.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final TenantService tenantService;

    @Autowired
    public UserServiceImpl(RoleService roleService,
                           UserRepository userRepository,
                           TenantService tenantService) {
        this.tenantService = tenantService;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> viewAllUsers(String tenantId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return userRepository.findAll(tenant.tenantCode());
    }

    @Override
    public void createUser(String tenantId, AddUser entity) {
        if (tenantService.getTenantById(tenantId) == null) {
            throw new ResultNotFoundException("Tenant not found");
        }
        Tenant tenant = tenantService.getTenantById(tenantId);
        Role role = roleService.viewByName(tenantId, entity.roleName());
        User user = new User(UUID.randomUUID().toString(), entity.username(), entity.password(), entity.email(),
                tenantService.getTenantById(tenantId).tenantCode(), role, entity.status());
        userRepository.save(tenant.tenantCode(), user);
    }

    @Override
    @Cacheable(value = "user", key = "#user.username")
    public User viewUserById(String tenantId, String entityId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return userRepository.findById(tenant.tenantCode(), entityId).orElseThrow(() -> new ResultNotFoundException("User not found {}", entityId));
    }

    @Override
    @Cacheable(value = "user", key = "#username")
    public User viewUserByUsername(String tenantId, String username) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return userRepository.findByUsername(tenant.tenantCode(), username).orElseThrow(() -> new ResultNotFoundException("User not found {}", username));
    }

    @Override
    @CachePut(value = "user", key = "#user.id")
    public void updateUser(String tenantId, UpdateUser entity, String entityId) {

    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(String tenantId, String entityId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        viewUserById(tenantId,entityId);
        userRepository.deleteById(tenant.tenantCode(), entityId);
    }
}

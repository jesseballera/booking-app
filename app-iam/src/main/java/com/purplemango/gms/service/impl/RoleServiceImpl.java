package com.purplemango.gms.service.impl;

import com.purplemango.gms.models.iam.*;
import com.purplemango.gms.repository.PermissionRepository;
import com.purplemango.gms.repository.RoleRepository;
import com.purplemango.gms.service.PermissionService;
import com.purplemango.gms.service.RoleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           PermissionService permissionService) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Collection<Role> viewAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role viewById(String entityId) {
        return roleRepository.findById(entityId).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role viewByName(String name) {
//        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Role not found"));
        return roleRepository.findByName(name).get();
    }

    @Override
    public void createRole(AddRole entity) {
//        roleRepository.findByName(entity.name()).orElseThrow(() -> new RuntimeException("Role already exists"));

        AddPermission addPermission = new AddPermission(entity.name());
        List<Permission> permissions = permissionService.findAllByPermissionNameIn(entity.permissions());
        Role role = new Role(UUID.randomUUID().toString(), entity.name(), permissions, entity.status());
        roleRepository.save(role);
    }

    @Override
    public void updateRole(UpdateRole entity, String entityId) {

    }

    @Override
    public void deleteRole(String entityId) {

    }


}

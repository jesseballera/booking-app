package com.purplemango.gms.service.impl;

import com.purplemango.gms.exceptions.DuplicateEntryException;
import com.purplemango.gms.exceptions.ResultNotFoundException;
import com.purplemango.gms.models.Tenant;
import com.purplemango.gms.models.iam.*;
import com.purplemango.gms.repository.RoleRepository;
import com.purplemango.gms.service.PermissionService;
import com.purplemango.gms.service.RoleService;
import com.purplemango.gms.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;
    private final TenantService tenantService;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           PermissionService permissionService,
                           TenantService tenantService) {
        this.tenantService = tenantService;
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Collection<Role> viewAllRoles(String tenantId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return roleRepository.findAll(tenant.tenantCode());
    }

    @Override
    public Role viewById(String tenantId, String entityId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return roleRepository.findById(tenant.tenantCode(), entityId).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role viewByName(String tenantId, String name) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return roleRepository.findByName(tenant.tenantCode(), name).orElseThrow(() -> new ResultNotFoundException("No result found.", name));
    }

    @Override
    public void createRole(String tenantId, AddRole entity) {
        if (tenantService.getTenantById(tenantId) == null) {
            throw new ResultNotFoundException("Tenant not found");
        }
        Tenant tenant = tenantService.getTenantById(tenantId);
        if (roleRepository.existsByName(tenant.tenantCode(), entity.name())) {
            throw new DuplicateEntryException("Duplicate entry found", entity.name());
        }

        AddPermission addPermission = new AddPermission(entity.name());
        List<Permission> permissions = permissionService.findAllByPermissionNameIn(entity.permissions());

        Role role = new Role(UUID.randomUUID().toString(), entity.name(), tenantService.getTenantById(tenantId).tenantCode(), permissions, entity.status());
        roleRepository.save(tenant.tenantCode(), role);
    }

    @Override
    public void updateRole(String tenantId, UpdateRole entity, String entityId) {

    }

    @Override
    public void deleteRole(String tenantId, String entityId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        roleRepository.deleteById(tenant.tenantCode(), entityId);
    }
}

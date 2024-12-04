package com.purplemango.gms.service.impl;

import com.purplemango.gms.models.core.enums.Status;
import com.purplemango.gms.models.iam.AddPermission;
import com.purplemango.gms.models.iam.Permission;
import com.purplemango.gms.models.iam.UpdatePermission;
import com.purplemango.gms.repository.PermissionRepository;
import com.purplemango.gms.service.PermissionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    @Override
    public Collection<Permission> viewAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> findAllByPermissionNameIn(List<String> lists) {
        return permissionRepository.findAllByPermissionNameIn(lists);
    }

    @Override
    public void createPermission(AddPermission entity) {
        permissionRepository.findByPermissionName(entity.permissionName()).orElseThrow(() -> new RuntimeException("Permission already exists"));
        permissionRepository.save(Permission.addPermission(entity));
    }

    @Override
    public void createPermission(List<String> list) {
        if (list != null) {
            for (String permission : list) {
//                if (permissionRepository.findByPermissionName(permission).isPresent() ) {
                    permissionRepository.save(new Permission(UUID.randomUUID().toString(), permission, Status.ACTIVE));
//                }
            }
        }
    }

    @Override
    public void updatePermission(UpdatePermission entity, String entityId) {
        permissionRepository.findById(entityId).orElseThrow(() -> new RuntimeException("Permission not found"));
        permissionRepository.findByPermissionName(entity.permissionName()).orElseThrow(() -> new RuntimeException("Permission already exists"));
        permissionRepository.save(Permission.updatePermission(entity, entityId));
    }

    @Override
    public void deletePermission(String entityId) {
        permissionRepository.findById(entityId).orElseThrow(() -> new RuntimeException("Permission not found"));
        permissionRepository.deleteById(entityId);
    }

}

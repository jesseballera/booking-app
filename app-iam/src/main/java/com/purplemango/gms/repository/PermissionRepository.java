package com.purplemango.gms.repository;

import com.purplemango.gms.models.iam.Permission;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findByPermissionName(String permissionName);

    List<Permission> findAllByPermissionNameIn(List<String> lists);
}

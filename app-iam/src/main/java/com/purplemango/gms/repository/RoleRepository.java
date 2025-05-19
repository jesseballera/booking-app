package com.purplemango.gms.repository;

import com.purplemango.gms.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.gms.models.iam.Permission;
import com.purplemango.gms.models.iam.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {
    Optional<Role> findByName(String tenant, String name);
    Collection<Role> findAll(String tenant);
    Optional<Role> findById(String tenant, String id);
    Role save(String tenant, Role role);
    void deleteById(String tenant, String id);
    boolean existsByName(String tenant, String name);
}

package com.purplemango.gms.repository;

import com.purplemango.gms.models.iam.Role;
import com.purplemango.gms.models.iam.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByUsername(String tenant, String username);
    Optional<User> findByEmail(String tenant, String email);
    Collection<User> findAll(String tenant);
    Optional<User> findById(String tenant, String id);
    User save(String tenant, User user);
    void deleteById(String tenant, String id);
}

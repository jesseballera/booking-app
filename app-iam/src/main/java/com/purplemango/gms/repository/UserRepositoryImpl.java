package com.purplemango.gms.repository;

import com.purplemango.gms.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.gms.aop.operations.BeforeTenantMongoOperation;
import com.purplemango.gms.config.MultiTenantMongoDBFactory;
import com.purplemango.gms.models.iam.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@BeforeTenantMongoOperation
public class UserRepositoryImpl extends MongoBaseRepository<User> implements UserRepository {

    public static final String COLLECTION_NAME = User.COLLECTION_NAME;

    private final String databaseName;
    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate, String databaseName) {
        this.databaseName = databaseName;
        super.setDatabaseName(databaseName);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Cacheable(value = "user", key = "#user.username")
    public Optional<User> findByUsername(String tenant, String username) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return Optional.of(findOneByQuery(new Query().addCriteria(Criteria.where("username").is(username)), User.class, COLLECTION_NAME));
    }

    @Override
    @Cacheable(value = "user", key = "#user.email")
    public Optional<User> findByEmail(String tenant, String email) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return Optional.of(findOneByQuery(new Query().addCriteria(Criteria.where("username").is(email)), User.class, COLLECTION_NAME));
    }

    @Override
    public Collection<User> findAll(String tenant) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return findAll(User.class, COLLECTION_NAME);
    }

    @Override
    @Cacheable(value = "user", key = "#user.id")
    public Optional<User> findById(String tenant, String id) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return Optional.of(findOneByQuery(new Query().addCriteria(Criteria.where("_id").is(id)), User.class, COLLECTION_NAME));
    }

    @Override
    @CachePut(value = "user", key = "#user.id")
    public User save(String tenant, User user) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return save(user, COLLECTION_NAME);
    }

    @Override
    @CacheEvict(value = "user", key = "#user.id")
    public void deleteById(String tenant, String id) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        deleteById(new Query().addCriteria(Criteria.where("_id").is(id)), User.class, COLLECTION_NAME);
    }
}

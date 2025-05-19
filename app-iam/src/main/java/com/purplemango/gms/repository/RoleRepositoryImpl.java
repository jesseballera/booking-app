package com.purplemango.gms.repository;

import com.purplemango.gms.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.gms.aop.operations.BeforeTenantMongoOperation;
import com.purplemango.gms.config.MultiTenantMongoDBFactory;
import com.purplemango.gms.models.iam.Permission;
import com.purplemango.gms.models.iam.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
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
public class RoleRepositoryImpl extends MongoBaseRepository<Role>implements RoleRepository {
    public static final String COLLECTION_NAME = Role.COLLECTION_NAME;
    private final String databaseName;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RoleRepositoryImpl(MongoTemplate mongoTemplate, String databaseName) {
        this.databaseName = databaseName;
        super.setDatabaseName(databaseName);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Cacheable(value = "role", key = "#name")
    public Optional<Role> findByName(String tenant, String name) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return Optional.of(findOneByQuery(new Query().addCriteria(Criteria.where("name").is(name)), Role.class, COLLECTION_NAME));
    }

    @Override
    public Collection<Role> findAll(String tenant) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return findAll(Role.class, COLLECTION_NAME);
    }

    @Override
    @Cacheable(value = "role", key = "#id")
    public Optional<Role> findById(String tenant, String id) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return Optional.of(findOneByQuery(new Query().addCriteria(Criteria.where("_id").is(id)), Role.class, COLLECTION_NAME));
    }

    @Override
    @Cacheable(value = "role", key = "#role.id")
    public Role save(String tenant, Role role) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return save(role, COLLECTION_NAME);
    }

    @Override
    @CacheEvict(value = "role", key = "#id")
    public void deleteById(String tenant, String id) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        deleteById(new Query().addCriteria(Criteria.where("_id").is(id)), Role.class, COLLECTION_NAME);
    }

    @Override
    public boolean existsByName(String tenant, String name) {
        String databaseName = String.format("%s-%s", this.getTargetName(), tenant);
        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return mongoTemplate.exists(new Query().addCriteria(Criteria.where("name").is(name)), Role.class, COLLECTION_NAME);
    }
}

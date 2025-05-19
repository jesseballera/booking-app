package com.purplemango.gms.repository;

import com.purplemango.gms.aop.operations.BeforeDefaultMongoOperation;
import com.purplemango.gms.config.MultiTenantMongoDBFactory;
import com.purplemango.gms.models.iam.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@BeforeDefaultMongoOperation
public class PermissionRepositoryImpl extends MongoBaseRepository<Permission> implements PermissionRepository {
    public static final String COLLECTION_NAME = Permission.COLLECTION_NAME;
    private String databaseName;
    private final MongoTemplate mongoTemplate;

    public PermissionRepositoryImpl(MongoTemplate mongoTemplate, String databaseName) {
        super.setDatabaseName(databaseName);
        this.databaseName = databaseName;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Permission> findByPermissionName(String permissionName) {
//        String databaseName = String.format("%s-%s", this.getTargetName(), COLLECTION_NAME);
//        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return Optional.of(findOneByQuery(new Query().addCriteria(Criteria.where("permissionName").is(permissionName)), Permission.class, COLLECTION_NAME));
    }

    @Override
    public List<Permission> findAllByPermissionNameIn(List<String> lists) {
//        String databaseName = String.format("%s-%s", this.getTargetName(), COLLECTION_NAME);
//        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return mongoTemplate.find(new Query().addCriteria(Criteria.where("permissionName").in(lists)), Permission.class, COLLECTION_NAME);
    }

    @Override
    public Collection<Permission> findAll() {
//        String databaseName = String.format("%s-%s", this.getTargetName(), COLLECTION_NAME);
//        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return findAll(Permission.class, COLLECTION_NAME);
    }

    @Override
    public Optional<Permission> findById(String id) {
//        String databaseName = String.format("%s-%s", this.getTargetName(), COLLECTION_NAME);
//        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return Optional.of(findOneByQuery(new Query().addCriteria(Criteria.where("_id").is(id)), Permission.class, COLLECTION_NAME));
    }

    @Override
    public Permission save(Permission permission) {
//        String databaseName = String.format("%s-%s", this.getTargetName(), COLLECTION_NAME);
//        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        return save(permission, COLLECTION_NAME);
    }

    @Override
    public void deleteById(String id) {
//        String databaseName = String.format("%s-%s", this.getTargetName(), COLLECTION_NAME);
//        MultiTenantMongoDBFactory.setDatabaseNameForCurrentThread(databaseName);
        deleteById(new Query().addCriteria(Criteria.where("_id").is(id)), Permission.class, COLLECTION_NAME);
    }
}

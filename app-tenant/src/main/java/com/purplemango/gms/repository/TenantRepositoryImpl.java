package com.purplemango.gms.repository;

import com.mongodb.client.result.UpdateResult;
import com.purplemango.gms.aop.operations.BeforeGlobalMongoOperation;
import com.purplemango.gms.models.Tenant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@BeforeGlobalMongoOperation
public class TenantRepositoryImpl extends MongoBaseRepository<Tenant> implements TenantRepository {

    public static final String COLLECTION_NAME = Tenant.COLLECTION_NAME;
    private String databaseName;
    private final MongoTemplate mongoTemplate;
    public TenantRepositoryImpl(MongoTemplate mongoTemplate, String databaseName) {
        this.databaseName = databaseName;
        super.setDatabaseName(databaseName);
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Tenant findByCompanyNameAndCompanyCode(String companyName, String companyCode) {
        return null;
    }

    @Override
    public Collection<Tenant> findAll() {
        return mongoTemplate.findAll(Tenant.class, COLLECTION_NAME);
    }

    @Override
    public Page<Tenant> findAll(Pageable pageable) {
        Query query = new Query().with(pageable).with(pageable.getSort());
        List<Tenant> filteredTenants = mongoTemplate.find(query, Tenant.class, COLLECTION_NAME);
        return PageableExecutionUtils.getPage( filteredTenants, pageable, () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Tenant.class));
    }

    @Override
    public Tenant findById(String tenantId) {
        Query query = new Query(Criteria.where("id").is(tenantId));
        return findOneByQuery(query, Tenant.class, COLLECTION_NAME);
    }

    @Override
    public Tenant findByTenantCode(String tenantCode) {
        Query query = new Query(Criteria.where("tenantCode").is(tenantCode));
        return findOneByQuery(query, Tenant.class, COLLECTION_NAME);
    }

    @Override
    public Tenant save(Tenant tenant) {
       return save(tenant, COLLECTION_NAME);
    }

    @Override
    public Tenant createOrUpdateTenant(Tenant tenant) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(tenant.id()));
        Update updateDefinition = null;
        String id = null;
//        if (tenant.id() != null) {
//            updateDefinition = new Update()
//                    .set("companyName", tenant.companyName())
//                    .set("companyCode", tenant.companyCode());
//            UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, Tenant.class);
//            id = tenant.id();
//        } else {
            updateDefinition = new Update()
//                    .set("id", UUID.randomUUID().toString())
                    .set("companyName", tenant.tenantName());
//                    .set("companyCode", tenant.companyCode());
            UpdateResult updateResult = mongoTemplate.upsert(query, updateDefinition, Tenant.class);
//            id = updateResult.getUpsertedId().toString();
//        }
        return findById(tenant.id());
    }

    @Override
    public boolean existByTenantId(String tenantId) {
        return mongoTemplate.exists(new Query().addCriteria(Criteria.where("id").is(tenantId)), Tenant.class, COLLECTION_NAME);
    }

    @Override
    public boolean existByTenantCode(String tenantCode) {
        return mongoTemplate.exists(new Query().addCriteria(Criteria.where("tenantCode").is(tenantCode)), Tenant.class, COLLECTION_NAME);
    }
}

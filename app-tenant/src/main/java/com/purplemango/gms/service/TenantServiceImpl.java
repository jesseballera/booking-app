package com.purplemango.gms.service;

import com.purplemango.gms.exceptions.DuplicateEntryException;
import com.purplemango.gms.models.AddTenant;
import com.purplemango.gms.models.Tenant;
import com.purplemango.gms.models.UpdateTenant;
import com.purplemango.gms.repository.TenantRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantServiceImpl implements TenantService {
    TenantRepository tenantRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Collection<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public Page<Tenant> getAllTenants(Pageable pageable) {
        return tenantRepository.findAll(pageable);
    }

    @Override
    public Tenant getTenantById(final String tenantId) {
        return tenantRepository.findById(tenantId);
    }

    @Override
    public Tenant getTenantByTenantCode(String tenantCode) {
        return tenantRepository.findByTenantCode(tenantCode);
    }

    @Override
    public Tenant createTenant(final AddTenant entity) {
        Tenant tenant = tenantRepository.findByCompanyNameAndCompanyCode(entity.tenantName(), entity.tenantName());
        if (tenant != null) {
            throw new DuplicateEntryException("Tenant already exists");
        }

        return tenantRepository.save(Tenant.build(entity));
    }

    @Override
    public Tenant createOrUpdateTenant(UpdateTenant tenant) {
        return tenantRepository.createOrUpdateTenant(Tenant.upsert(tenant));
    }

    @Override
    public boolean existByTenantId(String tenantId) {
        return tenantRepository.existByTenantId(tenantId);
    }
    @Override
    public boolean existByTenantCode(String tenantCode) {
        return tenantRepository.existByTenantCode(tenantCode);
    }
}

package com.purplemango.gms.service;


import com.purplemango.gms.models.AddTenant;
import com.purplemango.gms.models.Tenant;
import com.purplemango.gms.models.UpdateTenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface TenantService {

    Collection<Tenant> getAllTenants();
    Page <Tenant> getAllTenants(Pageable pageable);
    Tenant getTenantById(final String tenantId);
    Tenant getTenantByTenantCode(final String tenantCode);
    Tenant createTenant(final AddTenant tenant);
    Tenant createOrUpdateTenant(final UpdateTenant tenant);
    boolean existByTenantId(String tenantId);
    boolean existByTenantCode(String tenantCode);
}

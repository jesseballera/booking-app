package com.purplemango.gms.util;

import com.purplemango.gms.models.AddTenant;
import com.purplemango.gms.models.Tenant;
import com.purplemango.gms.models.core.enums.Status;
import com.purplemango.gms.models.iam.AddRole;
import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.User;
import com.purplemango.gms.models.security.RolePermission;
import com.purplemango.gms.service.PermissionService;
import com.purplemango.gms.service.RoleService;
import com.purplemango.gms.service.TenantService;
import com.purplemango.gms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class InitializeData implements ApplicationRunner {

    final PermissionService permissionService;
    final RoleService roleService;
    final UserService userService;
    final TenantService tenantService;
    final PasswordEncoder passwordEncoder;

    final String tenantName;
    final String tenantCode;


    @Autowired

    public InitializeData(PermissionService permissionService, RoleService roleService,
                          UserService userService, TenantService tenantService,
                          PasswordEncoder passwordEncoder,
                          @Value("${init-data.tenant-name}") String tenantName,
                          @Value("${init-data.tenant-code}") String tenantCode) {
        this.tenantName = tenantName;
        this.tenantCode = tenantCode;
        this.roleService = roleService;
        this.tenantService = tenantService;
        this.permissionService = permissionService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        addTenant();
        getTenantId();
        addPermissions();
        addRole();
        addUser();
    }

    void addTenant() {
        if (!tenantService.existByTenantCode(tenantCode))
            tenantService.createTenant(new AddTenant(tenantName, tenantCode));
    }

    void  addPermissions() {
        if (permissionService.viewAllPermissions().isEmpty())
            permissionService.createPermission(permissions());
    }

    Tenant getTenantId() {
        return tenantService.getTenantByTenantCode(tenantCode);
    }

    void addRole() {
        if (roleService.viewAllRoles(getTenantId().id()).isEmpty())  // Only add admin role if it doesn't exist'
            roleService.createRole(getTenantId().id(), new AddRole("admin", permissions(), getTenantId().tenantCode(), Status.ACTIVE));
    }

    void addUser() {
        if (userService.viewAllUsers(getTenantId().id()).isEmpty())
            userService.createUser(getTenantId().id(),new AddUser("root", passwordEncoder.encode("root"), "admin@email.com", getTenantId().tenantCode(), "admin", User.UserStatus.ACTIVE));
    }

    private List<String> permissions() {
        List<String> permissions = new ArrayList<>();
        permissions.addAll(RolePermission.USER_MANAGEMENT.getPermissions());
        permissions.addAll(RolePermission.RESERVATION_MANAGEMENT.getPermissions());
        permissions.addAll(RolePermission.CUSTOMER_MANAGEMENT.getPermissions());
        permissions.addAll(RolePermission.PAYMENT_MANAGEMENT.getPermissions());
        return permissions;

    }
}

package com.purplemango.gms.util;

import com.purplemango.gms.models.core.enums.Status;
import com.purplemango.gms.models.iam.AddRole;
import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.User;
import com.purplemango.gms.models.security.RolePermission;
import com.purplemango.gms.service.PermissionService;
import com.purplemango.gms.service.RoleService;
import com.purplemango.gms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Transactional
public class InitializeData implements ApplicationRunner {

    final PermissionService permissionService;
    final RoleService roleService;
    final UserService userService;
    final PasswordEncoder passwordEncoder;


    @Autowired
    public InitializeData(PermissionService permissionService, RoleService roleService, UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addPermissions();
        addRole();
        addUser();
    }

    void  addPermissions() {
        if (permissionService.viewAllPermissions().isEmpty())
            permissionService.createPermission(permissions());
    }

    void addRole() {
        if (roleService.viewAllRoles().isEmpty())  // Only add admin role if it doesn't exist'
            roleService.createRole(new AddRole("admin", permissions(), Status.ACTIVE));
    }

    void addUser() {
        if (userService.viewAllUsers().isEmpty())
            userService.createUser(new AddUser("root", passwordEncoder.encode("root"), "admin@email.com", "admin", User.UserStatus.ACTIVE));
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

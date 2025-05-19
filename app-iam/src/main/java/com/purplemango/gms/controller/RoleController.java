package com.purplemango.gms.controller;

import com.purplemango.gms.models.iam.AddRole;
import com.purplemango.gms.models.iam.Role;
import com.purplemango.gms.service.RoleService;
import com.purplemango.gms.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final TenantService tenantService;

    @Autowired
    public RoleController(RoleService roleService, TenantService tenantService) {
        this.roleService = roleService;
        this.tenantService = tenantService;
    }
    @GetMapping("/{tenant-id}")
    public ResponseEntity<Collection<Role>> viewAllRoles(@PathVariable("tenant-id") String tenantId) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roleService.viewAllRoles(tenantId));
    }

    @GetMapping("/{tenant-id}/find-by-name")
    public ResponseEntity<Role> viewRoleByName(@PathVariable("tenant-id") String tenantId, @RequestParam("role-name") String roleName) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roleService.viewByName(tenantId, roleName));
    }

    @GetMapping("/{tenant-id}/find-by-id")
    public ResponseEntity<Role> viewRoleById(@PathVariable("tenant-id") String tenantId, @RequestParam("role-id") String roleId) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roleService.viewById(tenantId, roleId));
    }

    @PostMapping("/{tenant-id}")
    public ResponseEntity<String> createRole(@PathVariable("tenant-id") String tenantId, @RequestBody @Valid AddRole entity) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        roleService.createRole(tenantId, entity);
        return ResponseEntity.ok("Role created successfully");
    }
}

package com.purplemango.gms.controller;

import com.purplemango.gms.models.iam.AddRole;
import com.purplemango.gms.models.iam.Role;
import com.purplemango.gms.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping
    public ResponseEntity<Collection<Role>> viewAllRoles() {
        return ResponseEntity.ok(roleService.viewAllRoles());
    }

    @GetMapping("/name/{role-name}")
    public ResponseEntity<Role> viewRoleByName(@PathVariable("role-name") String roleName) {
        return ResponseEntity.ok(roleService.viewByName(roleName));
    }

    @GetMapping("/{role-id}")
    public ResponseEntity<Role> viewRoleById(@PathVariable("role-id") String roleId) {
        return ResponseEntity.ok(roleService.viewById(roleId));
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody @Valid AddRole entity) {
        roleService.createRole(entity);
        return ResponseEntity.ok("Role created successfully");
    }
}

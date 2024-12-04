package com.purplemango.gms.controller;

import com.purplemango.gms.models.iam.Permission;
import com.purplemango.gms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<Collection<Permission>> viewAllPermissions() {
        return ResponseEntity.ok(permissionService.viewAllPermissions());
    }

    @GetMapping("/list")
    public ResponseEntity<Collection<String>> viewAllPermissionsAsString() {
        return ResponseEntity.ok(permissionService.viewAllPermissions().stream().map(Permission::permissionName).toList());
    }
}

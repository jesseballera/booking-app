package com.purplemango.gms.models.security;

import com.purplemango.gms.models.iam.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum RolePermission {



    RESERVATION_MANAGEMENT(true, "Reservation Management",
            Permission.RESERVATION_CREATE,
            Permission.RESERVATION_READ,
            Permission.RESERVATION_UPDATE,
            Permission.RESERVATION_CANCEL,
            Permission.RESERVATION_APPROVE,
            Permission.RESERVATION_LIST
    ), USER_MANAGEMENT(true, "User Management",
            Permission.IAM_USER_CREATE,
            Permission.IAM_USER_READ,
            Permission.IAM_USER_UPDATE,
            Permission.IAM_USER_BLOCK,
            Permission.IAM_USER_DELETE,
            Permission.IAM_USER_LIST,
            Permission.IAM_PASSWORD_OTHER_UPDATE,
            Permission.IAM_PASSWORD_UPDATE,
            Permission.IAM_ROLE_CREATE,
            Permission.IAM_ROLE_READ,
            Permission.IAM_ROLE_UPDATE,
            Permission.IAM_ROLE_DELETE,
            Permission.IAM_ROLE_LIST
    ), CUSTOMER_MANAGEMENT(true, "Customer Management",
            Permission.CUSTOMER_CREATE,
            Permission.CUSTOMER_READ,
            Permission.CUSTOMER_UPDATE,
            Permission.CUSTOMER_DELETE,
            Permission.CUSTOMER_LIST
    ), PAYMENT_MANAGEMENT(true, "Payment Management",
            Permission.PAYMENT_ADD,
            Permission.PAYMENT_APPROVE,
            Permission.PAYMENT_UPDATE,
            Permission.PAYMENT_CANCEL,
            Permission.PAYMENT_HOLD
    );

    private final List<String> permissions;

    private final boolean enabled;

    private final String name;

    private RolePermission(boolean enabled, String name, String... permissions) {
        this.enabled = enabled;
        this.name = name;
        this.permissions = new ArrayList<>();
        this.permissions.addAll(Arrays.asList(permissions));
    }
}

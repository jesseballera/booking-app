package com.purplemango.gms.models.iam;

import com.purplemango.gms.models.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Document(collection = "permissions")
public record Permission (
        @MongoId String id,
        String permissionName,
        Status status){

    public static Permission addPermission(AddPermission entity) {
        return new Permission(UUID.randomUUID().toString(), entity.permissionName(), Status.ACTIVE);
    }

    public static Permission updatePermission(UpdatePermission entity, String entityId) {
        return new Permission(entityId, entity.permissionName(), entity.status());
    }

    @Getter
    @AllArgsConstructor
    public enum PermissionLabel {
        RESERVATION("Reservation Management"),
        CUSTOMER("Customer Management"),
        USER("User Management"),
        PAYMENT("Payment Management");

        private final String value;

    }

    public static final String IAM_USER_CREATE = "iam:user:create";
    public static final String IAM_USER_LIST = "iam:user:list";
    public static final String IAM_USER_READ = "iam:user:read";
    public static final String IAM_USER_UPDATE = "iam:user:update";
    public static final String IAM_USER_DELETE = "iam:user:delete";
    public static final String IAM_USER_BLOCK = "iam:user:block";
    public static final String IAM_PASSWORD_OTHER_UPDATE = "iam:password:other:update";
    public static final String IAM_PASSWORD_UPDATE = "iam:password:update";

    public static final String IAM_ROLE_CREATE = "iam:role:create";
    public static final String IAM_ROLE_LIST = "iam:role:list";
    public static final String IAM_ROLE_READ = "iam:role:read";
    public static final String IAM_ROLE_UPDATE = "iam:role:update";
    public static final String IAM_ROLE_DELETE = "iam:role:delete";

    public static final String CUSTOMER_CREATE = "customer:create";
    public static final String CUSTOMER_LIST = "customer:list";
    public static final String CUSTOMER_READ = "customer:read";
    public static final String CUSTOMER_UPDATE = "customer:update";
    public static final String CUSTOMER_DELETE = "customer:delete";

    public static final String RESERVATION_CREATE = "reservation:create";
    public static final String RESERVATION_LIST = "reservation:list";
    public static final String RESERVATION_READ = "reservation:read";
    public static final String RESERVATION_UPDATE = "reservation:update";
    public static final String RESERVATION_CANCEL = "reservation:delete";
    public static final String RESERVATION_APPROVE = "reservation:approve";

    public static final String PAYMENT_ADD = "payment:add";
    public static final String PAYMENT_UPDATE = "payment:update";
    public static final String PAYMENT_CANCEL = "payment:cancel";
    public static final String PAYMENT_APPROVE = "payment:approve";
    public static final String PAYMENT_HOLD = "payment:hold";

    public static Map<String, String> asMap() {
        Map<String, String> map = new TreeMap<>();

        return map;
    }

}

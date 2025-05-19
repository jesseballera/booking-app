package com.purplemango.gms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddTenant(
        String tenantName,
        String tenantCode) { }

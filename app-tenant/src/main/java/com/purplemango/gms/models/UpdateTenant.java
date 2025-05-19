package com.purplemango.gms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateTenant(
        String id,
        String tenantName) { }

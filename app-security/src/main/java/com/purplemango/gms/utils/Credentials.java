/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purplemango.gms.utils;


import jakarta.validation.constraints.NotNull;

/**
 *
 * @author jesse
 */
public record Credentials(
        @NotNull(message = "Username is required")
        String username,

        @NotNull(message = "Password id required")
        String password){}

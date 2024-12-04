/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purplemango.gms.utils;

import java.util.Collection;

/**
 *
 * @author jesse
 */
public record JwtAuthenticationResponse(
        String username,
        String email,
//        String role,
        Collection<String> permissions) { }

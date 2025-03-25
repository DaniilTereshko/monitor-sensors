package com.tdi.userservice.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    VIEWER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
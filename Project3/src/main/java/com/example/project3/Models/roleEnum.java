package com.example.project3.Models;

import org.springframework.security.core.GrantedAuthority;

public enum roleEnum implements GrantedAuthority {
    USER, ADMIN, LIB, CATALOG;
    @Override
    public String getAuthority()
    {
        return name();
    }
}

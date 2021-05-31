package com.nbnfsoft.admin.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class NFUPAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public boolean hostSide;

    public NFUPAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public NFUPAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}

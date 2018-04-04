package com.laputa.foundation.web.security.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by jiangdongping on 2017/8/30 0030.
 */
public class LaputaUnLoginAuthentication implements Authentication {

    private static LaputaUnLoginAuthentication laputaUnLoginAuthentication = new LaputaUnLoginAuthentication();


    public static LaputaUnLoginAuthentication getInstance() {
        return laputaUnLoginAuthentication;
    }

    private LaputaUnLoginAuthentication() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}

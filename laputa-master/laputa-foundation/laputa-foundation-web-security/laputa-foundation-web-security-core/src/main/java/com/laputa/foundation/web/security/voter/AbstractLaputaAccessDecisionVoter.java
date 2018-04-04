package com.laputa.foundation.web.security.voter;

import com.laputa.foundation.web.security.core.LaputaUserDetails;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by JiangDongPing on 2016/12/22.
 */
public abstract class AbstractLaputaAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {


    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        //if(String.class != null){return  1;}
        if (authentication == null) {
            return ACCESS_DENIED;
        }

        /**
         * 优先判断反转权限
         */
        List<? extends GrantedAuthority> invertedAuthorities = extractInvertedAuthorities(authentication);
        if (invertedAuthorities != null && invertedAuthorities.size() > 0) {
            for (ConfigAttribute attribute : attributes) {
                if (this.supports(attribute)) {
                    for (GrantedAuthority authority : invertedAuthorities) {
                        if (attribute.getAttribute().equals(authority.getAuthority())) {
                            return ACCESS_DENIED;
                        }
                    }
                }
            }
        }

        int result = ACCESS_ABSTAIN;
        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

        for (ConfigAttribute attribute : attributes) {
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                if (!authentication.isAuthenticated()) {
                    throw new InsufficientAuthenticationException("unlogin");
                }

                // Attempt to find a matching granted authority
                if (authorities != null && attributes.size() > 0) {
                    for (GrantedAuthority authority : authorities) {
                        if (attribute.getAttribute().equals(authority.getAuthority())) {
                            return ACCESS_GRANTED;
                        }
                    }
                }
            }
        }

        return result;
    }

    private List<? extends GrantedAuthority> extractInvertedAuthorities(Authentication authentication) {
        if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof LaputaUserDetails) {
            return ((LaputaUserDetails) authentication.getPrincipal()).getInvertedAuthorities();
        }
        return Collections.emptyList();
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }
}

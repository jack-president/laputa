package com.laputa.foundation.web.security.voter;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * 扩展支持任何人 IS_ANYONE
 * Created by jiangdongping on 2017/8/29 0029.
 */
public class LaputaAuthenticatedVoter extends AuthenticatedVoter {
    public static final String IS_ANYONE = "IS_ANYONE";

    public boolean supports(ConfigAttribute attribute) {
        if ((attribute.getAttribute() != null)
                && (IS_AUTHENTICATED_FULLY.equals(attribute.getAttribute())
                || IS_AUTHENTICATED_REMEMBERED.equals(attribute.getAttribute())
                || IS_AUTHENTICATED_ANONYMOUSLY.equals(attribute.getAttribute())
                || IS_ANYONE.equals(attribute.getAttribute()))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        for (ConfigAttribute attribute : attributes) {
            if (this.supports(attribute)) {
                if (IS_ANYONE.equals(attribute.getAttribute())) {
                    return ACCESS_GRANTED;
                }
            }
        }

        return super.vote(authentication, object, attributes);
    }
}

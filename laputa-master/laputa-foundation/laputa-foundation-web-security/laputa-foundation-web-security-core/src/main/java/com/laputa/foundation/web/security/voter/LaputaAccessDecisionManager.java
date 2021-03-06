package com.laputa.foundation.web.security.voter;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * Created by jiangdongping on 2017/4/17 0017.
 */
public class LaputaAccessDecisionManager extends AbstractAccessDecisionManager {

    public LaputaAccessDecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        int grant = 0;
        //int abstain = 0;

        for (AccessDecisionVoter voter : getDecisionVoters()) {
            int result = voter.vote(authentication, object, configAttributes);

            if (logger.isDebugEnabled()) {
                logger.debug("Voter: " + voter + ", returned: " + result);
            }

            switch (result) {
                case AccessDecisionVoter.ACCESS_GRANTED:
                    grant++;

                    break;

                case AccessDecisionVoter.ACCESS_DENIED:
                    throw new AccessDeniedException(messages.getMessage(
                            "AbstractAccessDecisionManager.accessDenied",
                            "Access is denied"));

                default:
                    //abstain++;
                    break;
            }

        }

        // To get this far, there were no deny votes
        if (grant > 0) {
            return;
        }

        // To get this far, every AccessDecisionVoter abstained
        checkAllowIfAllAbstainDecisions();
    }
}

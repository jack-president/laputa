package com.laputa.foundation.web.security.service;

import com.laputa.foundation.web.security.core.LaputaSecurityContext;
import com.laputa.foundation.web.security.core.LaputaUnLoginAuthentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

/**
 * Created by jiangdongping on 2017/4/17 0017.
 */
@Service("laputaSessionSecurityContextRepository")
public class LaputaSessionSecurityContextRepository extends HttpSessionSecurityContextRepository {

    @Override
    protected SecurityContext generateNewContext() {
        LaputaSecurityContext laputaSecurityContext = new LaputaSecurityContext();
        laputaSecurityContext.setAuthentication(LaputaUnLoginAuthentication.getInstance());
        return laputaSecurityContext;
    }
}

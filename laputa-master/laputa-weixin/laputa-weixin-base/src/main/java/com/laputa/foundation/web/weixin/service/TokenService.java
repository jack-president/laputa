package com.laputa.foundation.web.weixin.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import weixin.popular.support.TokenManager;

/**
 * Created by jiangdongping on 2017/9/11 0011.
 */
@Service("tokenService")
public class TokenService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        TokenManager.init("wx84f8c874735fddf5", "39609ba93ec7c083715b22706d82aa29");
    }
}

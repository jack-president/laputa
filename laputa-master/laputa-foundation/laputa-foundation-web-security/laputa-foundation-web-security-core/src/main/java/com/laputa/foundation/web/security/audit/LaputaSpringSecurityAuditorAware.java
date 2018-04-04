package com.laputa.foundation.web.security.audit;

import com.laputa.foundation.persistence.audit.LaputaAuditorAware;
import com.laputa.foundation.web.security.util.SecurityUtils;

import java.util.Optional;

/**
 * Created by JiangDongPing on 2016/12/29.
 */
public class LaputaSpringSecurityAuditorAware implements LaputaAuditorAware {
    @Override
    public Optional<String> getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return Optional.of(userName != null ? userName : "system");
    }
}

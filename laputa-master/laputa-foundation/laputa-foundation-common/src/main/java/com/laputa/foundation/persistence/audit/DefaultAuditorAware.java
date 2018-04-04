package com.laputa.foundation.persistence.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created by JiangDongPing on 2016/12/6.
 */
public class DefaultAuditorAware implements AuditorAware<String> {

    @Autowired(required = false)
    LaputaAuditorAware laputaAuditorAware;

    @Override
    public Optional<String> getCurrentAuditor() {
        if (laputaAuditorAware != null) {
            return laputaAuditorAware.getCurrentAuditor();
        } else {
            return Optional.of("LAPUTA-UNCONFIG");
        }
    }
}

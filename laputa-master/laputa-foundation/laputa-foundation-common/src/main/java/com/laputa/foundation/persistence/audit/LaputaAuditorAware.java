package com.laputa.foundation.persistence.audit;

import java.util.Optional;

/**
 * Created by JiangDongPing on 2016/12/6.
 */
public interface LaputaAuditorAware {
    Optional<String> getCurrentAuditor();
}

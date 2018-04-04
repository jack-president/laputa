package com.laputa.foundation.web.security.voter;

import com.laputa.foundation.web.security.access.SysFileConfigAttribute;
import org.springframework.security.access.ConfigAttribute;

/**
 * Created by JiangDongPing on 2016/12/22.
 */
public class LaputaSysFileAccessDecisionVoter extends AbstractLaputaAccessDecisionVoter {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        if (attribute instanceof SysFileConfigAttribute) {
            return true;
        } else {
            return false;
        }
    }

}

package com.laputa.foundation.web.security.access;

import com.laputa.foundation.web.rbac.model.SysOperationModel;
import org.springframework.security.access.ConfigAttribute;

/**
 * Created by JiangDongPing on 2016/12/27.
 */
public class SysOperationConfigAttribute implements ConfigAttribute {

    private final SysOperationModel grantedAuthority;

    private final String authorityPrefix;

    private final String authority;

    public SysOperationConfigAttribute(SysOperationModel configAttribute) {
        this.grantedAuthority = configAttribute;
        this.authorityPrefix = AccessPrefixConstant.SYS_OPERATION_AUTHORITY_PREFIX;
        this.authority = authorityPrefix + "_" + grantedAuthority.getCode();
    }

    public final SysOperationModel getGrantedAuthority() {
        return grantedAuthority;
    }

    @Override
    public final String getAttribute() {
        return authority;
    }

    public final String getAuthorityPrefix() {
        return authorityPrefix;
    }

    @Override
    public String toString() {
        return String.format("{cname:%s,authority: %s}", getGrantedAuthority().getCname(), getAttribute());
    }
}

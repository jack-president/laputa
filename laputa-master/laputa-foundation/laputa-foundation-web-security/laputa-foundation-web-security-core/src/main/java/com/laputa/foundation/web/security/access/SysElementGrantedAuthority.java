package com.laputa.foundation.web.security.access;


import com.laputa.foundation.web.rbac.model.SysElementModel;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by JiangDongPing on 2016/12/27.
 */
public class SysElementGrantedAuthority implements GrantedAuthority {

    private final SysElementModel grantedAuthority;

    private final String authorityPrefix;

    private final String authority;

    public SysElementGrantedAuthority(SysElementModel grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
        this.authorityPrefix = AccessPrefixConstant.SYS_OPERATION_AUTHORITY_PREFIX;
        this.authority = authorityPrefix + "_" + grantedAuthority.getCode();

    }

    public final SysElementModel getGrantedAuthority() {
        return grantedAuthority;
    }

    @Override
    public final String getAuthority() {
        return authority;
    }

    public final String getAuthorityPrefix() {
        return authorityPrefix;
    }

    @Override
    public String toString() {
        return String.format("{cname:%s,authority: %s}", getGrantedAuthority().getCname(), getAuthority());
    }

}

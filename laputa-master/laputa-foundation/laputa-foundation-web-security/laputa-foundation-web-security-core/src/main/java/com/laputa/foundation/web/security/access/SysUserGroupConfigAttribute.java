package com.laputa.foundation.web.security.access;

import com.laputa.foundation.web.rbac.model.SysUserGroupModel;
import org.springframework.security.access.ConfigAttribute;

/**
 * Created by JiangDongPing on 2016/12/28.
 */
public class SysUserGroupConfigAttribute implements ConfigAttribute {

    private final SysUserGroupModel configAttribute;
    private final String attributePrefix;
    private final String attribute;

    public SysUserGroupConfigAttribute(SysUserGroupModel sysUserGroup) {
        this.configAttribute = sysUserGroup;
        this.attributePrefix = AccessPrefixConstant.SYS_USER_GROUP_AUTHORITY_PREFIX;
        this.attribute = attributePrefix + "_" + configAttribute.getCode();
    }

    public SysUserGroupModel getConfigAttribute() {
        return this.configAttribute;
    }

    public String getAttributePrefix() {
        return this.attributePrefix;
    }

    public String getAttribute() {
        return this.attribute;
    }

    public String toString() {
        return String.format("{cname:%s,code: %s}", new Object[]{this.getConfigAttribute().getCname(), this.getConfigAttribute().getCode()});
    }
}

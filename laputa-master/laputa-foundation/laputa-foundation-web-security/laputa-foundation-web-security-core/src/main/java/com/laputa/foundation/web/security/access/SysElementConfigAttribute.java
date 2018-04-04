package com.laputa.foundation.web.security.access;

import com.laputa.foundation.web.rbac.model.SysElementModel;
import org.springframework.security.access.ConfigAttribute;

/**
 * Created by JiangDongPing on 2016/12/27.
 */
public class SysElementConfigAttribute implements ConfigAttribute {

    private final SysElementModel configAttribute;
    private final String attributePrefix;
    private final String attribute;

    public SysElementConfigAttribute(SysElementModel configAttribute) {
        this.configAttribute = configAttribute;
        this.attributePrefix = AccessPrefixConstant.SYS_OPERATION_AUTHORITY_PREFIX;
        this.attribute = attributePrefix + "_" + configAttribute.getCode();
    }

    public SysElementModel getConfigAttribute() {
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

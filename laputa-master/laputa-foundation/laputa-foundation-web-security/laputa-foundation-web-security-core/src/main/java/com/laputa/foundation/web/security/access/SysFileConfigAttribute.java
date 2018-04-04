package com.laputa.foundation.web.security.access;

import com.laputa.foundation.web.rbac.model.SysFileModel;
import org.springframework.security.access.ConfigAttribute;

/**
 * Created by JiangDongPing on 2016/12/27.
 */
public class SysFileConfigAttribute implements ConfigAttribute {

    private final SysFileModel sysFile;

    private final String attributePrefix = AccessPrefixConstant.SYS_FILE_AUTHORITY_PREFIX;

    private final String attribute;

    public SysFileConfigAttribute(SysFileModel sysFile) {
        this.sysFile = sysFile;
        this.attribute = attributePrefix + "_" + sysFile.getId().toString();
    }

    public String getAttributePrefix() {
        return attributePrefix;
    }

    @Override
    public String getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return String.format("{ cname : %s, id : %s, path : %s , attribute : %s}", sysFile.getCname(), sysFile.getId(), sysFile.getPath(), attribute);
    }
}

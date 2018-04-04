package com.laputa.foundation.persistence.util;

import com.laputa.foundation.persistence.code.ParentAbleIdEntity;

/**
 * Created by JiangDongPing on 2017/1/10.
 */
public class ParentAbleIdEntityCheckCircularReferenceUtil {
    public final static Boolean checkCircularReference(Long id, ParentAbleIdEntity tryReferenceEntity) {
        if (tryReferenceEntity == null) {
            return Boolean.FALSE;
        } else if (id.equals(tryReferenceEntity.getId())) {
            return Boolean.TRUE;
        }
        return ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(id, tryReferenceEntity.getParent());
    }
}

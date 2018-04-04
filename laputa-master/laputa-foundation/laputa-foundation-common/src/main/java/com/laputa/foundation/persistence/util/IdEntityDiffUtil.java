package com.laputa.foundation.persistence.util;

import java.util.Collection;

import com.laputa.foundation.common.CollectionDiffUtil;
import com.laputa.foundation.persistence.entity.IdEntity;

/**
 * Created by JiangDongPing on 2016/11/18.
 */
public class IdEntityDiffUtil {

    private final static CollectionDiffUtil.Compare<? extends IdEntity> idEntityDiffCompare = new CollectionDiffUtil.Compare<IdEntity>() {
        @Override
        public Boolean equal(IdEntity left, IdEntity right) {
            return left.getId().equals(right.getId());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T extends IdEntity> Collection<T> inLeftButNotInRiht(Collection<T> left, Collection<T> right) {
        return CollectionDiffUtil.inLeftButNotInRiht(left, right, (CollectionDiffUtil.Compare<T>) idEntityDiffCompare);
    }
}

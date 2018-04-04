package com.laputa.foundation.common;

import java.util.ArrayList;
import java.util.Collection;

import com.laputa.foundation.persistence.entity.IdEntity;

/**
 * Created by JiangDongPing on 2016/12/2.
 */
public class CollectionDiffUtil {
    public interface Compare<T> {
        Boolean equal(T left, T right);
    }

    /**
     * 统计在左边集合但是不在右边集合的实体
     *
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    public static <T> Collection<T> inLeftButNotInRiht(
            Collection<T> left, Collection<T> right, Compare<T> compare) {
        Collection<T> inLeftNotInRihtCollection = new ArrayList<>();
        if (left != null && left.size() > 0) {
            if (right == null || right.size() == 0) {
                inLeftNotInRihtCollection.addAll(left);
            } else {
                for (T leftIdEntity : left) {
                    Boolean isInLeftNotInRiht = Boolean.TRUE;
                    for (T rightIdEntity : right) {
                        if (compare.equal(leftIdEntity, rightIdEntity)) {
                            isInLeftNotInRiht = Boolean.FALSE;
                            break;
                        }
                    }
                    if (isInLeftNotInRiht) {
                        inLeftNotInRihtCollection.add(leftIdEntity);
                    }
                }
            }
        }
        return inLeftNotInRihtCollection;
    }
}

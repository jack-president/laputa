package com.laputa.foundation.spring.beancopy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiangDongPing on 2016/11/15.
 */
public class BeanCopyUtil {
    public static <T, S> List<T> listCopy(
            List<S> srcLit, Class<T> toClass, org.springframework.cglib.beans.BeanCopier beanCopier,
            org.springframework.cglib.core.Converter converter) {
        if (srcLit == null || srcLit.size() == 0) {
            return null;
        }
        List<T> toList = new ArrayList<T>(srcLit.size());
        for (S src : srcLit) {
            try {
                T to = toClass.newInstance();
                toList.add(to);
                beanCopier.copy(src, to, converter);
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
        return toList;
    }
}

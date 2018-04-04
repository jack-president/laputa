package com.laputa.foundation.web.security.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiangdongping on 2017/8/30 0030.
 */
public final class HttpServletRequestDataWrap {

    public static  <T> void putWrapAttr(HttpServletRequest request, T data) {
        if (data != null) {
            request.setAttribute(HttpServletRequestDataWrap.class.getName()
                    + "."
                    + data.getClass().getName(), data);
        }
    }

    public static <T> T getWrapAttr(HttpServletRequest request, Class<T> clzz) {
        return (T) request.getAttribute(HttpServletRequestDataWrap.class.getName()
                + "."
                + clzz.getName());
    }
}

package com.laputa.foundation.web.security.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpSession;


/**
 * Created by JiangDongPing on 2016/12/21.
 */
public class SecuritySessionUtil {
    public static Boolean checkCaptcha(HttpSession session, String captcha) {
        Object sessionValue = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (sessionValue == null) {
            return Boolean.FALSE;
        }

        if (sessionValue.toString().equalsIgnoreCase(captcha)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean checkCaptchaAndClear(HttpSession session, String captcha) {
        Boolean result = checkCaptcha(session, captcha);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        return result;
    }
}

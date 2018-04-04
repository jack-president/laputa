package com.laputa.foundation.web.security.exception;

import com.laputa.foundation.common.exception.LaputaExceptionEnumInterface;
import com.laputa.foundation.web.exception.LaputaWebOperationException;

import java.text.MessageFormat;

/**
 * Created by jiangdongping on 2018/2/25 0025.
 */
public class LaputaWebOperationSecurityException extends LaputaWebOperationException {

    private static final Integer SUB_TYPE_WEB_OPERATION_SECURITY = 1;

    private LaputaWebOperationSecurityException(Integer code, String message) {
        super(code, message);
    }


    @Override
    public Integer takeSubType() {
        return super.takeSubType() + SUB_TYPE_WEB_OPERATION_SECURITY;
    }

    public enum ExceptionEnum implements LaputaExceptionEnumInterface<LaputaWebOperationSecurityException> {
        LOGIN_FAIL(1, "用户名或密码匹配失败"),
        USERNAMENOTFOUND(2, "用户名不存在"),
        CAPTCHA_FAIL(3, "验证码验证失败"),
        RBAC_METADATA_INIT_FAIL(3, "权限元数据加载失败");

        private Integer code;
        private String message;


        ExceptionEnum(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public Class<LaputaWebOperationSecurityException> getExceptionClass() {
            return LaputaWebOperationSecurityException.class;
        }

        @Override
        public LaputaWebOperationSecurityException generateException() {
            return new LaputaWebOperationSecurityException(this.code, this.message);
        }

        @Override
        public LaputaWebOperationSecurityException generateException(String message, Object... arguments) {
            if (arguments != null && arguments.length > 0) {
                message = MessageFormat.format(message, arguments);
            }
            StringBuffer stringBuffer = new StringBuffer(this.message);
            stringBuffer.append(" : ");
            stringBuffer.append(message);
            return new LaputaWebOperationSecurityException(this.code, stringBuffer.toString());
        }
    }
}

package com.laputa.foundation.configurer.exception;

import com.laputa.foundation.common.exception.LaputaBaseException;
import com.laputa.foundation.common.exception.LaputaExceptionEnumInterface;

import java.text.MessageFormat;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public class LaputaConfigurerException extends LaputaBaseException {

    private static final Integer MAJOR_TYPE_CONFIGURER = 3;
    private static final Integer SUB_TYPE_CONFIGURER = 0;


    public enum ExceptionEnum implements LaputaExceptionEnumInterface<LaputaConfigurerException> {
        NORMAL_CONFIGURER_FAIL(1, "配置中心异常"),
        FIELD_INJECT_FAIL(2, "通过字段注入属性异常"),
        METHOD_INJECT_FAIL(3, "通过方法注入属性异常");

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
        public Class<LaputaConfigurerException> getExceptionClass() {
            return LaputaConfigurerException.class;
        }

        @Override
        public LaputaConfigurerException generateException() {
            return new LaputaConfigurerException(this.code, this.message);
        }

        @Override
        public LaputaConfigurerException generateException(String message, Object... arguments) {
            if (arguments != null && arguments.length > 0) {
                message = MessageFormat.format(message, arguments);
            }
            StringBuffer stringBuffer = new StringBuffer(this.message);
            stringBuffer.append(" : ");
            stringBuffer.append(message);
            return new LaputaConfigurerException(this.code, stringBuffer.toString());
        }
    }


    protected LaputaConfigurerException(Integer code, String message) {
        super(code, message, null);
    }

    @Override
    public final Integer takeMajorType() {
        return MAJOR_TYPE_CONFIGURER;
    }

    @Override
    public Integer takeSubType() {
        return SUB_TYPE_CONFIGURER;
    }

    @Override
    public String takeTypeDescript() {
        return "配置中心相关异常";
    }
}

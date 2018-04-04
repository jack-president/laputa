package com.laputa.foundation.web.exception;

import com.laputa.foundation.common.exception.LaputaBaseException;
import com.laputa.foundation.common.exception.LaputaExceptionEnumInterface;

import java.text.MessageFormat;

/**
 * WEB操作用户类型异常
 * Created by jiangdongping on 2018/2/25 0025.
 */
public class LaputaWebOperationException extends LaputaBaseException {

    private static final Integer MAJOR_TYPE_WEB_OPERATION = 2;
    private static final Integer SUB_TYPE_WEB_OPERATION = 0;

    public enum ExceptionEnum implements LaputaExceptionEnumInterface<LaputaWebOperationException> {
        NORMAL_OPERATION_FAIL(1, "操作失败"),
        DESTORY_TARGET_NONEXISTENT(2, "删除对象不存在"),
        UPDATE_TARGET_NONEXISTENT(3, "更新对象不存在"),
        RELATION_TARGET_NONEXISTENT(4, "关联对象不存在"),
        RELATION_PARENT_NONEXISTENT(5, "关联父级不存在"),
        RELATION_EXIS_CIRCLE(6, "关联存在嵌套"),
        RELATION_FIELD_MUST_EXI(7, "关联对象必须存在");

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
        public Class<LaputaWebOperationException> getExceptionClass() {
            return LaputaWebOperationException.class;
        }

        @Override
        public LaputaWebOperationException generateException() {
            return new LaputaWebOperationException(this.code, this.message);
        }

        @Override
        public LaputaWebOperationException generateException(String message, Object... arguments) {
            if (arguments != null && arguments.length > 0) {
                message = MessageFormat.format(message, arguments);
            }
            StringBuffer stringBuffer = new StringBuffer(this.message);
            stringBuffer.append(" : ");
            stringBuffer.append(message);
            return new LaputaWebOperationException(this.code, stringBuffer.toString());
        }
    }


    protected LaputaWebOperationException(Integer code, String message) {
        super(code, message, null);
    }

    @Override
    public final Integer takeMajorType() {
        return MAJOR_TYPE_WEB_OPERATION;
    }

    @Override
    public Integer takeSubType() {
        return SUB_TYPE_WEB_OPERATION;
    }

    @Override
    public String takeTypeDescript() {
        return "用户在WEB界面操作错误,异常消息直接提示到用户";
    }
}

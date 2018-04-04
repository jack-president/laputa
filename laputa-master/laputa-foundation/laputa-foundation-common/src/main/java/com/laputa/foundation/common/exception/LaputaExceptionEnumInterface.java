package com.laputa.foundation.common.exception;

/**
 * Created by jiangdongping on 2018/2/25 0025.
 */
public interface LaputaExceptionEnumInterface<T extends LaputaBaseException> {
    Integer getCode();

    String getMessage();

    T generateException();

    T generateException(String message, Object... arguments);

    Class<T> getExceptionClass();
}

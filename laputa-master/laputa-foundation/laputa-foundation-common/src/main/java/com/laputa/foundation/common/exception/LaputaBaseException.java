package com.laputa.foundation.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础异常类
 * Created by jiangdongping on 2018/2/25 0025.
 */
public abstract class LaputaBaseException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(LaputaBaseException.class);

    /**
     * 异常编码
     */
    private Integer code;

    /**
     * 异常消息
     */
    private String message;

    /**
     * 黑匣子
     */
    private Object blackBox;

    protected LaputaBaseException(Integer code, String message, Object blackBox) {
        super(message);
        boolean normal = true;
        Integer majorType = takeMajorType();
        Integer subType = takeSubType();
        if (majorType > 9) {
            logger.error("主异常类型码分配错误 {}", majorType);
            normal = false;
        }

        if (subType > 99) {
            logger.error("子异常类型码分配错误 {}", subType);
            normal = false;
        }

        if (code > 999999) {
            logger.error("异常编号码分配错误 {}", code);
            normal = false;
        }

        if (normal) {
            this.code = majorType * 100;
            this.code = (this.code + subType) * 1000000;
            this.code = this.code + code;
        } else {
            this.code = Integer.parseInt(majorType.toString() + subType.toString() + code.toString());
        }

        this.message = message;
        this.blackBox = blackBox;
    }

    /**
     * 异常类型 1*100 ~ 9*100
     * <p>
     * 数字 1 开头的错误码表示系统级别的错误，比如缺少某种字符集，连不上数据库之类的，系统级的错误码不需要分模块，可以按照自增方式进行添加
     * 数字 2 开头的错误码表示API参数校验失败，比如 “交易模块下单场景中，订单金额参数不能为空” 可以用 40111 错误码来表示
     * 错误码按需分配，逐步增加，灵活扩展
     */
    public abstract Integer takeMajorType();

    public abstract Integer takeSubType();

    public String takeTypeDescript() {
        return "";
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBlackBox() {
        return blackBox;
    }

    public void setBlackBox(Object blackBox) {
        this.blackBox = blackBox;
    }
}

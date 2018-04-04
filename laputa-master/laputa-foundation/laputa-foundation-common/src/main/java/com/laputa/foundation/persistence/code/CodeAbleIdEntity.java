package com.laputa.foundation.persistence.code;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by JiangDongPing on 2016/12/28.
 */
@MappedSuperclass
public abstract class CodeAbleIdEntity extends NameAbleIdEntity {
    private String code;

    @Column(name = "code", nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

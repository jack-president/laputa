package com.laputa.foundation.configurer.listener;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public interface ConfListener {


    /**
     * @param source   root : app_name/ver 配置; 其他:分组名
     * @param key      属性名
     * @param preValue 之前值
     * @param curValue 当前值
     * @throws Exception
     */
    void onChange(String key, String preValue, String curValue) throws Exception;
}

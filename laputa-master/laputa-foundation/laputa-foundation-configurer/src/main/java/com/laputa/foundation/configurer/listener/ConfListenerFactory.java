package com.laputa.foundation.configurer.listener;

import com.laputa.foundation.configurer.core.PrepNodeData;
import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public class ConfListenerFactory {
    private static Logger logger = LoggerFactory.getLogger(ConfListenerFactory.class);

    /**
     * xxl conf listener repository
     */
    private static ConcurrentHashMap<String, List<ConfListener>> keyListenerRepository = new ConcurrentHashMap<>();
    private static List<ConfListener> noKeyConfListener = Collections.synchronizedList(new ArrayList<ConfListener>());

    private static List<PrepNodeData> injectByFileldNodePrepNodeDatas = Collections.synchronizedList(new ArrayList<PrepNodeData>());
    private static List<PrepNodeData> injectByMethodNodeDatas = Collections.synchronizedList(new ArrayList<PrepNodeData>());


    private static ConcurrentHashMap<String, List<ConfListener>> keySourceListenerRepository = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, List<ConfListener>> noKeySourceListenerRepository = new ConcurrentHashMap<>();


    public static void prepInjectByFileldNodeData(PrepNodeData prepNodeData) {
        injectByFileldNodePrepNodeDatas.add(prepNodeData);
    }

    public static void prepInjectByMethodNodeData(PrepNodeData prepNodeData) {
        injectByMethodNodeDatas.add(prepNodeData);
    }

    private static String sourceConnectKey(String source, String key) {
        return source + "." + key;
    }

    /**
     * add listener with xxl conf change
     *
     * @param key             empty will listener all key
     * @param xxlConfListener
     * @return
     */
    public synchronized static void addListener(String key, ConfListener xxlConfListener) {
        if (xxlConfListener == null) {
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException("配置监听器为空");
        }
        if (key == null || key.trim().length() == 0) {
            noKeyConfListener.add(xxlConfListener);
        } else {
            List<ConfListener> listeners = keyListenerRepository.get(key);
            if (listeners == null) {
                keyListenerRepository.putIfAbsent(key, Collections.synchronizedList(new ArrayList<>()));
                listeners = keyListenerRepository.get(key);
            }
            listeners.add(xxlConfListener);
        }
    }

    public synchronized static void addSourceListener(String source, String key, ConfListener xxlConfListener) {
        if (StringUtils.isEmpty(source)) {
            logger.error("错误的参数 source");
            return;
        }

        if (xxlConfListener == null) {
            logger.error("错误的参数 xxlConfListener");
            return;
        }

        if (StringUtils.isEmpty(key)) {
            List<ConfListener> listeners = noKeySourceListenerRepository.get(source);
            if (listeners == null) {
                noKeySourceListenerRepository.putIfAbsent(source, Collections.synchronizedList(new ArrayList<>()));
                listeners = noKeySourceListenerRepository.get(source);
            }
            listeners.add(xxlConfListener);
        } else {
            List<ConfListener> listeners = keySourceListenerRepository.get(sourceConnectKey(source, key));
            if (listeners == null) {
                keySourceListenerRepository.putIfAbsent(sourceConnectKey(source, key), Collections.synchronizedList(new ArrayList<>()));
                listeners = keySourceListenerRepository.get(sourceConnectKey(source, key));
            }
            listeners.add(xxlConfListener);
        }
    }

    private static void trrigerListener(String key, String preValue, String curValue, ConcurrentHashMap<String, List<ConfListener>> listConcurrentHashMap) {

        List<ConfListener> listeners = listConcurrentHashMap.get(key);
        if (listeners != null && listeners.size() > 0) {
            for (ConfListener listener : listeners) {
                try {
                    listener.onChange(key, preValue, curValue);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * invoke listener on xxl conf change
     *
     * @param key
     */
    public static void onChange(String source, String key, String preValue, String curValue) {
        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(key)) {
            logger.warn("未知的事件 {} {} {} {} ", source, key, preValue, curValue);
        }

        trrigerListener(sourceConnectKey(source, key), preValue, curValue, keySourceListenerRepository);

        trrigerListener(source, preValue, curValue, noKeySourceListenerRepository);


    }

    public static void onChange(String key, String preValue, String curValue) {
        if (key == null || key.trim().length() == 0) {
            return;
        }

        trrigerListener(key, preValue, curValue, keyListenerRepository);

        if (noKeyConfListener.size() > 0) {
            for (ConfListener confListener : noKeyConfListener) {
                try {
                    confListener.onChange(key, preValue, curValue);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

}

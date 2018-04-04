package com.laputa.foundation.configurer.listener;

import com.laputa.foundation.configurer.core.PrepNodeData;
import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    public static void prepInjectByFileldNodeData(PrepNodeData prepNodeData) {
        injectByFileldNodePrepNodeDatas.add(prepNodeData);
    }

    public static void prepInjectByMethodNodeData(PrepNodeData prepNodeData) {
        injectByMethodNodeDatas.add(prepNodeData);
    }

    /**
     * add listener with xxl conf change
     *
     * @param key             empty will listener all key
     * @param xxlConfListener
     * @return
     */
    public static void addListener(String key, ConfListener xxlConfListener) {
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

    /**
     * invoke listener on xxl conf change
     *
     * @param key
     */
    public static void onChange(String source, String key, String preValue, String curValue) {
        //TODO 直接监听某个源
    }

    public static void onChange(String key, String preValue, String curValue) {
        if (key == null || key.trim().length() == 0) {
            return;
        }
        List<ConfListener> keyListeners = keyListenerRepository.get(key);
        if (keyListeners != null && keyListeners.size() > 0) {
            for (ConfListener listener : keyListeners) {
                try {
                    listener.onChange(key, preValue, curValue);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
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

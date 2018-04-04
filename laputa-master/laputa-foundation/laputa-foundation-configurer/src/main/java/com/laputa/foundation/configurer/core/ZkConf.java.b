package com.laputa.foundation.configurer.core;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public class ZkConf {
    private static Logger logger = LoggerFactory.getLogger(ZkConf.class);


    public static String ZK_PATH = "/xxl-conf";

    // ------------------------------ zookeeper client ------------------------------

    private static ZkClient xxlZkClient = null;

    private static void init() {

        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    logger.info(">>>>>>>>>> xxl-conf: watcher:{}", watchedEvent);

                    // session expire, close old and create new
                    if (watchedEvent.getState() == Event.KeeperState.Expired) {
                        xxlZkClient.destroy();
                        xxlZkClient.getClient();
                        ConfigLocalCache.reloadAll();
                        logger.info(">>>>>>>>>> xxl-conf, zk re-connect reloadAll success.");
                    }

                    String path = watchedEvent.getPath();
                    String key = pathToKey(path);
                    if (key != null) {
                        // keep watch conf key：add One-time trigger
                        xxlZkClient.getClient().exists(path, true);
                        if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                            // conf deleted
                        } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                            // conf updated
                            String data = get(key);
                            ConfigLocalCache.update(key, data);
                        }
                    }
                } catch (KeeperException e) {
                    logger.error(e.getMessage(), e);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        };

        xxlZkClient = new ZkClient("127.0.0.1:2181", watcher);
        logger.info(">>>>>>>>>> xxl-conf, XxlConfZkConf init success.");
    }

    static {
        init();
    }

    public static void destroy() {
        if (xxlZkClient != null) {
            xxlZkClient.destroy();
        }
    }

    // ------------------------------ conf opt ------------------------------

    /**
     * set zk conf
     *
     * @param key
     * @param data
     * @return
     */
    public static void set(String key, String data) {
        String path = keyToPath(key);
        xxlZkClient.setPathData(path, data);
    }

    /**
     * delete zk conf
     *
     * @param key
     */
    public static void delete(String key) {
        String path = keyToPath(key);
        xxlZkClient.deletePath(path);
    }

    /**
     * get zk conf
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String path = keyToPath(key);
        return xxlZkClient.getPathData(path);
    }


    // ------------------------------ key 2 path / genarate key ------------------------------

    /**
     * path 2 key
     *
     * @param nodePath
     * @return ZnodeKey
     */
    public static String pathToKey(String nodePath) {
        if (nodePath == null || nodePath.length() <= ZK_PATH.length() || !nodePath.startsWith(ZK_PATH)) {
            return null;
        }
        return nodePath.substring(ZK_PATH.length() + 1, nodePath.length());
    }

    /**
     * key 2 path
     *
     * @param nodeKey
     * @return znodePath
     */
    public static String keyToPath(String nodeKey) {
        return ZK_PATH + "/" + nodeKey;
    }
}

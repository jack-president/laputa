package com.laputa.foundation.configurer.core;

import com.laputa.foundation.configurer.exception.LaputaConfigurerException;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by JiangDongPing on 2018/03/24.
 */
public class ZkClient {
    private static Logger logger = LoggerFactory.getLogger(ZkClient.class);


    private String zkServer;
    private Watcher watcher;    // watcher(One-time trigger)

    public ZkClient(String zkServer, Watcher watcher) {
        this.zkServer = zkServer;
        this.watcher = watcher;

        // reconnect when expire
        if (watcher == null) {
            // watcher(One-time trigger)
            watcher = new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    logger.info(">>>>>>>>>> xxl-conf: watcher:{}", watchedEvent);

                    // session expire, close old and create new
                    if (watchedEvent.getState() == Event.KeeperState.Expired) {
                        destroy();
                        getClient();
                    }
                }
            };
        }

    }

    // ------------------------------ zookeeper client ------------------------------
    private ZooKeeper zooKeeper;
    private ReentrantLock INSTANCE_INIT_LOCK = new ReentrantLock(true);

    public ZooKeeper getClient() {
        if (zooKeeper == null) {
            try {
                if (INSTANCE_INIT_LOCK.tryLock(2, TimeUnit.SECONDS)) {
                    if (zooKeeper == null) {        // 二次校验，防止并发创建client
                        try {
                            zooKeeper = new ZooKeeper(zkServer, 10000, watcher);
                        } finally {
                            INSTANCE_INIT_LOCK.unlock();
                        }
                        logger.info(">>>>>>>>>> xxl-conf, XxlZkClient init success.");
                    }
                }
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        if (zooKeeper == null) {
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException("XxlZkClient.zooKeeper is null.");
        }
        return zooKeeper;
    }

    public void destroy() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
                zooKeeper = null;
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    // ------------------------------ util ------------------------------

    /**
     * create node path with parent path (watch)
     * <p>
     * zk limit parent must exist
     *
     * @param path
     */
    private Stat createPatnWithParent(String path) {
        // valid
        if (path == null || path.trim().length() == 0) {
            return null;
        }

        try {
            Stat stat = getClient().exists(path, true);
            if (stat == null) {
                //  valid parent, createWithParent if not exists
                if (path.lastIndexOf("/") > 0) {
                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Stat parentStat = getClient().exists(parentPath, true);
                    if (parentStat == null) {
                        createPatnWithParent(parentPath);
                    }
                }
                // create desc node path
                getClient().create(path, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            return getClient().exists(path, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException(e.getMessage());
        }
    }

    /**
     * delete path (watch)
     *
     * @param path
     */
    public void deletePath(String path) {
        try {
            Stat stat = getClient().exists(path, true);
            if (stat != null) {
                getClient().delete(path, stat.getVersion());
            } else {
                logger.info(">>>>>>>>>> zookeeper node path not found :{}", path);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException(e.getMessage());
        }
    }

    /**
     * set data to node (watch)
     *
     * @param path
     * @param data
     * @return
     */
    public Stat setPathData(String path, String data) {
        try {
            Stat stat = getClient().exists(path, true);
            if (stat == null) {
                createPatnWithParent(path);
                stat = getClient().exists(path, true);
            }
            return getClient().setData(path, data.getBytes(), stat.getVersion());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException(e.getMessage());
        }
    }

    /**
     * get data from node (watch)
     *
     * @param path
     * @return
     */
    public String getPathData(String path) {
        try {
            String znodeValue = null;
            Stat stat = getClient().exists(path, true);
            if (stat != null) {
                byte[] resultData = getClient().getData(path, true, null);
                if (resultData != null) {
                    znodeValue = new String(resultData);
                }
            } else {
                logger.info(">>>>>>>>>> xxl-conf, path[{}] not found.", path);
            }
            return znodeValue;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException(e.getMessage());
        }
    }

    /**
     * get all child path data
     *
     * @return
     */
    private Map<String, String> getAllChildPathData(String parentPath) {
        Map<String, String> allData = new HashMap<String, String>();
        try {
            List<String> childKeys = getClient().getChildren(parentPath, true);
            if (childKeys != null && childKeys.size() > 0) {
                for (String key : childKeys) {
                    String data = getPathData(key);
                    allData.put(key, data);
                }
            }
            return allData;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw LaputaConfigurerException.ExceptionEnum.NORMAL_CONFIGURER_FAIL
                    .generateException(e.getMessage());
        }
    }

}
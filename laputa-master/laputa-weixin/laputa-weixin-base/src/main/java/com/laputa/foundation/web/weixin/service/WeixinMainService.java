package com.laputa.foundation.web.weixin.service;

import com.laputa.foundation.common.CollectionDiffUtil;
import com.laputa.foundation.web.weixin.controller.WeixinApiController;
import com.laputa.foundation.web.weixin.dao.WeixinMainJpaRepository;
import com.laputa.foundation.web.weixin.entity.WeixinBaseConfig;
import com.laputa.foundation.web.weixin.entity.WeixinUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import weixin.popular.support.TokenManager;
import weixin.popular.util.SignatureUtil;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 微信主服务
 * Created by jiangdongping on 2017/9/11 0011.
 */
@Service("weixinMainService")
public class WeixinMainService implements InitializingBean {

    private final Logger log = LoggerFactory.getLogger(WeixinApiController.class);

    @Inject
    private WeixinMainJpaRepository weixinMainJpaRepository;


    private List<WeixinBaseConfig> baseConfigList = null;
    private HashMap<String, WeixinBaseConfig> baseConfigHashMap = new HashMap<>();


    private final CollectionDiffUtil.Compare<WeixinBaseConfig> appidCompare = new CollectionDiffUtil.Compare<WeixinBaseConfig>() {
        @Override
        public Boolean equal(WeixinBaseConfig left, WeixinBaseConfig right) {
            try {
                return left.getAppid().equals(right.getAppid());
            } catch (Exception e) {
                return false;
            }
        }
    };

    @Override
    public void afterPropertiesSet() throws Exception {
        refresh();
    }


    /**
     * 签名获取微信
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public WeixinBaseConfig signatureCheck(String signature, String timestamp, String nonce) {
        if (baseConfigList == null || baseConfigList.size() == 0) {
            log.warn("未配置任何微信");
            return null;
        }
        for (WeixinBaseConfig weixinBaseConfig : baseConfigList) {
            if (signature.equals(SignatureUtil.generateEventMessageSignature(weixinBaseConfig.getToken(), timestamp, nonce))) {
                return weixinBaseConfig;
            }
        }
        log.warn("找不到匹配微信");
        return null;
    }


    /**
     * 编码获取微信
     *
     * @param weixincode
     * @return
     */
    public WeixinBaseConfig getByCode(String weixincode) {
        WeixinBaseConfig weixinBaseConfig = baseConfigHashMap.get(weixincode);
        return weixinBaseConfig;
    }

    /**
     * 查询微信用户
     *
     * @param unionid
     * @param weixincode
     * @return
     */
    public WeixinUser queryWeixinUser(String weixincode, String unionid) {
        WeixinUser weixinUser = weixinMainJpaRepository.findByWeixinCodeAndUnionId(weixincode, unionid);
        return weixinUser;
    }

    /**
     * 刷新微信服务模块
     */
    public synchronized void refresh() {
        List<WeixinBaseConfig> nowConfigList = weixinMainJpaRepository.findAll();
        if (baseConfigList == null || baseConfigList.size() == 0) {
            log.warn("未配置任何微信信息");
        }
        Collection<WeixinBaseConfig> needdDstroyed =
                CollectionDiffUtil.inLeftButNotInRiht(baseConfigList, nowConfigList, appidCompare);
        if (needdDstroyed != null && needdDstroyed.size() > 0) {
            for (WeixinBaseConfig weixinBaseConfig : needdDstroyed) {
                log.info("清理微信 {}", weixinBaseConfig.getAppid());
                TokenManager.destroyed(weixinBaseConfig.getAppid());
            }
        }

        Collection<WeixinBaseConfig> needInit =
                CollectionDiffUtil.inLeftButNotInRiht(nowConfigList, baseConfigList, appidCompare);

        if (needInit != null && needInit.size() > 0) {
            for (WeixinBaseConfig weixinBaseConfig : needInit) {
                try {
                    log.info("初始化微信 {}", weixinBaseConfig.getAppid());
                    TokenManager.init(weixinBaseConfig.getAppid(), weixinBaseConfig.getAppsecret());
                } catch (Exception e) {
                    log.error("初始化微信 {} 异常 {}\r\n{}", weixinBaseConfig.getAppid(), e.getMessage(), e);
                }
            }
        }
        baseConfigList = nowConfigList;
        initMap();
    }

    private synchronized void initMap() {
        baseConfigHashMap.clear();
        if (baseConfigList != null && baseConfigList.size() > 0) {
            for (WeixinBaseConfig weixinBaseConfig : baseConfigList) {
                baseConfigHashMap.put(weixinBaseConfig.getCode(), weixinBaseConfig);
            }
        }
    }
}

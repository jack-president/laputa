package com.laputa.foundation.web.security.service;

import com.laputa.foundation.web.rbac.model.RbacDTO;
import com.laputa.foundation.web.rbac.model.SysElementModel;
import com.laputa.foundation.web.rbac.model.SysFileModel;
import com.laputa.foundation.web.rbac.model.SysOperationModel;
import com.laputa.foundation.web.rbac.service.LaputRbacService;
import com.laputa.foundation.web.security.access.SysFileConfigAttribute;
import com.laputa.foundation.web.security.access.SysOperationConfigAttribute;
import com.laputa.foundation.web.security.api.LaputaConfigAttribute;
import com.laputa.foundation.web.security.api.SecurityMetadataConfig;
import com.laputa.foundation.web.security.exception.LaputaWebOperationSecurityException;
import com.laputa.foundation.web.security.voter.LaputaAuthenticatedVoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by JiangDongPing on 2016/12/20.
 */
@Service("laputaSecurityMetadataSourceService")
public class LaputaSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource, InitializingBean {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    private LinkedHashMap<RequestMatcher, Collection<? extends ConfigAttribute>> requestMap = null;

    @Autowired
    private LaputRbacService laputRbacService;

    @Autowired
    private List<SecurityMetadataConfig> metadataConfigList;


    private void checkSecurityMetaDataInit() {
        if (requestMap == null || requestMap.entrySet().size() == 0) {
            securityMetaDataInit(false);
        }
        if (requestMap == null || requestMap.entrySet().size() == 0) {
            throw LaputaWebOperationSecurityException.ExceptionEnum.RBAC_METADATA_INIT_FAIL.generateException();
        }
    }

    private synchronized void securityMetaDataInit(boolean force) {

        try {
            log.debug("尝试初始化 LaputaSecurityMetadataSourceService");

            RbacDTO rbacDTO = laputRbacService.getAllRbacMetaData();
            requestMap = new LinkedHashMap<>();
            initAnonymously();
            initSysFile(rbacDTO.getSysFileList());
            initSysElement(rbacDTO.getSysElementList());
            initSysOperation(rbacDTO.getSysOperationList());

            if (metadataConfigList != null && metadataConfigList.size() > 0) {
                for (SecurityMetadataConfig securityMetadataConfig : metadataConfigList) {
                    log.info("加载 {} 权限配置", securityMetadataConfig.name());

                    List<String> antPathList = securityMetadataConfig.configAntPathAnyoneAccess();
                    if (antPathList != null && antPathList.size() > 0) {
                        for (String antPath : antPathList) {
                            log.info("解除访问限制 {}", antPath);
                            requestMap.put(new AntPathRequestMatcher(antPath),
                                    SecurityConfig.createList(LaputaAuthenticatedVoter.IS_ANYONE));
                        }
                    } else {
                        log.info("{} 权限解除问限制配置为空", securityMetadataConfig.name());
                    }

                    List<? extends LaputaConfigAttribute> laputaConfigAttributeList
                            = securityMetadataConfig.configAntConfigAttribute();
                    if (laputaConfigAttributeList != null && laputaConfigAttributeList.size() > 0) {
                        for (LaputaConfigAttribute attribute : laputaConfigAttributeList) {
                            log.info("添加访问限制 {} {} {}", attribute.getClass().getName(), attribute.antPrefix(), attribute.getAttribute());
                            AntPathRequestMatcher matcher = new AntPathRequestMatcher(attribute.antPrefix());
                            requestMap.put(matcher, Arrays.asList(attribute));
                        }
                    } else {
                        log.info("{} 权限访问限制配置为空", securityMetadataConfig.name());
                    }
                }
            }
        } catch (Exception e) {
            log.error("初始化权限META信息失败 {}", e);
            requestMap = null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        securityMetaDataInit(true);
    }

    private void initAnonymously() {
        log.info("加载默认适配器");
        requestMap.put(new AntPathRequestMatcher("/**"),
                SecurityConfig.createList(LaputaAuthenticatedVoter.IS_AUTHENTICATED_FULLY));
//        requestMap.put(new AntPathRequestMatcher("/static/kendo"),
//                SecurityConfig.createList(LaputaAuthenticatedVoter.IS_AUTHENTICATED_FULLY));
//        requestMap.put(new AntPathRequestMatcher("/favicon.ico"),
//                SecurityConfig.createList(LaputaAuthenticatedVoter.IS_ANYONE));
//        requestMap.put(new AntPathRequestMatcher("/loginform"),
//                SecurityConfig.createList(LaputaAuthenticatedVoter.IS_ANYONE));
//        requestMap.put(new AntPathRequestMatcher("/captcha"),
//                SecurityConfig.createList(LaputaAuthenticatedVoter.IS_ANYONE));
//        requestMap.put(new AntPathRequestMatcher("/security/show/**"),
//                SecurityConfig.createList(LaputaAuthenticatedVoter.IS_ANYONE));
//        requestMap.put(new AntPathRequestMatcher("/static/**"),
//                SecurityConfig.createList(LaputaAuthenticatedVoter.IS_ANYONE));
    }

    private void initSysFile(List<SysFileModel> sysFileList) {
        if (sysFileList != null && sysFileList.size() > 0) {
            log.debug("导入 SysFile {}", sysFileList.size());
            for (SysFileModel sysFile : sysFileList) {
                AntPathRequestMatcher sysFileRequestMatcher = new AntPathRequestMatcher(sysFile.getPath());
                List<SysFileConfigAttribute> sysFileConfigAttributeList = Arrays.asList(new SysFileConfigAttribute(sysFile));
                requestMap.put(sysFileRequestMatcher, sysFileConfigAttributeList);
            }
        } else {
            log.debug("无需导入 SysFile");
        }
    }

    private void initSysElement(List<SysElementModel> sysElementList) {

        if (sysElementList != null && sysElementList.size() > 0) {
            log.debug("导入 SysElement {}", sysElementList.size());
            //TODO
        } else {
            log.debug("无需导入 SysElement");
        }
    }

    private void initSysOperation(List<SysOperationModel> sysOperationList) {
        if (sysOperationList != null && sysOperationList.size() > 0) {
            log.debug("导入 SysOperation {}", sysOperationList.size());
            for (SysOperationModel sysOperation : sysOperationList) {
                AntPathRequestMatcher sysOperationRequestMatcher = new AntPathRequestMatcher(sysOperation.getPrefixUrl());
                List<SysOperationConfigAttribute> sysOperationConfigAttributeList = Arrays.asList(new SysOperationConfigAttribute(sysOperation));
                requestMap.put(sysOperationRequestMatcher, sysOperationConfigAttributeList);
            }
        } else {
            log.debug("无需导入 SysOperation");
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        checkSecurityMetaDataInit();

        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<? extends ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {

        checkSecurityMetaDataInit();

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        Collection<ConfigAttribute> attributeCollection = new ArrayList<>();
        for (Map.Entry<RequestMatcher, Collection<? extends ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                attributeCollection.addAll(entry.getValue());
            }
        }
        return attributeCollection;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

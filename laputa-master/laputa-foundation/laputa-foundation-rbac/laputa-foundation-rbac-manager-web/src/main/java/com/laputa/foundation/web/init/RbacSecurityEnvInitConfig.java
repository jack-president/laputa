package com.laputa.foundation.web.init;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.laputa.foundation.persistence.code.ParentAbleIdEntity;
import com.laputa.foundation.web.init.model.*;
import com.laputa.foundation.web.rbac.RbacConfiguration;
import com.laputa.foundation.web.rbac.dao.*;
import com.laputa.foundation.web.rbac.entity.*;
import com.laputa.foundation.web.rbac.security.LaputaBCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import javax.inject.Inject;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by JiangDongPing on 2017/1/9.
 */
@Configuration
@Import({RbacConfiguration.class})
@ImportResource({
        "classpath*:/laputa-config/springcontext/applicationcontext-foundation-persistence.xml"
})
@Transactional
public class RbacSecurityEnvInitConfig {

    private final Logger log = LoggerFactory.getLogger(RbacSecurityEnvInitConfig.class);

    @Inject
    SysElementJpaRepository sysElementJpaRepository;

    @Inject
    SysOperationJpaRepository sysOperationJpaRepository;

    @Inject
    SysMenuJpaRepository sysMenuJpaRepository;

    @Inject
    SysOperationBelongtoRelationSysPermissionJpaRepository sysOperationBelongtoRelationSysPermissionJpaRepository;

    @Inject
    SysMenuBelongtoRelationSysPermissionJpaRepository sysMenuBelongtoRelationSysPermissionJpaRepository;

    @Inject
    SysUserJpaRepository sysUserJpaRepository;

    @Inject
    SysRoleJpaRepository sysRoleJpaRepository;

    @Inject
    SysUserBelongtoRelationSysRoleJpaRepository sysUserBelongtoRelationSysRoleJpaRepository;

    @Inject
    SysPermissionJpaRepository sysPermissionJpaRepository;

    @Inject
    SysPermissionBelongtoRelationSysRoleJpaRepository sysPermissionBelongtoRelationSysRoleJpaRepository;

    @Inject
    LaputaBCryptPasswordEncoder passwordEncoder;


    @Bean
    public LaputaBCryptPasswordEncoder passwordEncoder() {
        LaputaBCryptPasswordEncoder bCryptPasswordEncoder = new LaputaBCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Transactional
    public void clear() {
        log.info("开始初清理 Rbac");
        parentAbleDataClear(sysOperationJpaRepository);
        parentAbleDataClear(sysMenuJpaRepository);
        sysPermissionJpaRepository.deleteAll();
        sysRoleJpaRepository.deleteAll();
        sysPermissionJpaRepository.flush();
        sysUserJpaRepository.deleteAllInBatch();
    }

    @Transactional
    public void initEnv() {
        log.info("配置操作");
        List<SysOperationModel> sysOperationList = takeSysOperationList();
        for (SysOperationModel sysOperationModel : sysOperationList) {
            sysOperationJpaRepository.save(sysOperationModel.getSysOperation());
            if (sysOperationModel.getChildSysOperation() != null && sysOperationModel.getChildSysOperation().size() > 0) {
                for (SysOperation childSysOperation : sysOperationModel.getChildSysOperation()) {
                    childSysOperation.setParent(sysOperationModel.getSysOperation());
                    sysOperationJpaRepository.save(childSysOperation);
                }
            }
        }
        log.info("配置菜单");
        List<SysMenuModel> sysMenuModelList = takeSysMenuModelList();
        saveSysMenuModel(null, sysMenuModelList);

        log.info("配置元素");
    }

    private void saveSysMenuModel(SysMenu parentSysMenu, List<SysMenuModel> sysMenuModelList) {
        if (sysMenuModelList != null && sysMenuModelList.size() > 0) {
            for (SysMenuModel sysMenuModel : sysMenuModelList) {
                if (parentSysMenu != null) {
                    sysMenuModel.getSysMenu().setParent(parentSysMenu);
                }
                sysMenuJpaRepository.save(sysMenuModel.getSysMenu());
                saveSysMenuModel(sysMenuModel.getSysMenu(), sysMenuModel.getChildSysMenuModelList());
            }
        }
    }

    @Transactional
    public void initPermission() {
        log.info("初始化权限");
        List<SysPermissionModel> sysPermissionModelList = takeSysPermissionList();
        for (SysPermissionModel sysPermissionModel : sysPermissionModelList) {
            sysPermissionJpaRepository.save(sysPermissionModel.getSysPermission());
            if (sysPermissionModel.getSysOperationList() != null && sysPermissionModel.getSysOperationList().size() > 0) {
                log.info("初始化权限所属操作");
                for (String sysOperationCode : sysPermissionModel.getSysOperationList()) {
                    SysOperation sysOperation = sysOperationJpaRepository.selectByCode(sysOperationCode);
                    if (sysOperation == null) {
                        throw new RuntimeException("未找到操作代码 : " + sysOperationCode);
                    }
                    relationSysPermissionAndSysOperation(sysPermissionModel.getSysPermission(), sysOperation);
                }
            }
            if (sysPermissionModel.getSysMenuList() != null && sysPermissionModel.getSysMenuList().size() > 0) {
                log.info("初始化权限所属菜单");
                for (String menuCode : sysPermissionModel.getSysMenuList()) {
                    SysMenu sysMenu = sysMenuJpaRepository.selectByCode(menuCode);
                    if (sysMenu == null) {
                        throw new RuntimeException("未找到菜单代码 : " + menuCode);
                    }
                    relationSysPermissionAndSysMenu(sysPermissionModel.getSysPermission(), sysMenu);
                }
            }
        }
    }

    @Transactional
    public void initRole() {
        List<SysRoleModel> sysRoleModelList = takeSysRoleModelList();
        log.info("初始化角色 {}", sysRoleModelList.size());
        for (SysRoleModel sysRoleModel : sysRoleModelList) {
            sysRoleJpaRepository.save(sysRoleModel.getSysRole());
            log.info("关联角色权限 {}", sysRoleModel.getPermissionList().size());
            for (String permissionCode : sysRoleModel.getPermissionList()) {
                SysPermission sysPermission = sysPermissionJpaRepository.selectByCode(permissionCode);
                SysPermissionBelongtoRelationSysRole sysPermissionBelongtoRelationSysRole = new SysPermissionBelongtoRelationSysRole();
                sysPermissionBelongtoRelationSysRole.setSysPermission(sysPermission);
                sysPermissionBelongtoRelationSysRole.setSysRole(sysRoleModel.getSysRole());
                sysPermissionBelongtoRelationSysRoleJpaRepository.save(sysPermissionBelongtoRelationSysRole);
            }
        }
    }

    @Transactional
    public void initUser() {
        List<SysUserModel> sysUserModelList = takeSysUserList();
        log.info("配置 {} 用户", sysUserModelList.size());
        for (SysUserModel sysUserModel : sysUserModelList) {
            sysUserModel.getSysUser().setPassword(passwordEncoder.encode(sysUserModel.getSysUser().getPassword().toLowerCase()));
            sysUserJpaRepository.save(sysUserModel.getSysUser());

            log.info("配置 {} {} 角色", sysUserModel.getSysUser().getUsername(), sysUserModel.getSysRoleList().size());
            for (String roleCode : sysUserModel.getSysRoleList()) {
                SysRole sysRole = sysRoleJpaRepository.selectByCode(roleCode);

                SysUserBelongtoRelationSysRole sysUserBelongtoRelationSysRole = new SysUserBelongtoRelationSysRole();
                sysUserBelongtoRelationSysRole.setSysRole(sysRole);
                sysUserBelongtoRelationSysRole.setSysUser(sysUserModel.getSysUser());
                sysUserBelongtoRelationSysRoleJpaRepository.save(sysUserBelongtoRelationSysRole);
            }
        }
    }

    private void relationSysPermissionAndSysMenu(SysPermission sysPermission, SysMenu sysMenu) {
        SysMenuBelongtoRelationSysPermission sysMenuBelongtoRelationSysPermission = new SysMenuBelongtoRelationSysPermission();
        sysMenuBelongtoRelationSysPermission.setSysPermission(sysPermission);
        sysMenuBelongtoRelationSysPermission.setSysMenu(sysMenu);
        sysMenuBelongtoRelationSysPermissionJpaRepository.save(sysMenuBelongtoRelationSysPermission);
        if (sysMenu.getChildren() != null && sysMenu.getChildren().size() > 0) {
            for (SysMenu child : sysMenu.getChildren()) {
                relationSysPermissionAndSysMenu(sysPermission, child);
            }
        }
    }

    private void relationSysPermissionAndSysOperation(SysPermission sysPermission, SysOperation sysOperation) {
        SysOperationBelongtoRelationSysPermission sysOperationBelongtoRelationSysPermission = new SysOperationBelongtoRelationSysPermission();
        sysOperationBelongtoRelationSysPermission.setSysPermission(sysPermission);
        sysOperationBelongtoRelationSysPermission.setSysOperation(sysOperation);
        sysOperationBelongtoRelationSysPermissionJpaRepository.save(sysOperationBelongtoRelationSysPermission);
        if (sysOperation.getChildren() != null && sysOperation.getChildren().size() > 0) {
            for (SysOperation child : sysOperation.getChildren()) {
                relationSysPermissionAndSysOperation(sysPermission, child);
            }
        }
    }

    private List<SysRoleModel> takeSysRoleModelList() {
        List<SysRoleModel> sysPermissionModelList =
                (List<SysRoleModel>) toListBean("classpath*:/laputa-rbac-config/*/sysrole/SysRole.*.json", new TypeToken<List<SysRoleModel>>() {
                }.getType());
        if (sysPermissionModelList == null || sysPermissionModelList.size() == 0) {
            throw new RuntimeException();
        }
        return sysPermissionModelList;
    }

    private List<SysPermissionModel> takeSysPermissionList() {
        List<SysPermissionModel> sysPermissionModelList = (List<SysPermissionModel>) toListBean(
                "classpath*:/laputa-rbac-config/*/syspermission/SysPermission.*.json",
                new TypeToken<List<SysPermissionModel>>() {
                }.getType()
                                                                                               );
        if (sysPermissionModelList == null || sysPermissionModelList.size() == 0) {
            throw new RuntimeException();
        }
        return sysPermissionModelList;
    }

    private List<SysUserModel> takeSysUserList() {
        List<SysUserModel> sysUserModelList =
                (List<SysUserModel>) toListBean("classpath*:/laputa-rbac-config/rbac/sysuser/SysUser.*.json", new TypeToken<List<SysUserModel>>() {
                }.getType());
        if (sysUserModelList == null || sysUserModelList.size() == 0) {
            throw new RuntimeException();
        }
        return sysUserModelList;
    }

    private List<SysMenuModel> takeSysMenuModelList() {
        List<SysMenuModel> sysMenuModelList =
                (List<SysMenuModel>) toListBean("classpath*:/laputa-rbac-config/*/sysmenu/SysMenu.*.json", new TypeToken<List<SysMenuModel>>() {
                }.getType());
        if (sysMenuModelList == null || sysMenuModelList.size() == 0) {
            throw new RuntimeException();
        }
        return sysMenuModelList;
    }

    private List<SysOperationModel> takeSysOperationList() {
        List<SysOperationModel> sysOperationList = (List<SysOperationModel>) toListBean("classpath*:/laputa-rbac-config/*/sysoperation/SysOperation.*.json",
                                                                                        new TypeToken<List<SysOperationModel>>() {
                                                                                        }.getType()
                                                                                       );
        if (sysOperationList == null || sysOperationList.size() == 0) {
            throw new RuntimeException();
        }
        return sysOperationList;
    }

    private <T extends ParentAbleIdEntity<T>> void parentAbleDataClear(JpaRepository<T, Long> jpaRepository) {
        List<T> list = jpaRepository.findAll();
        if (list != null && list.size() > 0) {
            for (T data : list) {
                if (data.getParentId() == null) {
                    parentAbleDataClear(jpaRepository, data);
                }
            }
        }
        jpaRepository.flush();
    }

    private <T extends ParentAbleIdEntity<T>> void parentAbleDataClear(JpaRepository<T, Long> jpaRepository, T data) {
        jpaRepository.delete(data);
    }

    private void sysOperationClear() {
        List<SysOperation> sysOperationList = sysOperationJpaRepository.findAll();
        if (sysOperationList != null && sysOperationList.size() > 0) {
            for (SysOperation sysOperation : sysOperationList) {
                if (sysOperation.getParentId() == null) {
                    removeSysOperation(Arrays.asList(sysOperation));
                }
            }
        }
        sysOperationJpaRepository.flush();
    }

    private void removeSysOperation(Collection<SysOperation> sysOperationList) {
        if (sysOperationList != null || sysOperationList.size() > 0) {
            for (SysOperation sysOperation : sysOperationList) {
                removeSysOperation(sysOperation.getChildren());
                sysOperationJpaRepository.delete(sysOperation);
            }
        }
    }

    public List toListBean(String classPath, Type type) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().create();
        try {
            PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = pathMatchingResourcePatternResolver.getResources(classPath);
            List bean = null;
            for (Resource resource : resources) {
                String json = FileCopyUtils.copyToString(new FileReader(resource.getFile()));
                List beanList = gson.fromJson(json, type);
                if (bean == null) {
                    bean = beanList;
                } else {
                    bean.addAll(beanList);
                }
            }
            return bean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

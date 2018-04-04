package com.laputa.foundation.web.security.service;

import com.laputa.foundation.web.rbac.model.*;
import com.laputa.foundation.web.rbac.service.LaputRbacService;
import com.laputa.foundation.web.security.core.LaputaUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by JiangDongPing on 2016/12/20.
 */
@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private LaputRbacService securityJpaRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("鉴权 {}", username);
        SysUserModel user = securityJpaRepository.selectSysUserByUserName(username);
        if (user == null) {
            throw new UserNotExisException(username);
        }

        if (user.getLocked()) {
            throw new UserNotActivatedException("User " + username + " was not activated");
        }


        List<SysUserGroupModel> sysUserGroupList = securityJpaRepository.selectSysUserGroupBySysUserId(user.getId());
        if (sysUserGroupList != null && sysUserGroupList.size() > 0) {
            log.debug("{} 属于{} 个用户组", username, sysUserGroupList.size());
        }

        List<SysRoleModel> sysRoleList = securityJpaRepository.selectSysRoleBySysUserGroup(user.getId());
        if (sysRoleList != null && sysRoleList.size() > 0) {
            log.debug("{} 拥有 {} 个角色", username, sysRoleList.size());
        }

        List<SysPermissionModel> sysPermissionList = null;
        if (sysRoleList != null && sysRoleList.size() > 0) {
            sysPermissionList = securityJpaRepository.selectSysPermissionBySysRole(sysRoleList);
        } else {
            log.warn("{} 不拥有任何角色", username);
        }

        List<SysMenuModel> sysMenuList = null;
        List<SysElementModel> sysElementList = null;
        List<SysFileModel> sysFileList = null;
        List<SysOperationModel> sysOperationList = null;
        if (sysPermissionList != null && sysPermissionList.size() > 0) {
            log.debug("{} 拥有{} 个权限", username, sysPermissionList.size());
            sysMenuList = securityJpaRepository.selectSysMenuBySysPermission(sysPermissionList);
            sysElementList = securityJpaRepository.selectSysElementBySysPermission(sysPermissionList);
            sysFileList = securityJpaRepository.selectSysFileBySysPermission(sysPermissionList);
            sysOperationList = securityJpaRepository.selectSysOperationBySysPermission(sysPermissionList);
        }

        List<SysRoleModel> invertedSysRoleList = securityJpaRepository.selectInvertedSysRoleBySysUserGroup(user.getId());
        if (invertedSysRoleList != null && invertedSysRoleList.size() > 0) {
            log.debug("{} 拥有 {} 个反转角色", username, sysRoleList.size());
        }

        List<SysMenuModel> invertedSysMenuList = null;
        List<SysElementModel> invertedSysElementList = null;
        List<SysFileModel> invertedSysFileList = null;
        List<SysOperationModel> invertedSysOperationList = null;

        List<SysPermissionModel> invertedSysPermissionList = null;
        if (invertedSysRoleList != null && invertedSysRoleList.size() > 0) {
            invertedSysPermissionList = securityJpaRepository.selectSysPermissionBySysRole(invertedSysRoleList);
        } else {
            log.warn("{} 不拥有任何角色", username);
        }
        if (invertedSysPermissionList != null && invertedSysPermissionList.size() > 0) {
            log.debug("{} 拥有{} 个反转权限", username, invertedSysPermissionList.size());
            invertedSysMenuList = securityJpaRepository.selectSysMenuBySysPermission(invertedSysPermissionList);
            invertedSysElementList = securityJpaRepository.selectSysElementBySysPermission(invertedSysPermissionList);
            invertedSysFileList = securityJpaRepository.selectSysFileBySysPermission(invertedSysPermissionList);
            invertedSysOperationList = securityJpaRepository.selectSysOperationBySysPermission(invertedSysPermissionList);
        }


        return LaputaUserDetails.build(user,
                sysUserGroupList,

                sysRoleList,
                sysPermissionList,
                sysMenuList,
                sysElementList,
                sysFileList,
                sysOperationList,

                invertedSysRoleList,
                sysPermissionList,
                invertedSysMenuList,
                invertedSysElementList,
                invertedSysFileList,
                invertedSysOperationList
        );

    }
}

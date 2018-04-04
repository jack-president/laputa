package com.laputa.foundation.web.security.core;

import com.laputa.foundation.web.rbac.model.*;
import com.laputa.foundation.web.security.access.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangdongping on 2017/8/29 0029.
 */
public class LaputaUserDetails extends org.springframework.security.core.userdetails.User {

    private final static Logger log = LoggerFactory.getLogger(LaputaUserDetails.class);

    private SysUserModel sysUser;
    private List<SysUserGroupModel> sysUserGroupList;

    private List<SysRoleModel> sysRoleList;
    private List<SysPermissionModel> sysPermissionList;
    private List<SysMenuModel> sysMenuList;
    private List<SysElementModel> sysElementList;
    private List<SysFileModel> sysFileList;
    private List<SysOperationModel> sysOperationList;


    private List<SysRoleModel> invertedSysRoleList;
    private List<SysPermissionModel> invertedSysPermissionList;
    private List<SysMenuModel> invertedSysMenuList;
    private List<SysElementModel> invertedSysElementList;
    private List<SysFileModel> invertedSysFileList;
    private List<SysOperationModel> invertedSysOperationList;

    private List<? extends GrantedAuthority> invertedAuthorities;

    public static LaputaUserDetails build(SysUserModel sysUser,
                                          List<SysUserGroupModel> sysUserGroupList,

                                          List<SysRoleModel> sysRoleList,
                                          List<SysPermissionModel> sysPermissionList,
                                          List<SysMenuModel> sysMenuList,
                                          List<SysElementModel> sysElementList,
                                          List<SysFileModel> sysFileList,
                                          List<SysOperationModel> sysOperationList,

                                          List<SysRoleModel> invertedSysRoleList,
                                          List<SysPermissionModel> invertedSysPermissionList,
                                          List<SysMenuModel> invertedSysMenuList,
                                          List<SysElementModel> invertedSysElementList,
                                          List<SysFileModel> invertedSysFileList,
                                          List<SysOperationModel> invertedSysOperationList
    ) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (sysUserGroupList != null && sysUserGroupList.size() > 0) {
            for (SysUserGroupModel sysUserGroup : sysUserGroupList) {
                authorities.add(new SysUserGroupGrantedAuthority(sysUserGroup));
            }
        }
        if (sysMenuList != null && sysMenuList.size() > 0) {
            for (SysMenuModel sysMenu : sysMenuList) {
                authorities.add(new SysMenuGrantedAuthority(sysMenu));
            }
        }
        if (sysElementList != null && sysElementList.size() > 0) {
            for (SysElementModel sysElement : sysElementList) {
                authorities.add(new SysElementGrantedAuthority(sysElement));
            }
        }
        if (sysFileList != null && sysFileList.size() > 0) {
            for (SysFileModel sysFile : sysFileList) {
                authorities.add(new SysFileGrantedAuthority(sysFile));
            }
        }
        if (sysOperationList != null && sysOperationList.size() > 0) {
            for (SysOperationModel sysOperation : sysOperationList) {
                authorities.add(new SysOperationGrantedAuthority(sysOperation));
            }
        }

        List<GrantedAuthority> invertedAuthorities = new ArrayList<>();

        if (invertedSysMenuList != null && invertedSysMenuList.size() > 0) {
            for (SysMenuModel sysMenu : invertedSysMenuList) {
                invertedAuthorities.add(new SysMenuGrantedAuthority(sysMenu));
            }
        }
        if (invertedSysElementList != null && invertedSysElementList.size() > 0) {
            for (SysElementModel sysElement : invertedSysElementList) {
                invertedAuthorities.add(new SysElementGrantedAuthority(sysElement));
            }
        }
        if (invertedSysFileList != null && invertedSysFileList.size() > 0) {
            for (SysFileModel sysFile : invertedSysFileList) {
                invertedAuthorities.add(new SysFileGrantedAuthority(sysFile));
            }
        }
        if (invertedSysOperationList != null && invertedSysOperationList.size() > 0) {
            for (SysOperationModel sysOperation : invertedSysOperationList) {
                invertedAuthorities.add(new SysOperationGrantedAuthority(sysOperation));
            }
        }

        if (log.isDebugEnabled()) {
            if (authorities != null && authorities.size() > 0) {
                for (GrantedAuthority grantedAuthority : authorities) {
                    log.debug("{} own authoritie {} : {}", sysUser.getUsername(), grantedAuthority.getClass().getSimpleName(), grantedAuthority.toString());
                }
            }
        }

        if (log.isDebugEnabled()) {
            if (invertedAuthorities != null && invertedAuthorities.size() > 0) {
                for (GrantedAuthority invertedAuthority : invertedAuthorities) {
                    log.debug("{} inverted own authoritie {} : {}", sysUser.getUsername(), invertedAuthority.getClass().getSimpleName(), invertedAuthority.toString());
                }
            }
        }

        LaputaUserDetails laputaUserDetails = new LaputaUserDetails(sysUser,
                sysUserGroupList,

                sysRoleList,
                sysPermissionList,
                sysMenuList,
                sysElementList,
                sysFileList,
                sysOperationList,

                invertedSysRoleList,
                invertedSysPermissionList,
                invertedSysMenuList,
                invertedSysElementList,
                invertedSysFileList,
                invertedSysOperationList,

                invertedAuthorities,

                authorities);

        return laputaUserDetails;

    }

    private LaputaUserDetails(SysUserModel sysUser,
                              List<SysUserGroupModel> sysUserGroupList,

                              List<SysRoleModel> sysRoleList,
                              List<SysPermissionModel> sysPermissionList,
                              List<SysMenuModel> sysMenuList,
                              List<SysElementModel> sysElementList,
                              List<SysFileModel> sysFileList,
                              List<SysOperationModel> sysOperationList,

                              List<SysRoleModel> invertedSysRoleList,
                              List<SysPermissionModel> invertedSysPermissionList,
                              List<SysMenuModel> invertedSysMenuList,
                              List<SysElementModel> invertedSysElementList,
                              List<SysFileModel> invertedSysFileList,
                              List<SysOperationModel> invertedSysOperationList,
                              List<? extends GrantedAuthority> invertedAuthorities,

                              List<? extends GrantedAuthority> authorities) {

        super(sysUser.getUsername(), sysUser.getPassword(), !sysUser.getLocked(),
                true, true, !sysUser.getLocked(), authorities);

        this.sysUser = sysUser;
        this.sysUserGroupList = sysUserGroupList;

        this.sysRoleList = sysRoleList;
        this.sysPermissionList = sysPermissionList;
        this.sysMenuList = sysMenuList;
        this.sysElementList = sysElementList;
        this.sysFileList = sysFileList;
        this.sysOperationList = sysOperationList;

        this.invertedSysRoleList = invertedSysRoleList;
        this.invertedSysPermissionList = invertedSysPermissionList;
        this.invertedSysMenuList = invertedSysMenuList;
        this.invertedSysElementList = invertedSysElementList;
        this.invertedSysFileList = invertedSysFileList;
        this.invertedSysOperationList = invertedSysOperationList;
        this.invertedAuthorities = invertedAuthorities;
    }

    public List<SysOperationModel> getSysOperationList() {
        return sysOperationList;
    }

    public void setSysOperationList(List<SysOperationModel> sysOperationList) {
        this.sysOperationList = sysOperationList;
    }

    public SysUserModel getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUserModel sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysUserGroupModel> getSysUserGroupList() {
        return sysUserGroupList;
    }

    public void setSysUserGroupList(List<SysUserGroupModel> sysUserGroupList) {
        this.sysUserGroupList = sysUserGroupList;
    }

    public List<SysPermissionModel> getSysPermissionList() {
        return sysPermissionList;
    }

    public void setSysPermissionList(List<SysPermissionModel> sysPermissionList) {
        this.sysPermissionList = sysPermissionList;
    }

    public List<SysMenuModel> getSysMenuList() {
        return sysMenuList;
    }

    public void setSysMenuList(List<SysMenuModel> sysMenuList) {
        this.sysMenuList = sysMenuList;
    }

    public List<SysElementModel> getSysElementList() {
        return sysElementList;
    }

    public void setSysElementList(List<SysElementModel> sysElementList) {
        this.sysElementList = sysElementList;
    }

    public List<SysFileModel> getSysFileList() {
        return sysFileList;
    }

    public void setSysFileList(List<SysFileModel> sysFileList) {
        this.sysFileList = sysFileList;
    }

    public List<SysRoleModel> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRoleModel> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public List<SysRoleModel> getInvertedSysRoleList() {
        return invertedSysRoleList;
    }

    public void setInvertedSysRoleList(List<SysRoleModel> invertedSysRoleList) {
        this.invertedSysRoleList = invertedSysRoleList;
    }

    public List<SysPermissionModel> getInvertedSysPermissionList() {
        return invertedSysPermissionList;
    }

    public void setInvertedSysPermissionList(List<SysPermissionModel> invertedSysPermissionList) {
        this.invertedSysPermissionList = invertedSysPermissionList;
    }

    public List<SysMenuModel> getInvertedSysMenuList() {
        return invertedSysMenuList;
    }

    public void setInvertedSysMenuList(List<SysMenuModel> invertedSysMenuList) {
        this.invertedSysMenuList = invertedSysMenuList;
    }

    public List<SysElementModel> getInvertedSysElementList() {
        return invertedSysElementList;
    }

    public void setInvertedSysElementList(List<SysElementModel> invertedSysElementList) {
        this.invertedSysElementList = invertedSysElementList;
    }

    public List<SysFileModel> getInvertedSysFileList() {
        return invertedSysFileList;
    }

    public void setInvertedSysFileList(List<SysFileModel> invertedSysFileList) {
        this.invertedSysFileList = invertedSysFileList;
    }

    public List<SysOperationModel> getInvertedSysOperationList() {
        return invertedSysOperationList;
    }

    public void setInvertedSysOperationList(List<SysOperationModel> invertedSysOperationList) {
        this.invertedSysOperationList = invertedSysOperationList;
    }

    public List<? extends GrantedAuthority> getInvertedAuthorities() {
        return invertedAuthorities;
    }

    public void setInvertedAuthorities(List<? extends GrantedAuthority> invertedAuthorities) {
        this.invertedAuthorities = invertedAuthorities;
    }
}

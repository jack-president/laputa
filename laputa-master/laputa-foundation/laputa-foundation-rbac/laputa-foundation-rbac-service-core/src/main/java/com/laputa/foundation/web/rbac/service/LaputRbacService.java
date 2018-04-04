package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.web.rbac.model.*;

import java.util.List;

/**
 * 提供权限相关查询接口
 * Created by JiangDongPing on 2018/04/02.
 */
public interface LaputRbacService {

    /**
     * 获取所有权限元数据
     *
     * @return
     */
    RbacDTO getAllRbacMetaData();

    SysUserModel selectSysUserByUserName(String username);

    List<SysUserGroupModel> selectSysUserGroupBySysUserId(Long sysUserId);

    /**
     * 查询用户角色
     *
     * @param sysUserId
     * @return
     */
    List<SysRoleModel> selectSysRoleBySysUserGroup(Long sysUserId);

    /**
     * 查询用户 反转角色
     *
     * @param sysUserId
     * @return
     */
    List<SysRoleModel> selectInvertedSysRoleBySysUserGroup(Long sysUserId);

    List<SysRoleModel> selectSysRoleBySysUserIdExcludeAlreadyFind(Long sysUserId, List<SysRoleModel> alreadyFind);

    List<SysRoleModel> selectSysRoleBySysUserId(Long sysUserId);

    List<SysPermissionModel> selectSysPermissionBySysRole(List<SysRoleModel> sysRoleList);

    List<SysMenuModel> selectSysMenuBySysPermission(List<SysPermissionModel> sysPermissionList);

    List<SysElementModel> selectSysElementBySysPermission(List<SysPermissionModel> sysPermissionList);

    List<SysFileModel> selectSysFileBySysPermission(List<SysPermissionModel> sysPermissionList);

    List<SysOperationModel> selectSysOperationBySysPermission(List<SysPermissionModel> sysPermissionList);
}

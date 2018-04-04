package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.web.rbac.converter.*;
import com.laputa.foundation.web.rbac.entity.*;
import com.laputa.foundation.web.rbac.model.*;
import com.laputa.foundation.web.rbac.service.LaputRbacService;
import com.laputa.foundation.web.rbac.dao.RbacJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by JiangDongPing on 2018/04/02.
 */
@Transactional(readOnly = true)
public class LaputRbacServiceImpl implements LaputRbacService {

    @Autowired(required = false)
    private RbacJpaRepository rbacJpaRepository;

    @Override
    public RbacDTO getAllRbacMetaData() {
        RbacDTO rbacDTO = new RbacDTO();

        rbacDTO.setSysFileList(
                SysFileBeanCopier.sysFileEntityToSysFileModel(rbacJpaRepository.selectAllSysFile()));
        rbacDTO.setSysElementList(
                SysElementBeanCopier.sysElementEntityToSysElementModel(rbacJpaRepository.selectAllSysSysElement()));
        rbacDTO.setSysOperationList(
                SysOperationBeanCopier.sysOperationEntityToSysOperationModel(rbacJpaRepository.selectAllSysOperation()));

        return rbacDTO;
    }

    @Override
    public SysUserModel selectSysUserByUserName(String username) {
        SysUser sysUser = rbacJpaRepository.selectSysUserByUserName(username);
        SysUserModel sysUserModel = SysUserBeanCopier.sysUserEntityToSysUserModel(sysUser);
        return sysUserModel;
    }

    @Override
    public List<SysUserGroupModel> selectSysUserGroupBySysUserId(Long sysUserId) {

        List<SysUserGroup> sysUserGroupList = rbacJpaRepository.selectSysUserGroupBySysUserId(sysUserId);
        List<SysUserGroupModel> sysUserGroupModelList =
                SysUserGroupBeanCopier.sysUserGroupEntityToSysUserGroupModel(sysUserGroupList);
        return sysUserGroupModelList;
    }

    @Override
    public List<SysRoleModel> selectSysRoleBySysUserGroup(Long sysUserId) {
        List<SysRole> sysRoleList = rbacJpaRepository.selectSysRoleBySysUserGroup(sysUserId);
        List<SysRoleModel> sysRoleModelList =
                SysRoleBeanCopier.sysRoleEntityToSysRoleModel(sysRoleList);
        return sysRoleModelList;
    }

    @Override
    public List<SysRoleModel> selectInvertedSysRoleBySysUserGroup(Long sysUserId) {
        List<SysRole> sysRoleList = rbacJpaRepository.selectInvertedSysRoleBySysUserGroup(sysUserId);
        List<SysRoleModel> sysRoleModelList =
                SysRoleBeanCopier.sysRoleEntityToSysRoleModel(sysRoleList);
        return sysRoleModelList;
    }

    @Override
    public List<SysRoleModel> selectSysRoleBySysUserIdExcludeAlreadyFind(Long sysUserId, List<SysRoleModel> alreadyFind) {

        List<SysRole> alreadyFindEntiy = SysRoleBeanCopier.sysRoleModelToSysRoleEntity(alreadyFind);
        List<SysRole> sysRoleList = rbacJpaRepository.selectSysRoleBySysUserIdExcludeAlreadyFind(sysUserId, alreadyFindEntiy);

        List<SysRoleModel> sysRoleModelList =
                SysRoleBeanCopier.sysRoleEntityToSysRoleModel(sysRoleList);

        return sysRoleModelList;
    }

    @Override
    public List<SysRoleModel> selectSysRoleBySysUserId(Long sysUserId) {
        List<SysRole> sysRoleList = rbacJpaRepository.selectSysRoleBySysUserId(sysUserId);
        List<SysRoleModel> sysRoleModelList = SysRoleBeanCopier.sysRoleEntityToSysRoleModel(sysRoleList);
        return sysRoleModelList;
    }

    @Override
    public List<SysPermissionModel> selectSysPermissionBySysRole(List<SysRoleModel> sysRoleList) {

        List<SysRole> sysRoleEntiyList = SysRoleBeanCopier.sysRoleModelToSysRoleEntity(sysRoleList);
        List<SysPermission> sysPermissionList =
                rbacJpaRepository.selectSysPermissionBySysRole(sysRoleEntiyList);
        List<SysPermissionModel> sysPermissionModelList =
                SysPermissionBeanCopier.sysPermissionEntityToSysPermissionModel(sysPermissionList);
        return sysPermissionModelList;
    }

    @Override
    public List<SysMenuModel> selectSysMenuBySysPermission(List<SysPermissionModel> sysPermissionList) {
        List<SysPermission> sysPermissionEntiyList =
                SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionList);
        List<SysMenu> sysMenuList = rbacJpaRepository.selectSysMenuBySysPermission(sysPermissionEntiyList);
        List<SysMenuModel> sysMenuModelList = SysMenuBeanCopier.sysMenuEntityToSysMenuModel(sysMenuList);

        return sysMenuModelList;
    }

    @Override
    public List<SysElementModel> selectSysElementBySysPermission(List<SysPermissionModel> sysPermissionList) {
        List<SysPermission> sysPermissionEntiyList =
                SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionList);
        List<SysElement> sysElementList =
                rbacJpaRepository.selectSysElementBySysPermission(sysPermissionEntiyList);
        List<SysElementModel> sysElementModelList =
                SysElementBeanCopier.sysElementEntityToSysElementModel(sysElementList);
        return sysElementModelList;
    }

    @Override
    public List<SysFileModel> selectSysFileBySysPermission(List<SysPermissionModel> sysPermissionList) {
        List<SysPermission> sysPermissionEntiyList =
                SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionList);
        List<SysFile> sysFileList =
                rbacJpaRepository.selectSysFileBySysPermission(sysPermissionEntiyList);

        List<SysFileModel> sysFileModelList = SysFileBeanCopier.sysFileEntityToSysFileModel(sysFileList);
        return sysFileModelList;
    }

    @Override
    public List<SysOperationModel> selectSysOperationBySysPermission(List<SysPermissionModel> sysPermissionList) {
        List<SysPermission> sysPermissionEntiyList =
                SysPermissionBeanCopier.sysPermissionModelToSysPermissionEntity(sysPermissionList);
        List<SysOperation> sysOperationList =
                rbacJpaRepository.selectSysOperationBySysPermission(sysPermissionEntiyList);
        List<SysOperationModel> sysOperationModelList =
                SysOperationBeanCopier.sysOperationEntityToSysOperationModel(sysOperationList);

        return sysOperationModelList;
    }

}

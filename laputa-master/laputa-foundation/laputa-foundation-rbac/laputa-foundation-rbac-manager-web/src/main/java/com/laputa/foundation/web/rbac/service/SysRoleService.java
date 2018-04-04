package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.dao.SysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.dao.SysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.dao.SysUserJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.rbac.dao.SysUserGroupJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysRole;
import com.laputa.foundation.web.rbac.entity.SysPermissionBelongtoRelationSysRole;

import com.laputa.foundation.common.CollectionDiffUtil;
import com.laputa.foundation.web.exception.LaputaWebOperationException;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:11+08:00 .
 */
@Transactional
@Service("sysRoleService")
public class SysRoleService {

    @Resource
    private SysRoleJpaRepository sysRoleJpaRepository;


    @Resource
    private SysPermissionJpaRepository sysPermissionJpaRepository;

    @Resource
    private SysUserJpaRepository sysUserJpaRepository;

    @Resource
    private SysUserGroupJpaRepository sysUserGroupJpaRepository;

    @Resource
    private SysPermissionBelongtoRelationSysRoleJpaRepository sysPermissionBelongtoRelationSysRoleJpaRepository;

    @Resource
    private SysUserBelongtoRelationSysRoleJpaRepository sysUserBelongtoRelationSysRoleJpaRepository;

    public List<SysPermission> readNotHaveOwnSysPermission(Long sysRoleId) {
        List<SysPermission> sysPermissionList = sysRoleJpaRepository.selectNotHaveOwnSysPermission(sysRoleId);
        return sysPermissionList;
    }

    public List<SysPermission> readHaveOwnSysPermission(Long sysRoleId) {
        List<SysPermission> sysPermissionList = sysRoleJpaRepository.selectSysPermissionListBySysRoleId(sysRoleId);
        return sysPermissionList;
    }

    @Transactional
    public SysRole updateHaveOwnSysPermission(SysRole sysRole) {
        SysRole updateSysRole = sysRoleJpaRepository.findById(sysRole.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("角色 [{0}] 无法编辑 因为无此记录 可能已经被删除", sysRole.getId()));
        processOneToManyOwnSysPermissionCollection(sysRole, updateSysRole);

        return updateSysRole;
    }

    @Transactional
    public SysRole create(SysRole sysRole) {

        SysRole createSysRole = new SysRole();

        createSysRole.setCname(sysRole.getCname());
        createSysRole.setCode(sysRole.getCode());
        createSysRole.setInverted(sysRole.getInverted());
        createSysRole.setDescript(sysRole.getDescript());
        if (sysRole.getParentSysUserGroupId() != null) {
            SysUserGroup parentSysUserGroup = sysUserGroupJpaRepository.findById(sysRole.getParentSysUserGroupId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                            .generateException("[ 所属用户组{0} ] 无法配置 因为无此记录 可能已经被删除", sysRole.getParentSysUserGroupId()));
            createSysRole.setParentSysUserGroup(parentSysUserGroup);
        }


        createSysRole = sysRoleJpaRepository.save(createSysRole);

        processOneToManyOwnSysPermissionCollection(sysRole, createSysRole);
        processOneToManyOwnSysUserCollection(sysRole, createSysRole);

        return createSysRole;
    }

    @Transactional
    public void destory(SysRole sysRole) {
        SysRole destorySysRole = sysRoleJpaRepository.findById(sysRole.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("角色 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysRole.getId()));
        sysRoleJpaRepository.delete(destorySysRole);
    }


    @Transactional
    public SysRole update(SysRole sysRole) {
        SysRole updateSysRole = sysRoleJpaRepository.findById(sysRole.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("角色 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysRole.getId()));

        updateSysRole.setCname(sysRole.getCname());
        updateSysRole.setCode(sysRole.getCode());
        updateSysRole.setDescript(sysRole.getDescript());
        if (sysRole.getParentSysUserGroupId() != null) {
            if (!sysRole.getParentSysUserGroupId().equals(updateSysRole.getParentSysUserGroupId())) {
                SysUserGroup parentSysUserGroup = sysUserGroupJpaRepository.findById(sysRole.getParentSysUserGroupId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("角色 所属用户组 用户组 [ {0} ] 无法更新 因为无此记录 可能已经被删除", sysRole.getParentSysUserGroupId()));
                updateSysRole.setParentSysUserGroup(parentSysUserGroup);
            }
        } else {
            updateSysRole.setParentSysUserGroup(null);
        }


        sysRoleJpaRepository.save(updateSysRole);

        processOneToManyOwnSysPermissionCollection(sysRole, updateSysRole);
        processOneToManyOwnSysUserCollection(sysRole, updateSysRole);

        return updateSysRole;
    }


    @Transactional
    public List<SysRole> read() {
        List<SysRole> sysRoleList = sysRoleJpaRepository.findAll();
        return sysRoleList;
    }

    @Transactional
    public List<SysRole> readEager() {
        List<SysRole> sysRoleList = sysRoleJpaRepository.findAll();
        if (sysRoleList != null && sysRoleList.size() > 0) {
            for (SysRole sysRole : sysRoleList) {
                sysRole.getOwnSysPermissionCollection().size();
                sysRole.getOwnSysUserCollection().size();
            }
        }
        return sysRoleList;
    }


    @Transactional
    public Page<SysRole> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysRole> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysRole> sysRolePage = sysRoleJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysRolePage != null && sysRolePage.getContent() != null && sysRolePage.getContent().size() > 0) {
            for (SysRole sysRole : sysRolePage.getContent()) {
                sysRole.getOwnSysPermissionCollection().size();
                sysRole.getOwnSysUserCollection().size();
            }
        }
        return sysRolePage;
    }

    CollectionDiffUtil.Compare<SysUserBelongtoRelationSysRole> sysUserBelongtoRelationSysRoleCompare = new CollectionDiffUtil.Compare<SysUserBelongtoRelationSysRole>() {
        @Override
        public Boolean equal(SysUserBelongtoRelationSysRole left, SysUserBelongtoRelationSysRole right) {
            return left.getSysUserId().equals(right.getSysUserId());
        }
    };
    CollectionDiffUtil.Compare<SysPermissionBelongtoRelationSysRole> sysPermissionBelongtoRelationSysRoleCompare = new CollectionDiffUtil.Compare<SysPermissionBelongtoRelationSysRole>() {
        @Override
        public Boolean equal(SysPermissionBelongtoRelationSysRole left, SysPermissionBelongtoRelationSysRole right) {
            return left.getSysPermissionId().equals(right.getSysPermissionId());
        }
    };


    @Transactional
    private void processOneToManyOwnSysPermissionCollection(SysRole sysRole, SysRole persistedSysRole) {
        Collection<SysPermissionBelongtoRelationSysRole> needClearownSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysRole.getOwnSysPermissionCollection(), sysRole.getOwnSysPermissionCollection(), sysPermissionBelongtoRelationSysRoleCompare);
        if (needClearownSysPermissionCollection != null && needClearownSysPermissionCollection.size() > 0) {
            sysPermissionBelongtoRelationSysRoleJpaRepository.deleteAll(needClearownSysPermissionCollection);
            persistedSysRole.getOwnSysPermissionCollection().removeAll(needClearownSysPermissionCollection);
        }

        Collection<SysPermissionBelongtoRelationSysRole> needProcessOwnSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(sysRole.getOwnSysPermissionCollection(), persistedSysRole.getOwnSysPermissionCollection(), sysPermissionBelongtoRelationSysRoleCompare);
        if (needProcessOwnSysPermissionCollection != null && needProcessOwnSysPermissionCollection.size() > 0) {
            for (SysPermissionBelongtoRelationSysRole needProcessSysPermissionBelongtoRelationSysRole : needProcessOwnSysPermissionCollection) {
                SysPermission persistedNeedProcessSysPermission = sysPermissionJpaRepository.findById(needProcessSysPermissionBelongtoRelationSysRole.getSysPermissionId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("权限 [ {0} ] 不存在", needProcessSysPermissionBelongtoRelationSysRole.getSysPermissionId()));

                SysPermissionBelongtoRelationSysRole sysPermissionBelongtoRelationSysRole = new SysPermissionBelongtoRelationSysRole();
                sysPermissionBelongtoRelationSysRole.setSysRole(persistedSysRole);
                sysPermissionBelongtoRelationSysRole.setSysPermission(persistedNeedProcessSysPermission);
                sysPermissionBelongtoRelationSysRoleJpaRepository.save(sysPermissionBelongtoRelationSysRole);
            }
        }
    }

    @Transactional
    private void processOneToManyOwnSysUserCollection(SysRole sysRole, SysRole persistedSysRole) {
        Collection<SysUserBelongtoRelationSysRole> needClearownSysUserCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysRole.getOwnSysUserCollection(), sysRole.getOwnSysUserCollection(), sysUserBelongtoRelationSysRoleCompare);
        if (needClearownSysUserCollection != null && needClearownSysUserCollection.size() > 0) {
            sysUserBelongtoRelationSysRoleJpaRepository.deleteAll(needClearownSysUserCollection);
            persistedSysRole.getOwnSysUserCollection().removeAll(needClearownSysUserCollection);
        }

        Collection<SysUserBelongtoRelationSysRole> needProcessOwnSysUserCollection = CollectionDiffUtil.inLeftButNotInRiht(sysRole.getOwnSysUserCollection(), persistedSysRole.getOwnSysUserCollection(), sysUserBelongtoRelationSysRoleCompare);
        if (needProcessOwnSysUserCollection != null && needProcessOwnSysUserCollection.size() > 0) {
            for (SysUserBelongtoRelationSysRole needProcessSysUserBelongtoRelationSysRole : needProcessOwnSysUserCollection) {
                SysUser persistedNeedProcessSysUser = sysUserJpaRepository.findById(needProcessSysUserBelongtoRelationSysRole.getSysUserId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("用户 [ {0} ] 不存在", needProcessSysUserBelongtoRelationSysRole.getSysUserId()));

                SysUserBelongtoRelationSysRole sysUserBelongtoRelationSysRole = new SysUserBelongtoRelationSysRole();
                sysUserBelongtoRelationSysRole.setSysRole(persistedSysRole);
                sysUserBelongtoRelationSysRole.setSysUser(persistedNeedProcessSysUser);
                sysUserBelongtoRelationSysRoleJpaRepository.save(sysUserBelongtoRelationSysRole);
            }
        }
    }
}
package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.web.rbac.entity.SysUserGroup;
import com.laputa.foundation.web.rbac.dao.SysUserGroupJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.dao.SysRoleJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserGroupCanhaveRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserBelongtoRelationSysUserGroupJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.dao.SysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserGroupCanhaveRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserBelongtoRelationSysUserGroupJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysUser;
import com.laputa.foundation.web.rbac.dao.SysUserJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserGroupCanhaveRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysUserBelongtoRelationSysUserGroupJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysUserBelongtoRelationSysUserGroup;
import com.laputa.foundation.web.rbac.entity.SysUserGroupCanhaveRelationSysPermission;

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
 * 用户组 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:12+08:00 .
 */
@Transactional
@Service("sysUserGroupService")
public class SysUserGroupService {

    @Resource
    private SysUserGroupJpaRepository sysUserGroupJpaRepository;


    @Resource
    private SysRoleJpaRepository sysRoleJpaRepository;

    @Resource
    private SysPermissionJpaRepository sysPermissionJpaRepository;

    @Resource
    private SysUserJpaRepository sysUserJpaRepository;

    @Resource
    private SysUserGroupCanhaveRelationSysPermissionJpaRepository sysUserGroupCanhaveRelationSysPermissionJpaRepository;

    @Resource
    private SysUserBelongtoRelationSysUserGroupJpaRepository sysUserBelongtoRelationSysUserGroupJpaRepository;

    @Transactional
    public void updateHaveSysPermission(SysUserGroup sysUserGroup) {
        SysUserGroup updateSysUserGroup = sysUserGroupJpaRepository.findById(sysUserGroup.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("用户组 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysUserGroup.getId()));

        processOneToManyCanhaveRelationSysPermissionCollection(sysUserGroup, updateSysUserGroup);

        sysUserGroupJpaRepository.clearOwnSysRoleCollectionRole(sysUserGroup.getId());
    }

    public List<SysPermission> readNotHaveSysPermission(Long sysUserGroupId) {
        List<SysPermission> sysPermissionList = sysUserGroupJpaRepository.selectOtherSysPermissionListBySysUserGroupId(sysUserGroupId);
        return sysPermissionList;
    }

    public List<SysPermission> readHaveSysPermission(Long sysUserGroupId) {
        List<SysPermission> sysPermissionList = sysUserGroupJpaRepository.selectSysPermissionListBySysUserGroupId(sysUserGroupId);
        return sysPermissionList;
    }


    @Transactional
    public SysUserGroup create(SysUserGroup sysUserGroup) {

        SysUserGroup createSysUserGroup = new SysUserGroup();

        createSysUserGroup.setCname(sysUserGroup.getCname());
        createSysUserGroup.setCode(sysUserGroup.getCode());
        createSysUserGroup.setDescript(sysUserGroup.getDescript());

        createSysUserGroup = sysUserGroupJpaRepository.save(createSysUserGroup);

        processOneToManyOwnSysRoleCollection(sysUserGroup, createSysUserGroup);
        processOneToManyCanhaveRelationSysPermissionCollection(sysUserGroup, createSysUserGroup);
        processOneToManyOwnSysUserCollection(sysUserGroup, createSysUserGroup);

        return createSysUserGroup;
    }

    @Transactional
    public void destory(SysUserGroup sysUserGroup) {
        SysUserGroup destorySysUserGroup = sysUserGroupJpaRepository.findById(sysUserGroup.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("用户组 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysUserGroup.getId()));
        sysUserGroupJpaRepository.delete(destorySysUserGroup);
    }


    @Transactional
    public SysUserGroup update(SysUserGroup sysUserGroup) {
        SysUserGroup updateSysUserGroup = sysUserGroupJpaRepository.findById(sysUserGroup.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("用户组 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysUserGroup.getId()));

        updateSysUserGroup.setCname(sysUserGroup.getCname());
        updateSysUserGroup.setCode(sysUserGroup.getCode());
        updateSysUserGroup.setDescript(sysUserGroup.getDescript());

        sysUserGroupJpaRepository.save(updateSysUserGroup);

        processOneToManyOwnSysRoleCollection(sysUserGroup, updateSysUserGroup);
        processOneToManyCanhaveRelationSysPermissionCollection(sysUserGroup, updateSysUserGroup);
        processOneToManyOwnSysUserCollection(sysUserGroup, updateSysUserGroup);

        return updateSysUserGroup;
    }


    @Transactional
    public List<SysUserGroup> read() {
        List<SysUserGroup> sysUserGroupList = sysUserGroupJpaRepository.findAll();
        return sysUserGroupList;
    }

    @Transactional
    public List<SysUserGroup> readEager() {
        List<SysUserGroup> sysUserGroupList = sysUserGroupJpaRepository.findAll();
        if (sysUserGroupList != null && sysUserGroupList.size() > 0) {
            for (SysUserGroup sysUserGroup : sysUserGroupList) {
                sysUserGroup.getOwnSysRoleCollection().size();
                sysUserGroup.getCanhaveRelationSysPermissionCollection().size();
                sysUserGroup.getOwnSysUserCollection().size();
            }
        }
        return sysUserGroupList;
    }


    @Transactional
    public Page<SysUserGroup> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysUserGroup> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysUserGroup> sysUserGroupPage = sysUserGroupJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysUserGroupPage != null && sysUserGroupPage.getContent() != null && sysUserGroupPage.getContent().size() > 0) {
            for (SysUserGroup sysUserGroup : sysUserGroupPage.getContent()) {
                sysUserGroup.getOwnSysRoleCollection().size();
                sysUserGroup.getCanhaveRelationSysPermissionCollection().size();
                sysUserGroup.getOwnSysUserCollection().size();
            }
        }
        return sysUserGroupPage;
    }

    CollectionDiffUtil.Compare<SysUserBelongtoRelationSysUserGroup> sysUserBelongtoRelationSysUserGroupCompare = new CollectionDiffUtil.Compare<SysUserBelongtoRelationSysUserGroup>() {
        @Override
        public Boolean equal(SysUserBelongtoRelationSysUserGroup left, SysUserBelongtoRelationSysUserGroup right) {
            return left.getSysUserId().equals(right.getSysUserId());
        }
    };
    CollectionDiffUtil.Compare<SysUserGroupCanhaveRelationSysPermission> sysUserGroupCanhaveRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysUserGroupCanhaveRelationSysPermission>() {
        @Override
        public Boolean equal(SysUserGroupCanhaveRelationSysPermission left, SysUserGroupCanhaveRelationSysPermission right) {
            return left.getSysPermissionId().equals(right.getSysPermissionId());
        }
    };


    @Transactional
    private void processOneToManyOwnSysRoleCollection(SysUserGroup sysUserGroup, SysUserGroup persistedSysUserGroup) {
        Collection<SysRole> needClearownSysRoleCollection = IdEntityDiffUtil.inLeftButNotInRiht(persistedSysUserGroup.getOwnSysRoleCollection(), sysUserGroup.getOwnSysRoleCollection());
        if (needClearownSysRoleCollection != null && needClearownSysRoleCollection.size() > 0) {
            for (SysRole persistedsysRole : needClearownSysRoleCollection) {
                persistedsysRole.setParentSysUserGroup(null);
            }
            persistedSysUserGroup.getOwnSysRoleCollection().removeAll(needClearownSysRoleCollection);
        }

        Collection<SysRole> needProcessOwnSysRoleCollection = IdEntityDiffUtil.inLeftButNotInRiht(sysUserGroup.getOwnSysRoleCollection(), persistedSysUserGroup.getOwnSysRoleCollection());
        if (needProcessOwnSysRoleCollection != null && needProcessOwnSysRoleCollection.size() > 0) {
            for (SysRole needProcessSysRole : needProcessOwnSysRoleCollection) {
                SysRole persistedNeedProcessSysRole = sysRoleJpaRepository.findById(needProcessSysRole.getId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("角色 [ {0} ] 不存在", needProcessSysRole.getId()));
                persistedNeedProcessSysRole.setParentSysUserGroup(persistedSysUserGroup);
            }
        }
    }

    @Transactional
    private void processOneToManyCanhaveRelationSysPermissionCollection(SysUserGroup sysUserGroup, SysUserGroup persistedSysUserGroup) {
        Collection<SysUserGroupCanhaveRelationSysPermission> needClearcanhaveRelationSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysUserGroup.getCanhaveRelationSysPermissionCollection(), sysUserGroup.getCanhaveRelationSysPermissionCollection(), sysUserGroupCanhaveRelationSysPermissionCompare);
        if (needClearcanhaveRelationSysPermissionCollection != null && needClearcanhaveRelationSysPermissionCollection.size() > 0) {
            sysUserGroupCanhaveRelationSysPermissionJpaRepository.deleteAll(needClearcanhaveRelationSysPermissionCollection);
            persistedSysUserGroup.getCanhaveRelationSysPermissionCollection().removeAll(needClearcanhaveRelationSysPermissionCollection);
        }

        Collection<SysUserGroupCanhaveRelationSysPermission> needProcessCanhaveRelationSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(sysUserGroup.getCanhaveRelationSysPermissionCollection(), persistedSysUserGroup.getCanhaveRelationSysPermissionCollection(), sysUserGroupCanhaveRelationSysPermissionCompare);
        if (needProcessCanhaveRelationSysPermissionCollection != null && needProcessCanhaveRelationSysPermissionCollection.size() > 0) {
            for (SysUserGroupCanhaveRelationSysPermission needProcessSysUserGroupCanhaveRelationSysPermission : needProcessCanhaveRelationSysPermissionCollection) {
                SysPermission persistedNeedProcessSysPermission = sysPermissionJpaRepository.findById(needProcessSysUserGroupCanhaveRelationSysPermission.getSysPermissionId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("权限 [ {0} ] 不存在", needProcessSysUserGroupCanhaveRelationSysPermission.getSysPermissionId()));

                SysUserGroupCanhaveRelationSysPermission sysUserGroupCanhaveRelationSysPermission = new SysUserGroupCanhaveRelationSysPermission();
                sysUserGroupCanhaveRelationSysPermission.setSysUserGroup(persistedSysUserGroup);
                sysUserGroupCanhaveRelationSysPermission.setSysPermission(persistedNeedProcessSysPermission);
                sysUserGroupCanhaveRelationSysPermissionJpaRepository.save(sysUserGroupCanhaveRelationSysPermission);
            }
        }
    }

    @Transactional
    private void processOneToManyOwnSysUserCollection(SysUserGroup sysUserGroup, SysUserGroup persistedSysUserGroup) {
        Collection<SysUserBelongtoRelationSysUserGroup> needClearownSysUserCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysUserGroup.getOwnSysUserCollection(), sysUserGroup.getOwnSysUserCollection(), sysUserBelongtoRelationSysUserGroupCompare);
        if (needClearownSysUserCollection != null && needClearownSysUserCollection.size() > 0) {
            sysUserBelongtoRelationSysUserGroupJpaRepository.deleteAll(needClearownSysUserCollection);
            persistedSysUserGroup.getOwnSysUserCollection().removeAll(needClearownSysUserCollection);
        }

        Collection<SysUserBelongtoRelationSysUserGroup> needProcessOwnSysUserCollection = CollectionDiffUtil.inLeftButNotInRiht(sysUserGroup.getOwnSysUserCollection(), persistedSysUserGroup.getOwnSysUserCollection(), sysUserBelongtoRelationSysUserGroupCompare);
        if (needProcessOwnSysUserCollection != null && needProcessOwnSysUserCollection.size() > 0) {
            for (SysUserBelongtoRelationSysUserGroup needProcessSysUserBelongtoRelationSysUserGroup : needProcessOwnSysUserCollection) {
                SysUser persistedNeedProcessSysUser = sysUserJpaRepository.findById(needProcessSysUserBelongtoRelationSysUserGroup.getSysUserId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("用户 [ {0} ] 不存在", needProcessSysUserBelongtoRelationSysUserGroup.getSysUserId()));

                SysUserBelongtoRelationSysUserGroup sysUserBelongtoRelationSysUserGroup = new SysUserBelongtoRelationSysUserGroup();
                sysUserBelongtoRelationSysUserGroup.setSysUserGroup(persistedSysUserGroup);
                sysUserBelongtoRelationSysUserGroup.setSysUser(persistedNeedProcessSysUser);
                sysUserBelongtoRelationSysUserGroupJpaRepository.save(sysUserBelongtoRelationSysUserGroup);
            }
        }
    }
}
package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.common.CollectionDiffUtil;
import com.laputa.foundation.web.exception.LaputaWebOperationException;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;
import com.laputa.foundation.web.rbac.dao.*;
import com.laputa.foundation.web.rbac.entity.*;
import com.laputa.foundation.web.rbac.security.LaputaBCryptPasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:11+08:00 .
 */
@Transactional
@Service("sysUserService")
public class SysUserService {

    @Resource
    private SysUserJpaRepository sysUserJpaRepository;


    @Resource
    private SysRoleJpaRepository sysRoleJpaRepository;

    @Resource
    private SysUserGroupJpaRepository sysUserGroupJpaRepository;

    @Resource
    private SysUserBelongtoRelationSysRoleJpaRepository sysUserBelongtoRelationSysRoleJpaRepository;

    @Resource
    private SysUserBelongtoRelationSysUserGroupJpaRepository sysUserBelongtoRelationSysUserGroupJpaRepository;

    @Resource
    private LaputaBCryptPasswordEncoder passwordEncoder;

    @Transactional
    public SysUser updateBelongtoSysRoleCollection(SysUser sysUser) {
        SysUser updateSysUser = sysUserJpaRepository.findById(sysUser.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("用户 [{0}] 无法编辑 因为无此记录 可能已经被删除", sysUser.getId()));

        processOneToManyBelongtoSysRoleCollection(sysUser, updateSysUser);

        return updateSysUser;
    }

    @Transactional
    public SysUser updateBelongtoSysUserGroupCollection(SysUser sysUser) {
        SysUser updateSysUser = sysUserJpaRepository.findById(sysUser.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("用户 [{0}] 无法编辑 因为无此记录 可能已经被删除", sysUser.getId()));

        processOneToManyBelongtoSysUserGroupCollection(sysUser, updateSysUser);

        sysUserJpaRepository.clearOwnSysRoleCollection(sysUser.getId());

        return updateSysUser;
    }


    @Transactional
    public SysUser updatePassword(SysUser sysUser) {
        SysUser updateSysUser = sysUserJpaRepository.findById(sysUser.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("用户 [{0}] 无法编辑 因为无此记录 可能已经被删除", sysUser.getId()));

        updateSysUser.setPassword(passwordEncoder.encode(sysUser.getPassword().toLowerCase()));

        sysUserJpaRepository.save(updateSysUser);

        return updateSysUser;
    }

    public List<SysUserGroup> readSysUserBelongtoUserGroupList(Long sysUserId) {
        List<SysUserGroup> sysUserBelongtoUserGroupList =
                sysUserJpaRepository.selectSysUserGroupListBySysUserId(sysUserId);
        if (sysUserBelongtoUserGroupList != null && sysUserBelongtoUserGroupList.size() > 0) {
            for (SysUserGroup sysUserGroup : sysUserBelongtoUserGroupList) {
                sysUserGroup.getOwnSysRoleCollection().size();
            }
        }
        return sysUserBelongtoUserGroupList;
    }

    @Transactional
    public SysUser create(SysUser sysUser) {

        SysUser createSysUser = new SysUser();

        createSysUser.setCname(sysUser.getCname());
        createSysUser.setUsername(sysUser.getUsername());
        createSysUser.setPassword(sysUser.getPassword());
        createSysUser.setLocked(sysUser.getLocked());
        createSysUser.setDescript(sysUser.getDescript());

        createSysUser = sysUserJpaRepository.save(createSysUser);

        processOneToManyBelongtoSysRoleCollection(sysUser, createSysUser);
        processOneToManyBelongtoSysUserGroupCollection(sysUser, createSysUser);

        return createSysUser;
    }

    @Transactional
    public void destory(SysUser sysUser) {
        SysUser destorySysUser = sysUserJpaRepository.findById(sysUser.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("用户 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysUser.getId()));
        sysUserJpaRepository.delete(destorySysUser);
    }


    @Transactional
    public SysUser update(SysUser sysUser) {
        SysUser updateSysUser = sysUserJpaRepository.findById(sysUser.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("用户 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysUser.getId()));

        updateSysUser.setCname(sysUser.getCname());
        updateSysUser.setUsername(sysUser.getUsername());
        updateSysUser.setPassword(sysUser.getPassword());
        updateSysUser.setLocked(sysUser.getLocked());
        updateSysUser.setDescript(sysUser.getDescript());

        sysUserJpaRepository.save(updateSysUser);

        processOneToManyBelongtoSysRoleCollection(sysUser, updateSysUser);
        processOneToManyBelongtoSysUserGroupCollection(sysUser, updateSysUser);

        return updateSysUser;
    }


    @Transactional
    public List<SysUser> read() {
        List<SysUser> sysUserList = sysUserJpaRepository.findAll();
        return sysUserList;
    }

    @Transactional
    public List<SysUser> readEager() {
        List<SysUser> sysUserList = sysUserJpaRepository.findAll();
        if (sysUserList != null && sysUserList.size() > 0) {
            for (SysUser sysUser : sysUserList) {
                sysUser.getBelongtoSysRoleCollection().size();
                sysUser.getBelongtoSysUserGroupCollection().size();
            }
        }
        return sysUserList;
    }


    @Transactional
    public Page<SysUser> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysUser> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysUser> sysUserPage = sysUserJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysUserPage != null && sysUserPage.getContent() != null && sysUserPage.getContent().size() > 0) {
            for (SysUser sysUser : sysUserPage.getContent()) {
                sysUser.getBelongtoSysRoleCollection().size();
                sysUser.getBelongtoSysUserGroupCollection().size();
            }
        }
        return sysUserPage;
    }

    CollectionDiffUtil.Compare<SysUserBelongtoRelationSysRole> sysUserBelongtoRelationSysRoleCompare = new CollectionDiffUtil.Compare<SysUserBelongtoRelationSysRole>() {
        @Override
        public Boolean equal(SysUserBelongtoRelationSysRole left, SysUserBelongtoRelationSysRole right) {
            return left.getSysRoleId().equals(right.getSysRoleId());
        }
    };
    CollectionDiffUtil.Compare<SysUserBelongtoRelationSysUserGroup> sysUserBelongtoRelationSysUserGroupCompare = new CollectionDiffUtil.Compare<SysUserBelongtoRelationSysUserGroup>() {
        @Override
        public Boolean equal(SysUserBelongtoRelationSysUserGroup left, SysUserBelongtoRelationSysUserGroup right) {
            return left.getSysUserGroupId().equals(right.getSysUserGroupId());
        }
    };


    @Transactional
    private void processOneToManyBelongtoSysRoleCollection(SysUser sysUser, SysUser persistedSysUser) {
        Collection<SysUserBelongtoRelationSysRole> needClearbelongtoSysRoleCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysUser.getBelongtoSysRoleCollection(), sysUser.getBelongtoSysRoleCollection(), sysUserBelongtoRelationSysRoleCompare);
        if (needClearbelongtoSysRoleCollection != null && needClearbelongtoSysRoleCollection.size() > 0) {
            sysUserBelongtoRelationSysRoleJpaRepository.deleteAll(needClearbelongtoSysRoleCollection);
            persistedSysUser.getBelongtoSysRoleCollection().removeAll(needClearbelongtoSysRoleCollection);
        }

        Collection<SysUserBelongtoRelationSysRole> needProcessBelongtoSysRoleCollection = CollectionDiffUtil.inLeftButNotInRiht(sysUser.getBelongtoSysRoleCollection(), persistedSysUser.getBelongtoSysRoleCollection(), sysUserBelongtoRelationSysRoleCompare);
        if (needProcessBelongtoSysRoleCollection != null && needProcessBelongtoSysRoleCollection.size() > 0) {
            for (SysUserBelongtoRelationSysRole needProcessSysUserBelongtoRelationSysRole : needProcessBelongtoSysRoleCollection) {
                SysRole persistedNeedProcessSysRole = sysRoleJpaRepository.findById(needProcessSysUserBelongtoRelationSysRole.getSysRoleId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("角色 [ {0} ] 不存在", needProcessSysUserBelongtoRelationSysRole.getSysRoleId()));

                SysUserBelongtoRelationSysRole sysUserBelongtoRelationSysRole = new SysUserBelongtoRelationSysRole();
                sysUserBelongtoRelationSysRole.setSysUser(persistedSysUser);
                sysUserBelongtoRelationSysRole.setSysRole(persistedNeedProcessSysRole);
                sysUserBelongtoRelationSysRoleJpaRepository.save(sysUserBelongtoRelationSysRole);
            }
        }
    }

    @Transactional
    private void processOneToManyBelongtoSysUserGroupCollection(SysUser sysUser, SysUser persistedSysUser) {
        Collection<SysUserBelongtoRelationSysUserGroup> needClearbelongtoSysUserGroupCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysUser.getBelongtoSysUserGroupCollection(), sysUser.getBelongtoSysUserGroupCollection(), sysUserBelongtoRelationSysUserGroupCompare);
        if (needClearbelongtoSysUserGroupCollection != null && needClearbelongtoSysUserGroupCollection.size() > 0) {
            sysUserBelongtoRelationSysUserGroupJpaRepository.deleteAll(needClearbelongtoSysUserGroupCollection);
            persistedSysUser.getBelongtoSysUserGroupCollection().removeAll(needClearbelongtoSysUserGroupCollection);
        }

        Collection<SysUserBelongtoRelationSysUserGroup> needProcessBelongtoSysUserGroupCollection = CollectionDiffUtil.inLeftButNotInRiht(sysUser.getBelongtoSysUserGroupCollection(), persistedSysUser.getBelongtoSysUserGroupCollection(), sysUserBelongtoRelationSysUserGroupCompare);
        if (needProcessBelongtoSysUserGroupCollection != null && needProcessBelongtoSysUserGroupCollection.size() > 0) {
            for (SysUserBelongtoRelationSysUserGroup needProcessSysUserBelongtoRelationSysUserGroup : needProcessBelongtoSysUserGroupCollection) {
                SysUserGroup persistedNeedProcessSysUserGroup = sysUserGroupJpaRepository.findById(needProcessSysUserBelongtoRelationSysUserGroup.getSysUserGroupId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("用户组 [ {0} ] 不存在", needProcessSysUserBelongtoRelationSysUserGroup.getSysUserGroupId()));

                SysUserBelongtoRelationSysUserGroup sysUserBelongtoRelationSysUserGroup = new SysUserBelongtoRelationSysUserGroup();
                sysUserBelongtoRelationSysUserGroup.setSysUser(persistedSysUser);
                sysUserBelongtoRelationSysUserGroup.setSysUserGroup(persistedNeedProcessSysUserGroup);
                sysUserBelongtoRelationSysUserGroupJpaRepository.save(sysUserBelongtoRelationSysUserGroup);
            }
        }
    }
}
package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.dao.SysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysElement;
import com.laputa.foundation.web.rbac.dao.SysElementJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysElementBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysFileBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysMenuBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysOperationBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysFile;
import com.laputa.foundation.web.rbac.dao.SysFileJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysElementBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysFileBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysMenuBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysOperationBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.dao.SysMenuJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysElementBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysFileBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysMenuBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysOperationBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysOperation;
import com.laputa.foundation.web.rbac.dao.SysOperationJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysElementBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysFileBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysMenuBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysOperationBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysRole;
import com.laputa.foundation.web.rbac.dao.SysRoleJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysElementBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysFileBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysMenuBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysOperationBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysPermissionBelongtoRelationSysRoleJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission;
import com.laputa.foundation.web.rbac.entity.SysOperationBelongtoRelationSysPermission;
import com.laputa.foundation.web.rbac.entity.SysElementBelongtoRelationSysPermission;
import com.laputa.foundation.web.rbac.entity.SysFileBelongtoRelationSysPermission;
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
 * 权限 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:11+08:00 .
 */
@Transactional
@Service("sysPermissionService")
public class SysPermissionService {

    @Resource
    private SysPermissionJpaRepository sysPermissionJpaRepository;


    @Resource
    private SysElementJpaRepository sysElementJpaRepository;

    @Resource
    private SysFileJpaRepository sysFileJpaRepository;

    @Resource
    private SysMenuJpaRepository sysMenuJpaRepository;

    @Resource
    private SysOperationJpaRepository sysOperationJpaRepository;

    @Resource
    private SysRoleJpaRepository sysRoleJpaRepository;

    @Resource
    private SysElementBelongtoRelationSysPermissionJpaRepository sysElementBelongtoRelationSysPermissionJpaRepository;

    @Resource
    private SysFileBelongtoRelationSysPermissionJpaRepository sysFileBelongtoRelationSysPermissionJpaRepository;

    @Resource
    private SysMenuBelongtoRelationSysPermissionJpaRepository sysMenuBelongtoRelationSysPermissionJpaRepository;

    @Resource
    private SysOperationBelongtoRelationSysPermissionJpaRepository sysOperationBelongtoRelationSysPermissionJpaRepository;

    @Resource
    private SysPermissionBelongtoRelationSysRoleJpaRepository sysPermissionBelongtoRelationSysRoleJpaRepository;


    @Transactional
    public SysPermission updateOwnSysFileCollection(SysPermission sysPermission) {
        SysPermission updateSysPermission = sysPermissionJpaRepository.findById(sysPermission.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("权限 [{0}] 无法编辑 因为无此记录 可能已经被删除", sysPermission.getId()));

        processOneToManyOwnSysFileCollection(sysPermission, updateSysPermission);

        return updateSysPermission;
    }

    @Transactional
    public SysPermission updateOwnSysMenuCollection(SysPermission sysPermission) {
        SysPermission updateSysPermission = sysPermissionJpaRepository.findById(sysPermission.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("权限 [{0}] 无法编辑 因为无此记录 可能已经被删除", sysPermission.getId()));

        processOneToManyOwnSysMenuCollection(sysPermission, updateSysPermission);

        return updateSysPermission;
    }


    @Transactional
    public SysPermission updateOwnSysOperationCollection(SysPermission sysPermission) {
        SysPermission updateSysPermission = sysPermissionJpaRepository.findById(sysPermission.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("权限 [{0}] 无法编辑 因为无此记录 可能已经被删除", sysPermission.getId()));

        processOneToManyOwnSysOperationCollection(sysPermission, updateSysPermission);

        return updateSysPermission;
    }


    @Transactional
    public SysPermission create(SysPermission sysPermission) {

        SysPermission createSysPermission = new SysPermission();

        createSysPermission.setCname(sysPermission.getCname());
        createSysPermission.setCode(sysPermission.getCode());
        createSysPermission.setDescript(sysPermission.getDescript());

        createSysPermission = sysPermissionJpaRepository.save(createSysPermission);

        processOneToManyOwnSysElementCollection(sysPermission, createSysPermission);
        processOneToManyOwnSysFileCollection(sysPermission, createSysPermission);
        processOneToManyOwnSysMenuCollection(sysPermission, createSysPermission);
        processOneToManyOwnSysOperationCollection(sysPermission, createSysPermission);
        processOneToManyBelongtoSysRoleCollection(sysPermission, createSysPermission);

        return createSysPermission;
    }

    @Transactional
    public void destory(SysPermission sysPermission) {
        SysPermission destorySysPermission = sysPermissionJpaRepository.findById(sysPermission.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("权限 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysPermission.getId()));
        sysPermissionJpaRepository.delete(destorySysPermission);
    }


    @Transactional
    public SysPermission update(SysPermission sysPermission) {
        SysPermission updateSysPermission = sysPermissionJpaRepository.findById(sysPermission.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("权限 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysPermission.getId()));

        updateSysPermission.setCname(sysPermission.getCname());
        updateSysPermission.setCode(sysPermission.getCode());
        updateSysPermission.setDescript(sysPermission.getDescript());

        sysPermissionJpaRepository.save(updateSysPermission);

        processOneToManyOwnSysElementCollection(sysPermission, updateSysPermission);
        processOneToManyOwnSysFileCollection(sysPermission, updateSysPermission);
        processOneToManyOwnSysMenuCollection(sysPermission, updateSysPermission);
        processOneToManyOwnSysOperationCollection(sysPermission, updateSysPermission);
        processOneToManyBelongtoSysRoleCollection(sysPermission, updateSysPermission);

        return updateSysPermission;
    }


    @Transactional
    public List<SysPermission> read() {
        List<SysPermission> sysPermissionList = sysPermissionJpaRepository.findAll();
        return sysPermissionList;
    }

    @Transactional
    public List<SysPermission> readEager() {
        List<SysPermission> sysPermissionList = sysPermissionJpaRepository.findAll();
        if (sysPermissionList != null && sysPermissionList.size() > 0) {
            for (SysPermission sysPermission : sysPermissionList) {
                sysPermission.getOwnSysElementCollection().size();
                sysPermission.getOwnSysFileCollection().size();
                sysPermission.getOwnSysMenuCollection().size();
                sysPermission.getOwnSysOperationCollection().size();
                sysPermission.getBelongtoSysRoleCollection().size();
            }
        }
        return sysPermissionList;
    }


    @Transactional
    public Page<SysPermission> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysPermission> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysPermission> sysPermissionPage = sysPermissionJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysPermissionPage != null && sysPermissionPage.getContent() != null && sysPermissionPage.getContent().size() > 0) {
            for (SysPermission sysPermission : sysPermissionPage.getContent()) {
                sysPermission.getOwnSysElementCollection().size();
                sysPermission.getOwnSysFileCollection().size();
                sysPermission.getOwnSysMenuCollection().size();
                sysPermission.getOwnSysOperationCollection().size();
                sysPermission.getBelongtoSysRoleCollection().size();
            }
        }
        return sysPermissionPage;
    }

    CollectionDiffUtil.Compare<SysMenuBelongtoRelationSysPermission> sysMenuBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysMenuBelongtoRelationSysPermission>() {
        @Override
        public Boolean equal(SysMenuBelongtoRelationSysPermission left, SysMenuBelongtoRelationSysPermission right) {
            return left.getSysMenuId().equals(right.getSysMenuId());
        }
    };
    CollectionDiffUtil.Compare<SysOperationBelongtoRelationSysPermission> sysOperationBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysOperationBelongtoRelationSysPermission>() {
        @Override
        public Boolean equal(SysOperationBelongtoRelationSysPermission left, SysOperationBelongtoRelationSysPermission right) {
            return left.getSysOperationId().equals(right.getSysOperationId());
        }
    };
    CollectionDiffUtil.Compare<SysElementBelongtoRelationSysPermission> sysElementBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysElementBelongtoRelationSysPermission>() {
        @Override
        public Boolean equal(SysElementBelongtoRelationSysPermission left, SysElementBelongtoRelationSysPermission right) {
            return left.getSysElementId().equals(right.getSysElementId());
        }
    };
    CollectionDiffUtil.Compare<SysFileBelongtoRelationSysPermission> sysFileBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysFileBelongtoRelationSysPermission>() {
        @Override
        public Boolean equal(SysFileBelongtoRelationSysPermission left, SysFileBelongtoRelationSysPermission right) {
            return left.getSysFileId().equals(right.getSysFileId());
        }
    };
    CollectionDiffUtil.Compare<SysPermissionBelongtoRelationSysRole> sysPermissionBelongtoRelationSysRoleCompare = new CollectionDiffUtil.Compare<SysPermissionBelongtoRelationSysRole>() {
        @Override
        public Boolean equal(SysPermissionBelongtoRelationSysRole left, SysPermissionBelongtoRelationSysRole right) {
            return left.getSysRoleId().equals(right.getSysRoleId());
        }
    };


    @Transactional
    private void processOneToManyOwnSysElementCollection(SysPermission sysPermission, SysPermission persistedSysPermission) {
        Collection<SysElementBelongtoRelationSysPermission> needClearownSysElementCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysPermission.getOwnSysElementCollection(), sysPermission.getOwnSysElementCollection(), sysElementBelongtoRelationSysPermissionCompare);
        if (needClearownSysElementCollection != null && needClearownSysElementCollection.size() > 0) {
            sysElementBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearownSysElementCollection);
            persistedSysPermission.getOwnSysElementCollection().removeAll(needClearownSysElementCollection);
        }

        Collection<SysElementBelongtoRelationSysPermission> needProcessOwnSysElementCollection = CollectionDiffUtil.inLeftButNotInRiht(sysPermission.getOwnSysElementCollection(), persistedSysPermission.getOwnSysElementCollection(), sysElementBelongtoRelationSysPermissionCompare);
        if (needProcessOwnSysElementCollection != null && needProcessOwnSysElementCollection.size() > 0) {
            for (SysElementBelongtoRelationSysPermission needProcessSysElementBelongtoRelationSysPermission : needProcessOwnSysElementCollection) {
                SysElement persistedNeedProcessSysElement = sysElementJpaRepository.findById(needProcessSysElementBelongtoRelationSysPermission.getSysElementId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("系统元素 [ {0} ] 不存在", needProcessSysElementBelongtoRelationSysPermission.getSysElementId()));

                SysElementBelongtoRelationSysPermission sysElementBelongtoRelationSysPermission = new SysElementBelongtoRelationSysPermission();
                sysElementBelongtoRelationSysPermission.setSysPermission(persistedSysPermission);
                sysElementBelongtoRelationSysPermission.setSysElement(persistedNeedProcessSysElement);
                sysElementBelongtoRelationSysPermissionJpaRepository.save(sysElementBelongtoRelationSysPermission);
            }
        }
    }

    @Transactional
    private void processOneToManyOwnSysFileCollection(SysPermission sysPermission, SysPermission persistedSysPermission) {
        Collection<SysFileBelongtoRelationSysPermission> needClearownSysFileCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysPermission.getOwnSysFileCollection(), sysPermission.getOwnSysFileCollection(), sysFileBelongtoRelationSysPermissionCompare);
        if (needClearownSysFileCollection != null && needClearownSysFileCollection.size() > 0) {
            sysFileBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearownSysFileCollection);
            persistedSysPermission.getOwnSysFileCollection().removeAll(needClearownSysFileCollection);
        }

        Collection<SysFileBelongtoRelationSysPermission> needProcessOwnSysFileCollection = CollectionDiffUtil.inLeftButNotInRiht(sysPermission.getOwnSysFileCollection(), persistedSysPermission.getOwnSysFileCollection(), sysFileBelongtoRelationSysPermissionCompare);
        if (needProcessOwnSysFileCollection != null && needProcessOwnSysFileCollection.size() > 0) {
            for (SysFileBelongtoRelationSysPermission needProcessSysFileBelongtoRelationSysPermission : needProcessOwnSysFileCollection) {
                SysFile persistedNeedProcessSysFile = sysFileJpaRepository.findById(needProcessSysFileBelongtoRelationSysPermission.getSysFileId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("系统文件 [ {0} ] 不存在", needProcessSysFileBelongtoRelationSysPermission.getSysFileId()));

                SysFileBelongtoRelationSysPermission sysFileBelongtoRelationSysPermission = new SysFileBelongtoRelationSysPermission();
                sysFileBelongtoRelationSysPermission.setSysPermission(persistedSysPermission);
                sysFileBelongtoRelationSysPermission.setSysFile(persistedNeedProcessSysFile);
                sysFileBelongtoRelationSysPermissionJpaRepository.save(sysFileBelongtoRelationSysPermission);
            }
        }
    }

    @Transactional
    private void processOneToManyOwnSysMenuCollection(SysPermission sysPermission, SysPermission persistedSysPermission) {
        Collection<SysMenuBelongtoRelationSysPermission> needClearownSysMenuCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysPermission.getOwnSysMenuCollection(), sysPermission.getOwnSysMenuCollection(), sysMenuBelongtoRelationSysPermissionCompare);
        if (needClearownSysMenuCollection != null && needClearownSysMenuCollection.size() > 0) {
            sysMenuBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearownSysMenuCollection);
            persistedSysPermission.getOwnSysMenuCollection().removeAll(needClearownSysMenuCollection);
        }

        Collection<SysMenuBelongtoRelationSysPermission> needProcessOwnSysMenuCollection = CollectionDiffUtil.inLeftButNotInRiht(sysPermission.getOwnSysMenuCollection(), persistedSysPermission.getOwnSysMenuCollection(), sysMenuBelongtoRelationSysPermissionCompare);
        if (needProcessOwnSysMenuCollection != null && needProcessOwnSysMenuCollection.size() > 0) {
            for (SysMenuBelongtoRelationSysPermission needProcessSysMenuBelongtoRelationSysPermission : needProcessOwnSysMenuCollection) {
                SysMenu persistedNeedProcessSysMenu = sysMenuJpaRepository.findById(needProcessSysMenuBelongtoRelationSysPermission.getSysMenuId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("菜单 [ {0} ] 不存在", needProcessSysMenuBelongtoRelationSysPermission.getSysMenuId()));

                SysMenuBelongtoRelationSysPermission sysMenuBelongtoRelationSysPermission = new SysMenuBelongtoRelationSysPermission();
                sysMenuBelongtoRelationSysPermission.setSysPermission(persistedSysPermission);
                sysMenuBelongtoRelationSysPermission.setSysMenu(persistedNeedProcessSysMenu);
                sysMenuBelongtoRelationSysPermissionJpaRepository.save(sysMenuBelongtoRelationSysPermission);
            }
        }
    }

    @Transactional
    private void processOneToManyOwnSysOperationCollection(SysPermission sysPermission, SysPermission persistedSysPermission) {
        Collection<SysOperationBelongtoRelationSysPermission> needClearownSysOperationCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysPermission.getOwnSysOperationCollection(), sysPermission.getOwnSysOperationCollection(), sysOperationBelongtoRelationSysPermissionCompare);
        if (needClearownSysOperationCollection != null && needClearownSysOperationCollection.size() > 0) {
            sysOperationBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearownSysOperationCollection);
            persistedSysPermission.getOwnSysOperationCollection().removeAll(needClearownSysOperationCollection);
        }

        Collection<SysOperationBelongtoRelationSysPermission> needProcessOwnSysOperationCollection = CollectionDiffUtil.inLeftButNotInRiht(sysPermission.getOwnSysOperationCollection(), persistedSysPermission.getOwnSysOperationCollection(), sysOperationBelongtoRelationSysPermissionCompare);
        if (needProcessOwnSysOperationCollection != null && needProcessOwnSysOperationCollection.size() > 0) {
            for (SysOperationBelongtoRelationSysPermission needProcessSysOperationBelongtoRelationSysPermission : needProcessOwnSysOperationCollection) {
                SysOperation persistedNeedProcessSysOperation = sysOperationJpaRepository.findById(needProcessSysOperationBelongtoRelationSysPermission.getSysOperationId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("功能操作 [ {0} ] 不存在", needProcessSysOperationBelongtoRelationSysPermission.getSysOperationId()));

                SysOperationBelongtoRelationSysPermission sysOperationBelongtoRelationSysPermission = new SysOperationBelongtoRelationSysPermission();
                sysOperationBelongtoRelationSysPermission.setSysPermission(persistedSysPermission);
                sysOperationBelongtoRelationSysPermission.setSysOperation(persistedNeedProcessSysOperation);
                sysOperationBelongtoRelationSysPermissionJpaRepository.save(sysOperationBelongtoRelationSysPermission);
            }
        }
    }

    @Transactional
    private void processOneToManyBelongtoSysRoleCollection(SysPermission sysPermission, SysPermission persistedSysPermission) {
        Collection<SysPermissionBelongtoRelationSysRole> needClearbelongtoSysRoleCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysPermission.getBelongtoSysRoleCollection(), sysPermission.getBelongtoSysRoleCollection(), sysPermissionBelongtoRelationSysRoleCompare);
        if (needClearbelongtoSysRoleCollection != null && needClearbelongtoSysRoleCollection.size() > 0) {
            sysPermissionBelongtoRelationSysRoleJpaRepository.deleteAll(needClearbelongtoSysRoleCollection);
            persistedSysPermission.getBelongtoSysRoleCollection().removeAll(needClearbelongtoSysRoleCollection);
        }

        Collection<SysPermissionBelongtoRelationSysRole> needProcessBelongtoSysRoleCollection = CollectionDiffUtil.inLeftButNotInRiht(sysPermission.getBelongtoSysRoleCollection(), persistedSysPermission.getBelongtoSysRoleCollection(), sysPermissionBelongtoRelationSysRoleCompare);
        if (needProcessBelongtoSysRoleCollection != null && needProcessBelongtoSysRoleCollection.size() > 0) {
            for (SysPermissionBelongtoRelationSysRole needProcessSysPermissionBelongtoRelationSysRole : needProcessBelongtoSysRoleCollection) {
                SysRole persistedNeedProcessSysRole = sysRoleJpaRepository.findById(needProcessSysPermissionBelongtoRelationSysRole.getSysRoleId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("角色 [ {0} ] 不存在", needProcessSysPermissionBelongtoRelationSysRole.getSysRoleId()));

                SysPermissionBelongtoRelationSysRole sysPermissionBelongtoRelationSysRole = new SysPermissionBelongtoRelationSysRole();
                sysPermissionBelongtoRelationSysRole.setSysPermission(persistedSysPermission);
                sysPermissionBelongtoRelationSysRole.setSysRole(persistedNeedProcessSysRole);
                sysPermissionBelongtoRelationSysRoleJpaRepository.save(sysPermissionBelongtoRelationSysRole);
            }
        }
    }
}
package com.laputa.foundation.web.rbac.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.persistence.util.ParentAbleIdEntityCheckCircularReferenceUtil;
import com.laputa.foundation.web.rbac.entity.SysMenu;
import com.laputa.foundation.web.rbac.dao.SysMenuJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysMenuBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysPermission;
import com.laputa.foundation.web.rbac.dao.SysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.dao.SysMenuBelongtoRelationSysPermissionJpaRepository;
import com.laputa.foundation.web.rbac.entity.SysMenuBelongtoRelationSysPermission;

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
* 菜单 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-03-07T16:26:11+08:00 .
*/
@Transactional
@Service("sysMenuService")
public class SysMenuService {

    @Resource
    private SysMenuJpaRepository sysMenuJpaRepository;


    @Resource
    private SysPermissionJpaRepository sysPermissionJpaRepository;

    @Resource
    private SysMenuBelongtoRelationSysPermissionJpaRepository sysMenuBelongtoRelationSysPermissionJpaRepository;

    @Transactional
    public SysMenu create(SysMenu sysMenu) {

        SysMenu createSysMenu = new SysMenu();

        createSysMenu.setCname(sysMenu.getCname());
        createSysMenu.setCode(sysMenu.getCode());
        createSysMenu.setIconClass(sysMenu.getIconClass());
        createSysMenu.setResources(sysMenu.getResources());
        createSysMenu.setDescript(sysMenu.getDescript());
        createSysMenu.setOrderValue(sysMenu.getOrderValue());
        if( sysMenu.getParentId() != null ){
        SysMenu parent = sysMenuJpaRepository.findById(sysMenu.getParentId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                    .generateException("[ 父菜单{0} ] 无法配置 因为无此记录 可能已经被删除", sysMenu.getParentId()));
            createSysMenu.setParent(parent);
        }
        


        createSysMenu = sysMenuJpaRepository.save(createSysMenu);

        processOneToManyChildren(sysMenu,createSysMenu);
        processOneToManyBelongtoSysPermissionCollection(sysMenu,createSysMenu);

        return createSysMenu;
    }

    @Transactional
    public void destory(SysMenu sysMenu) {
        SysMenu destorySysMenu = sysMenuJpaRepository.findById(sysMenu.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("菜单 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysMenu.getId()));
        sysMenuJpaRepository.delete(destorySysMenu);
    }


    @Transactional
    public SysMenu update(SysMenu sysMenu) {
        SysMenu updateSysMenu = sysMenuJpaRepository.findById(sysMenu.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("菜单 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysMenu.getId()));

        updateSysMenu.setCname(sysMenu.getCname());
        updateSysMenu.setCode(sysMenu.getCode());
        updateSysMenu.setIconClass(sysMenu.getIconClass());
        updateSysMenu.setResources(sysMenu.getResources());
        updateSysMenu.setDescript(sysMenu.getDescript());
        updateSysMenu.setOrderValue(sysMenu.getOrderValue());
        if( sysMenu.getParentId() != null ){
            if(!sysMenu.getParentId().equals(updateSysMenu.getParentId()) ){
                SysMenu parent = sysMenuJpaRepository.findById(sysMenu.getParentId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                        .generateException("菜单 父菜单 菜单 [ {0} ] 无法更新 因为无此记录 可能已经被删除", sysMenu.getParentId()));

                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(sysMenu.getId(),parent)){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException( "{0}  同 {1} 存在嵌套关系",updateSysMenu.getCname(),parent.getCname());
                }
                updateSysMenu.setParent(parent);
        }
        }else{
            updateSysMenu.setParent(null);
        }


        sysMenuJpaRepository.save(updateSysMenu);

        processOneToManyChildren(sysMenu,updateSysMenu);
        processOneToManyBelongtoSysPermissionCollection(sysMenu,updateSysMenu);

        return updateSysMenu;
    }


    @Transactional
    public List<SysMenu> read() {
        List<SysMenu> sysMenuList = sysMenuJpaRepository.findAll();
        return sysMenuList;
    }

    @Transactional
    public List<SysMenu> readEager() {
        List<SysMenu> sysMenuList = sysMenuJpaRepository.findAll();
        if( sysMenuList != null && sysMenuList.size() > 0 ){
            for( SysMenu sysMenu :  sysMenuList ){
                sysMenu.getChildren().size();
                sysMenu.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysMenuList;
    }


    @Transactional
    public Page<SysMenu> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysMenu> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysMenu> sysMenuPage = sysMenuJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysMenuPage != null && sysMenuPage.getContent() != null && sysMenuPage.getContent().size() > 0) {
            for (SysMenu sysMenu : sysMenuPage.getContent()) {
                sysMenu.getChildren().size();
                sysMenu.getBelongtoSysPermissionCollection().size();
            }
        }
        return sysMenuPage;
    }

    CollectionDiffUtil.Compare<SysMenuBelongtoRelationSysPermission> sysMenuBelongtoRelationSysPermissionCompare = new CollectionDiffUtil.Compare<SysMenuBelongtoRelationSysPermission>() {
            @Override
            public Boolean equal(SysMenuBelongtoRelationSysPermission left, SysMenuBelongtoRelationSysPermission right) {
                return left.getSysPermissionId().equals(right.getSysPermissionId());
            }
        };


    @Transactional
    private void processOneToManyChildren(SysMenu sysMenu, SysMenu persistedSysMenu) {
        Collection<SysMenu> needClearchildren = IdEntityDiffUtil.inLeftButNotInRiht(persistedSysMenu.getChildren(),sysMenu.getChildren());
        if (needClearchildren != null && needClearchildren.size() > 0) {
            for(SysMenu persistedsysMenu : needClearchildren ){
                persistedsysMenu.setParent(null);
            }
            persistedSysMenu.getChildren().removeAll(needClearchildren);
        }

        Collection<SysMenu> needProcessChildren = IdEntityDiffUtil.inLeftButNotInRiht(sysMenu.getChildren(),persistedSysMenu.getChildren());
        if(needProcessChildren != null && needProcessChildren.size() > 0){
            for( SysMenu needProcessSysMenu :  needProcessChildren){
                SysMenu persistedNeedProcessSysMenu = sysMenuJpaRepository.findById(needProcessSysMenu.getId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("菜单 [ {0} ] 不存在", needProcessSysMenu.getId()));
                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(persistedNeedProcessSysMenu.getId(),persistedSysMenu)){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException("{0}  同 {1} 存在嵌套关系", persistedNeedProcessSysMenu.getCname(),persistedSysMenu.getCname());
                }
                persistedNeedProcessSysMenu.setParent(persistedSysMenu);
            }
        }
    }
    @Transactional
    private void processOneToManyBelongtoSysPermissionCollection(SysMenu sysMenu, SysMenu persistedSysMenu) {
        Collection<SysMenuBelongtoRelationSysPermission> needClearbelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysMenu.getBelongtoSysPermissionCollection(),sysMenu.getBelongtoSysPermissionCollection(),sysMenuBelongtoRelationSysPermissionCompare);
        if (needClearbelongtoSysPermissionCollection != null && needClearbelongtoSysPermissionCollection.size() > 0) {
            sysMenuBelongtoRelationSysPermissionJpaRepository.deleteAll(needClearbelongtoSysPermissionCollection);
            persistedSysMenu.getBelongtoSysPermissionCollection().removeAll(needClearbelongtoSysPermissionCollection);
        }

        Collection<SysMenuBelongtoRelationSysPermission> needProcessBelongtoSysPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(sysMenu.getBelongtoSysPermissionCollection(),persistedSysMenu.getBelongtoSysPermissionCollection(),sysMenuBelongtoRelationSysPermissionCompare);
        if(needProcessBelongtoSysPermissionCollection != null && needProcessBelongtoSysPermissionCollection.size() > 0){
            for( SysMenuBelongtoRelationSysPermission needProcessSysMenuBelongtoRelationSysPermission :  needProcessBelongtoSysPermissionCollection){
                SysPermission persistedNeedProcessSysPermission = sysPermissionJpaRepository.findById(needProcessSysMenuBelongtoRelationSysPermission.getSysPermissionId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                        .generateException("权限 [ {0} ] 不存在", needProcessSysMenuBelongtoRelationSysPermission.getSysPermissionId()));

                    SysMenuBelongtoRelationSysPermission sysMenuBelongtoRelationSysPermission = new SysMenuBelongtoRelationSysPermission();
                    sysMenuBelongtoRelationSysPermission.setSysMenu(persistedSysMenu);
                    sysMenuBelongtoRelationSysPermission.setSysPermission(persistedNeedProcessSysPermission);
                    sysMenuBelongtoRelationSysPermissionJpaRepository.save(sysMenuBelongtoRelationSysPermission);
            }
        }
    }
}
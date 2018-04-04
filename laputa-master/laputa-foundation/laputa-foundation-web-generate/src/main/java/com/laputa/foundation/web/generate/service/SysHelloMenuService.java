package com.laputa.foundation.web.generate.service;

import com.laputa.foundation.persistence.util.IdEntityDiffUtil;
import com.laputa.foundation.persistence.util.ParentAbleIdEntityCheckCircularReferenceUtil;
import com.laputa.foundation.web.generate.entity.SysHelloMenu;
import com.laputa.foundation.web.generate.dao.SysHelloMenuJpaRepository;
import com.laputa.foundation.web.generate.dao.SysHelloMenuBelongtoRelationSysHelloPermissionJpaRepository;
import com.laputa.foundation.web.generate.entity.SysHelloPermission;
import com.laputa.foundation.web.generate.dao.SysHelloPermissionJpaRepository;
import com.laputa.foundation.web.generate.dao.SysHelloMenuBelongtoRelationSysHelloPermissionJpaRepository;
import com.laputa.foundation.web.generate.entity.SysHelloMenuBelongtoRelationSysHelloPermission;

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
 * Hi菜单 Service<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-03-07T16:15:25+08:00 .
 */
@Transactional
@Service("sysHelloMenuService")
public class SysHelloMenuService {

    @Resource
    private SysHelloMenuJpaRepository sysHelloMenuJpaRepository;


    @Resource
    private SysHelloPermissionJpaRepository sysHelloPermissionJpaRepository;

    @Resource
    private SysHelloMenuBelongtoRelationSysHelloPermissionJpaRepository sysHelloMenuBelongtoRelationSysHelloPermissionJpaRepository;

    @Transactional
    public SysHelloMenu create(SysHelloMenu sysHelloMenu) {

        SysHelloMenu createSysHelloMenu = new SysHelloMenu();

        createSysHelloMenu.setCname(sysHelloMenu.getCname());
        createSysHelloMenu.setCode(sysHelloMenu.getCode());
        createSysHelloMenu.setIconClass(sysHelloMenu.getIconClass());
        if( sysHelloMenu.getParentId() != null ){
            SysHelloMenu parent = sysHelloMenuJpaRepository.findById(sysHelloMenu.getParentId())
                    .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                            .generateException("[ 父菜单{0} ] 无法配置 因为无此记录 可能已经被删除", sysHelloMenu.getParentId()));
            createSysHelloMenu.setParent(parent);
        }



        createSysHelloMenu = sysHelloMenuJpaRepository.save(createSysHelloMenu);

        processOneToManyChildren(sysHelloMenu,createSysHelloMenu);
        processOneToManyBelongSysHelloPermissionCollection(sysHelloMenu,createSysHelloMenu);

        return createSysHelloMenu;
    }

    @Transactional
    public void destory(SysHelloMenu sysHelloMenu) {
        SysHelloMenu destorySysHelloMenu = sysHelloMenuJpaRepository.findById(sysHelloMenu.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                        .generateException("Hi菜单 [ {0} ] 无法删除 因为无此记录 可能已经被删除", sysHelloMenu.getId()));
        sysHelloMenuJpaRepository.delete(destorySysHelloMenu);
    }


    @Transactional
    public SysHelloMenu update(SysHelloMenu sysHelloMenu) {
        SysHelloMenu updateSysHelloMenu = sysHelloMenuJpaRepository.findById(sysHelloMenu.getId())
                .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                        .generateException("Hi菜单 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", sysHelloMenu.getId()));

        updateSysHelloMenu.setCname(sysHelloMenu.getCname());
        updateSysHelloMenu.setCode(sysHelloMenu.getCode());
        updateSysHelloMenu.setIconClass(sysHelloMenu.getIconClass());
        if( sysHelloMenu.getParentId() != null ){
            if(!sysHelloMenu.getParentId().equals(updateSysHelloMenu.getParentId()) ){
                SysHelloMenu parent = sysHelloMenuJpaRepository.findById(sysHelloMenu.getParentId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_PARENT_NONEXISTENT
                                .generateException("Hi菜单 父菜单 Hi菜单 [ {0} ] 无法更新 因为无此记录 可能已经被删除", sysHelloMenu.getParentId()));

                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(sysHelloMenu.getId(),parent)){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException( "{0}  同 {1} 存在嵌套关系",updateSysHelloMenu.getCname(),parent.getCname());
                }
                updateSysHelloMenu.setParent(parent);
            }
        }else{
            updateSysHelloMenu.setParent(null);
        }


        sysHelloMenuJpaRepository.save(updateSysHelloMenu);

        processOneToManyChildren(sysHelloMenu,updateSysHelloMenu);
        processOneToManyBelongSysHelloPermissionCollection(sysHelloMenu,updateSysHelloMenu);

        return updateSysHelloMenu;
    }


    @Transactional
    public List<SysHelloMenu> read() {
        List<SysHelloMenu> sysHelloMenuList = sysHelloMenuJpaRepository.findAll();
        return sysHelloMenuList;
    }

    @Transactional
    public List<SysHelloMenu> readEager() {
        List<SysHelloMenu> sysHelloMenuList = sysHelloMenuJpaRepository.findAll();
        if( sysHelloMenuList != null && sysHelloMenuList.size() > 0 ){
            for( SysHelloMenu sysHelloMenu :  sysHelloMenuList ){
                sysHelloMenu.getChildren().size();
                sysHelloMenu.getBelongSysHelloPermissionCollection().size();
            }
        }
        return sysHelloMenuList;
    }


    @Transactional
    public Page<SysHelloMenu> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<SysHelloMenu> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<SysHelloMenu> sysHelloMenuPage = sysHelloMenuJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (sysHelloMenuPage != null && sysHelloMenuPage.getContent() != null && sysHelloMenuPage.getContent().size() > 0) {
            for (SysHelloMenu sysHelloMenu : sysHelloMenuPage.getContent()) {
                sysHelloMenu.getChildren().size();
                sysHelloMenu.getBelongSysHelloPermissionCollection().size();
            }
        }
        return sysHelloMenuPage;
    }

    CollectionDiffUtil.Compare<SysHelloMenuBelongtoRelationSysHelloPermission> sysHelloMenuBelongtoRelationSysHelloPermissionCompare = new CollectionDiffUtil.Compare<SysHelloMenuBelongtoRelationSysHelloPermission>() {
        @Override
        public Boolean equal(SysHelloMenuBelongtoRelationSysHelloPermission left, SysHelloMenuBelongtoRelationSysHelloPermission right) {
            return left.getSysHelloPermissionId().equals(right.getSysHelloPermissionId());
        }
    };


    @Transactional
    private void processOneToManyChildren(SysHelloMenu sysHelloMenu, SysHelloMenu persistedSysHelloMenu) {
        Collection<SysHelloMenu> needClearchildren = IdEntityDiffUtil.inLeftButNotInRiht(persistedSysHelloMenu.getChildren(),sysHelloMenu.getChildren());
        if (needClearchildren != null && needClearchildren.size() > 0) {
            for(SysHelloMenu persistedsysHelloMenu : needClearchildren ){
                persistedsysHelloMenu.setParent(null);
            }
            persistedSysHelloMenu.getChildren().removeAll(needClearchildren);
        }

        Collection<SysHelloMenu> needProcessChildren = IdEntityDiffUtil.inLeftButNotInRiht(sysHelloMenu.getChildren(),persistedSysHelloMenu.getChildren());
        if(needProcessChildren != null && needProcessChildren.size() > 0){
            for( SysHelloMenu needProcessSysHelloMenu :  needProcessChildren){
                SysHelloMenu persistedNeedProcessSysHelloMenu = sysHelloMenuJpaRepository.findById(needProcessSysHelloMenu.getId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("Hi菜单 [ {0} ] 不存在", needProcessSysHelloMenu.getId()));
                if(ParentAbleIdEntityCheckCircularReferenceUtil.checkCircularReference(persistedNeedProcessSysHelloMenu.getId(),persistedSysHelloMenu)){
                    throw LaputaWebOperationException.ExceptionEnum.RELATION_EXIS_CIRCLE.generateException("{0}  同 {1} 存在嵌套关系", persistedNeedProcessSysHelloMenu.getCname(),persistedSysHelloMenu.getCname());
                }
                persistedNeedProcessSysHelloMenu.setParent(persistedSysHelloMenu);
            }
        }
    }
    @Transactional
    private void processOneToManyBelongSysHelloPermissionCollection(SysHelloMenu sysHelloMenu, SysHelloMenu persistedSysHelloMenu) {
        Collection<SysHelloMenuBelongtoRelationSysHelloPermission> needClearbelongSysHelloPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(persistedSysHelloMenu.getBelongSysHelloPermissionCollection(),sysHelloMenu.getBelongSysHelloPermissionCollection(),sysHelloMenuBelongtoRelationSysHelloPermissionCompare);
        if (needClearbelongSysHelloPermissionCollection != null && needClearbelongSysHelloPermissionCollection.size() > 0) {
            sysHelloMenuBelongtoRelationSysHelloPermissionJpaRepository.deleteAll(needClearbelongSysHelloPermissionCollection);
            persistedSysHelloMenu.getBelongSysHelloPermissionCollection().removeAll(needClearbelongSysHelloPermissionCollection);
        }

        Collection<SysHelloMenuBelongtoRelationSysHelloPermission> needProcessBelongSysHelloPermissionCollection = CollectionDiffUtil.inLeftButNotInRiht(sysHelloMenu.getBelongSysHelloPermissionCollection(),persistedSysHelloMenu.getBelongSysHelloPermissionCollection(),sysHelloMenuBelongtoRelationSysHelloPermissionCompare);
        if(needProcessBelongSysHelloPermissionCollection != null && needProcessBelongSysHelloPermissionCollection.size() > 0){
            for( SysHelloMenuBelongtoRelationSysHelloPermission needProcessSysHelloMenuBelongtoRelationSysHelloPermission :  needProcessBelongSysHelloPermissionCollection){
                SysHelloPermission persistedNeedProcessSysHelloPermission = sysHelloPermissionJpaRepository.findById(needProcessSysHelloMenuBelongtoRelationSysHelloPermission.getSysHelloPermissionId())
                        .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.RELATION_TARGET_NONEXISTENT
                                .generateException("Hi权限 [ {0} ] 不存在", needProcessSysHelloMenuBelongtoRelationSysHelloPermission.getSysHelloPermissionId()));

                SysHelloMenuBelongtoRelationSysHelloPermission sysHelloMenuBelongtoRelationSysHelloPermission = new SysHelloMenuBelongtoRelationSysHelloPermission();
                sysHelloMenuBelongtoRelationSysHelloPermission.setSysHelloMenu(persistedSysHelloMenu);
                sysHelloMenuBelongtoRelationSysHelloPermission.setSysHelloPermission(persistedNeedProcessSysHelloPermission);
                sysHelloMenuBelongtoRelationSysHelloPermissionJpaRepository.save(sysHelloMenuBelongtoRelationSysHelloPermission);
            }
        }
    }
}
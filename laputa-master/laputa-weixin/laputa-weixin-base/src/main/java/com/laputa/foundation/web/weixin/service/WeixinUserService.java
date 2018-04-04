package com.laputa.foundation.web.weixin.service;

import com.laputa.foundation.web.weixin.entity.WeixinUser;
import com.laputa.foundation.web.weixin.dao.WeixinUserJpaRepository;
import com.laputa.foundation.web.exception.LaputaWebOperationException;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.specification.LaputaKendoSpecification;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* <p>
* 微信用户 Service<br>
* /p>
* Created by JiangDongPing CodeGnerate on 2018-03-07T16:55:09+08:00 .
*/
@Transactional
@Service("weixinUserService")
public class WeixinUserService {

    @Resource
    private WeixinUserJpaRepository weixinUserJpaRepository;


    @Transactional
    public WeixinUser create(WeixinUser weixinUser) {

        WeixinUser createWeixinUser = new WeixinUser();

        createWeixinUser.setSubscribe(weixinUser.getSubscribe());
        createWeixinUser.setOpenid(weixinUser.getOpenid());
        createWeixinUser.setNickname(weixinUser.getNickname());
        createWeixinUser.setNicknameEmoji(weixinUser.getNicknameEmoji());
        createWeixinUser.setSex(weixinUser.getSex());
        createWeixinUser.setLanguage(weixinUser.getLanguage());
        createWeixinUser.setCity(weixinUser.getCity());
        createWeixinUser.setProvince(weixinUser.getProvince());
        createWeixinUser.setCountry(weixinUser.getCountry());
        createWeixinUser.setHeadimgurl(weixinUser.getHeadimgurl());
        createWeixinUser.setSubscribeTime(weixinUser.getSubscribeTime());
        createWeixinUser.setPrivilege(weixinUser.getPrivilege());
        createWeixinUser.setUnionid(weixinUser.getUnionid());
        createWeixinUser.setGroupid(weixinUser.getGroupid());
        createWeixinUser.setRemark(weixinUser.getRemark());
        createWeixinUser.setTagidList(weixinUser.getTagidList());
        createWeixinUser.setWeixinCode(weixinUser.getWeixinCode());

        createWeixinUser = weixinUserJpaRepository.save(createWeixinUser);


        return createWeixinUser;
    }

    @Transactional
    public void destory(WeixinUser weixinUser) {
        WeixinUser destoryWeixinUser = weixinUserJpaRepository.findById(weixinUser.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.DESTORY_TARGET_NONEXISTENT
                .generateException("微信用户 [ {0} ] 无法删除 因为无此记录 可能已经被删除", weixinUser.getId()));
        weixinUserJpaRepository.delete(destoryWeixinUser);
    }


    @Transactional
    public WeixinUser update(WeixinUser weixinUser) {
        WeixinUser updateWeixinUser = weixinUserJpaRepository.findById(weixinUser.getId())
            .orElseThrow(() -> LaputaWebOperationException.ExceptionEnum.UPDATE_TARGET_NONEXISTENT
                .generateException("微信用户 [ {0} ] 无法编辑 因为无此记录 可能已经被删除", weixinUser.getId()));

        updateWeixinUser.setSubscribe(weixinUser.getSubscribe());
        updateWeixinUser.setOpenid(weixinUser.getOpenid());
        updateWeixinUser.setNickname(weixinUser.getNickname());
        updateWeixinUser.setNicknameEmoji(weixinUser.getNicknameEmoji());
        updateWeixinUser.setSex(weixinUser.getSex());
        updateWeixinUser.setLanguage(weixinUser.getLanguage());
        updateWeixinUser.setCity(weixinUser.getCity());
        updateWeixinUser.setProvince(weixinUser.getProvince());
        updateWeixinUser.setCountry(weixinUser.getCountry());
        updateWeixinUser.setHeadimgurl(weixinUser.getHeadimgurl());
        updateWeixinUser.setSubscribeTime(weixinUser.getSubscribeTime());
        updateWeixinUser.setPrivilege(weixinUser.getPrivilege());
        updateWeixinUser.setUnionid(weixinUser.getUnionid());
        updateWeixinUser.setGroupid(weixinUser.getGroupid());
        updateWeixinUser.setRemark(weixinUser.getRemark());
        updateWeixinUser.setTagidList(weixinUser.getTagidList());
        updateWeixinUser.setWeixinCode(weixinUser.getWeixinCode());

        weixinUserJpaRepository.save(updateWeixinUser);


        return updateWeixinUser;
    }


    @Transactional
    public List<WeixinUser> read() {
        List<WeixinUser> weixinUserList = weixinUserJpaRepository.findAll();
        return weixinUserList;
    }

    @Transactional
    public List<WeixinUser> readEager() {
        List<WeixinUser> weixinUserList = weixinUserJpaRepository.findAll();
        if( weixinUserList != null && weixinUserList.size() > 0 ){
            for( WeixinUser weixinUser :  weixinUserList ){
            }
        }
        return weixinUserList;
    }


    @Transactional
    public Page<WeixinUser> readDataSource(DataSourceRequest dataSourceRequest) {
        LaputaKendoSpecification<WeixinUser> laputaKendoSpecification = new LaputaKendoSpecification<>(dataSourceRequest);
        Page<WeixinUser> weixinUserPage = weixinUserJpaRepository.findAll(laputaKendoSpecification, laputaKendoSpecification.pageable());
        if (weixinUserPage != null && weixinUserPage.getContent() != null && weixinUserPage.getContent().size() > 0) {
            for (WeixinUser weixinUser : weixinUserPage.getContent()) {
            }
        }
        return weixinUserPage;
    }



}
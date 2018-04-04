package com.laputa.foundation.web.generate.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.laputa.foundation.web.generate.entity.SysField;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
public interface SysFieldRepository extends CrudRepository<SysField, Long> {
    SysField findBySysEntityIdAndFieldName(Long sysEntityId, String fieldName);

    List<SysField> findBySysEntityId(Long sysEntityId);
}

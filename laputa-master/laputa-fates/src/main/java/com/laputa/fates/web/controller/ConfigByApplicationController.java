package com.laputa.fates.web.controller;

import com.laputa.fates.web.converter.LaputaConfigBeanCopier;
import com.laputa.fates.web.converter.LaputaConfigHistoryBeanCopier;
import com.laputa.fates.web.entity.LaputaAplication;
import com.laputa.fates.web.entity.LaputaConfig;
import com.laputa.fates.web.entity.LaputaConfigHistory;
import com.laputa.fates.web.model.LaputaConfigHistoryModel;
import com.laputa.fates.web.model.LaputaConfigModel;
import com.laputa.fates.web.model.LaputaConfigUpdateDTO;
import com.laputa.fates.web.service.ConfigByApplicationService;
import com.laputa.fates.web.service.LaputaConfigService;
import com.laputa.foundation.configurer.LaputaConfigurer;
import com.laputa.foundation.web.kendo.model.DataSourceRequest;
import com.laputa.foundation.web.kendo.model.DataSourceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;


/**
 * <p>
 * Laputa项目 Controller<br>
 * /p>
 * Created by JiangDongPing CodeGnerate on 2018-04-06T10:15:10+08:00 .
 */
@Controller
@RequestMapping("/web/laputaconfig/{laputaAplicationCode}")
public class ConfigByApplicationController {

    private static Logger logger = LoggerFactory.getLogger(ConfigByApplicationController.class);

    @Resource
    private LaputaConfigService laputaConfigService;

    @Resource
    private ConfigByApplicationService configByApplicationService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String laputaAplicationList(@PathVariable(value = "laputaAplicationCode") String laputaAplicationCode, Model model) {

        model.addAttribute("laputaAplication", configByApplicationService.getLaputaAplicationBylaputaAplicationCode(laputaAplicationCode));

        return "/web/laputaconfig/configByApplication";
    }

    @ResponseBody
    @RequestMapping(value = "/readDataSource", method = RequestMethod.POST)
    public DataSourceResult readDataSource(@PathVariable(value = "laputaAplicationCode") String laputaAplicationCode, @RequestBody DataSourceRequest dataSourceRequest) {
        Page<LaputaConfig> laputaConfigPage = configByApplicationService.readDataSource(laputaAplicationCode, dataSourceRequest);
        List<LaputaConfigModel> laputaConfigModelList = LaputaConfigBeanCopier.laputaConfigEntityToLaputaConfigModelEager(laputaConfigPage.getContent());

        try {
            Properties properties = LaputaConfigurer.getInstance().getCloudConfigProperties(laputaAplicationCode);
            if (!CollectionUtils.isEmpty(laputaConfigModelList)) {
                for (LaputaConfigModel laputaConfigModel : laputaConfigModelList) {
                    laputaConfigModel.setCloudValue((String) properties.get(laputaConfigModel.getCode()));

                }
            }
        } catch (Exception e) {
            logger.error("配置中心异常{}", e);
        }

        DataSourceResult dataSourceResult = new DataSourceResult(laputaConfigModelList, laputaConfigPage.getTotalElements());
        return dataSourceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/readHistoryDataSource/{laputaConfigId}", method = RequestMethod.POST)
    public DataSourceResult readHistoryDataSource(@PathVariable(value = "laputaAplicationCode") String laputaAplicationCode,
                                                  @PathVariable(value = "laputaConfigId") Long laputaConfigId,
                                                  @RequestBody DataSourceRequest dataSourceRequest) {
        Page<LaputaConfigHistory> laputaConfigPage = configByApplicationService.readHistoryDataSource(laputaAplicationCode, laputaConfigId, dataSourceRequest);
        List<LaputaConfigHistoryModel> laputaConfigHistoryModelList = LaputaConfigHistoryBeanCopier.laputaConfigHistoryEntityToLaputaConfigHistoryModelEager(laputaConfigPage.getContent());
        DataSourceResult dataSourceResult = new DataSourceResult(laputaConfigHistoryModelList, laputaConfigPage.getTotalElements());
        return dataSourceResult;
    }


    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public LaputaConfigModel create(@PathVariable(value = "laputaAplicationCode") String laputaAplicationCode, @RequestBody LaputaConfigModel laputaConfigModel) {
        LaputaConfig laputaConfig = LaputaConfigBeanCopier.laputaConfigModelToLaputaConfigEntity(laputaConfigModel);
        LaputaConfig createLaputaConfig = configByApplicationService.create(laputaAplicationCode, laputaConfig);
        laputaConfigModel.setId(createLaputaConfig.getId());

        try {
            LaputaConfigurer.getInstance().setCloudConfigProperties(laputaAplicationCode, laputaConfig.getCode(), laputaConfig.getConfigValue());
        } catch (Exception e) {
            logger.error("新增同步失败{}", e);
        }

        return laputaConfigModel;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public LaputaConfigUpdateDTO update(@PathVariable(value = "laputaAplicationCode") String laputaAplicationCode, @RequestBody LaputaConfigUpdateDTO laputaConfigUpdateDTO) {

        LaputaConfig laputaConfig = configByApplicationService.update(laputaAplicationCode,
                laputaConfigUpdateDTO.getId(),
                laputaConfigUpdateDTO.getConfigValue(),
                laputaConfigUpdateDTO.getCauseDescript());

        try {
            LaputaConfigurer.getInstance().setCloudConfigProperties(laputaAplicationCode, laputaConfig.getCode(), laputaConfig.getConfigValue());
        } catch (Exception e) {
            logger.error("更新同步失败{}", e);
        }

        return laputaConfigUpdateDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/syn", method = RequestMethod.POST)
    public LaputaConfigModel syn(@PathVariable(value = "laputaAplicationCode") String laputaAplicationCode, @RequestBody LaputaConfigModel laputaConfigModel) throws Exception {

        LaputaAplication laputaAplication = configByApplicationService.getLaputaAplicationBylaputaAplicationCode(laputaAplicationCode);
        LaputaConfigurer.getInstance().setCloudConfigProperties(laputaAplication.getCode(), laputaConfigModel.getCode(), laputaConfigModel.getConfigValue());

        return laputaConfigModel;
    }

}
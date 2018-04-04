package com.laputa.foundation.web.ueditor.controller;

import com.laputa.foundation.web.ueditor.api.dto.UeditorExFileDTO;
import com.laputa.foundation.web.ueditor.service.DefaultLaputaUeditorExFileService;
import com.laputa.foundation.web.ueditor.vo.UeditorConfigVO;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangdongping on 2017/12/26 0026.
 */
@Controller
@RequestMapping("/ueditor/service")
public class UeditorController {

    private static final Logger logger = LoggerFactory.getLogger(UeditorController.class);

    private static UeditorConfigVO ueditorConfigVO;

    @Autowired
    private DefaultLaputaUeditorExFileService defaultLaputaUeditorExFileService;

    static {
        ueditorConfigVO = new UeditorConfigVO();

        ueditorConfigVO.setImageActionName("uploadimage");
        ueditorConfigVO.setImageFieldName("upfile");
        ueditorConfigVO.setImageMaxSize(2048000);
        ueditorConfigVO.setImageAllowFiles(Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"));
        ueditorConfigVO.setImageCompressEnable(Boolean.FALSE);
        ueditorConfigVO.setImageCompressBorder(1600);
        ueditorConfigVO.setImageInsertAlign("none");
        ueditorConfigVO.setImageUrlPrefix("");
        ueditorConfigVO.setImagePathFormat("/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
    }


    @ResponseBody
    @RequestMapping("/config")
    public Object config(HttpServletRequest request, @RequestParam("action") String action) throws IOException {

        if ("config".equalsIgnoreCase(action)) {
            return ueditorConfigVO;
        } else if ("uploadimage".equalsIgnoreCase(action)) {
            logger.info("开始上传图片");
            if (request instanceof MultipartHttpServletRequest) {

                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                MultipartFile f = multipartHttpServletRequest.getFile("upfile");

                UeditorExFileDTO ueditorExFileDTO =
                        defaultLaputaUeditorExFileService.saveFile(f.getOriginalFilename(),
                                IOUtils.toByteArray(f.getInputStream()));

                Map<String, String> result = new HashMap<>();
                result.put("original", ueditorExFileDTO.getOriginalFileName());
                result.put("name", ueditorExFileDTO.getSaveInDiskFileName());
                result.put("url", ueditorExFileDTO.getUrl());
                result.put("state", "SUCCESS");
                result.put("type", ueditorExFileDTO.getType());
                return result;

                /*
                String savePath = request.getServletContext().getRealPath("/static/ueditor/up");
                String fileName = UUID.randomUUID().toString() + f.getOriginalFilename().lastIndexOf(".");
                File file = new File(savePath + "\\" + fileName);
                byte[] data = IOUtils.toByteArray(f.getInputStream());
                FileUtils.writeByteArrayToFile(file, data);
                //FileUtils.write(file,"asdasdas");

                Map<String, String> result = new HashMap<>();
                result.put("original", fileName);
                result.put("name", fileName);
                result.put("url", "http://127.0.0.1:9000/ue/static/ueditor/up/" + fileName);
                result.put("size", "" + data.length);
                result.put("state", "SUCCESS");
                result.put("type", ".png");
                //result.put("transid", ContextUtil.getTransId());

                return result;
                */

            } else {
                logger.warn("不正确的请求类型 {}", request.getClass().getName());
            }
        }
        return null;
    }

    @RequestMapping("/justdev")
    public String justdev() {

        return "/ueditor/justdev";
    }
}

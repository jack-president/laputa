package com.laputa.foundation.web.ueditor.api;

import com.laputa.foundation.web.ueditor.api.dto.ListExFileDTO;
import com.laputa.foundation.web.ueditor.api.dto.UeditorExFileDTO;

/**
 * 附件存储相关接口
 * 不建议使用 rpc
 * Created by jiangdongping on 2017/12/28 0028.
 */
public interface LaputaUeditorExFileService {

    UeditorExFileDTO saveFile(String originalFileName, byte[] fileData);

    ListExFileDTO queryExFilePage(Integer start, Integer size);
}

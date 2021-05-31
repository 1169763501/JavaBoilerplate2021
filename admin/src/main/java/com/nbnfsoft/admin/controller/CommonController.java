package com.nbnfsoft.admin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.nbnfsoft.admin.domain.model.JsonData;
import com.nbnfsoft.admin.domain.output.AttachmentOutput;
import com.nbnfsoft.admin.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author:zhuli
 * @Description：通用基础
 * @Date:Create in 17:25 2019-11-15
 */
@RestController
@Api(tags = "通用基础")
@RequestMapping("/common")
@ApiSort(2)
public class CommonController extends BaseController {
    @Autowired
    private CommonService commonService;

    @PostMapping(value = "/upload")
    @ApiOperation(value = "附件[上传]-OK")
    @ResponseBody
    public JsonData<AttachmentOutput> upload(@RequestParam("file") MultipartFile uploadFile) {
        return success(commonService.upload(uploadFile));
    }

}

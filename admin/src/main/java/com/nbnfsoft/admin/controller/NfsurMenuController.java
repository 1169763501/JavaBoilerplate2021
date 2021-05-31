package com.nbnfsoft.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.nbnfsoft.admin.domain.dto.MeunDto;
import com.nbnfsoft.admin.domain.input.IdInput;
import com.nbnfsoft.admin.domain.input.MeunListInput;
import com.nbnfsoft.admin.domain.model.JsonData;
import com.nbnfsoft.admin.domain.output.IdOutput;
import com.nbnfsoft.admin.service.NfsurMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 目录表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-05-06
 */
@RestController
@RequestMapping("/nfsurmenu")
public class NfsurMenuController extends BaseController{

    @Autowired
    NfsurMenuService nfsurMenuService;

    @ApiOperation(value = "列表")
    @GetMapping(value = "/list")
    public JsonData<List<MeunDto>> list(@Validated MeunListInput input) {
        return success(nfsurMenuService.list(input));
    }


    @ApiOperation(value = "查")
    @GetMapping(value = "/detail")
    public JsonData<MeunDto> detail(@Validated long meun_id) {

        return success(nfsurMenuService.detail(meun_id));
    }

    @ApiOperation(value = "增改")
    @PostMapping(value = "/createorupdate")
    public JsonData<IdOutput> createorupdate(@RequestBody @Validated MeunDto input) {
        return success(new IdOutput(nfsurMenuService.createOrUpdate(input)));
    }

    @ApiOperation(value = "删")
    @PostMapping(value = "/del")
    public JsonData<IdOutput> del(@RequestBody @Validated IdInput input) {

        nfsurMenuService.del(input.id);
        return success();
    }


}


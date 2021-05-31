package com.nbnfsoft.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nbnfsoft.admin.domain.model.JsonData;
import com.nbnfsoft.admin.framework.web.page.TableDataInfo;

public abstract class BaseController {
    public JsonData success(IPage page) {
        TableDataInfo dataInfo = new TableDataInfo();
        dataInfo.setTotalCount(page.getTotal());
        dataInfo.setList(page.getRecords());
        dataInfo.setCurrentPage(page.getCurrent());
        dataInfo.setPerPage(page.getSize());
        dataInfo.setPageCount(page.getPages());
        return success(dataInfo);
    }

    public JsonData success() {
        return new JsonData(true, null, null);
    }

    public JsonData success(Object data) {
        return new JsonData(true, data, null);
    }

    public JsonData success(Object data, String msg) {
        return new JsonData(true, data, msg);
    }

    public JsonData success(Object data, boolean success) {
        return new JsonData(success, data, null);
    }

    public JsonData failure(String msg) {
        return new JsonData(false, null, msg);
    }

    public JsonData failure(String msg, boolean success) {
        return new JsonData(success, null, msg);
    }

    public JsonData failure(String msg, Object data) {
        return new JsonData(false, data, msg);
    }
}

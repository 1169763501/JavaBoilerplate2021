package com.nbnfsoft.admin.domain.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author:louyi
 * @Description:实现数据权限过滤需要继承此类
 * @Date:Create in 13:36 2020-01-07
 */
public class BaseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据权限
     */
    @ApiModelProperty(value = "数据范围", hidden = true)
    private String dataScope;

    @ApiModelProperty(value = "是否验证权限Y/N,默认Y。", dataType = "String")
    private String data_control;

    public String getData_control() {
        return data_control;
    }

    public void setData_control(String data_control) {
        this.data_control = data_control;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }
}

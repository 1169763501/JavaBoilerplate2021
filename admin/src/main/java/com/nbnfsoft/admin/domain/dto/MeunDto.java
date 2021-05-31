package com.nbnfsoft.admin.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class MeunDto {

    public long meun_id;

    public Long parent_id;

    public String name;

    public Date create_date;

    public Date last_update_date;

    @ApiModelProperty(value = "0,启用，-1停用")
    public long status;

}
package com.nbnfsoft.admin.domain.input;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 10:01 2020-12-18
 */
public class IdInput {

    @ApiModelProperty(value = "唯一标识")
    @JsonProperty("id")
    @NotNull(message = "id不能为空")
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

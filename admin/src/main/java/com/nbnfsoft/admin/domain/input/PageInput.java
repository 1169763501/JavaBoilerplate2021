package com.nbnfsoft.admin.domain.input;

import com.nbnfsoft.admin.domain.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 14:19 2019-11-19
 */
public class PageInput extends BaseDto {
    @ApiModelProperty(value = "当前开始页", dataType = "Long")
    private Long current_page;
    @ApiModelProperty(value = "每页显示数量", dataType = "Long")
    private Long per_page;

    public Long getCurrent_page() {
        if (current_page == null || current_page == 0) {
            current_page = 1L;
        }
        return current_page;
    }

    public void setCurrent_page(Long current_page) {
        this.current_page = current_page;
    }

    public Long getPer_page() {
        if (per_page == null || per_page == 0) {
            per_page = 10L;
        }
        return per_page;
    }

    public void setPer_page(Long per_page) {
        this.per_page = per_page;
    }
}

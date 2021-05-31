package com.nbnfsoft.admin.framework.web.page;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * 表格分页数据对象
 *
 * @author louyi
 */
public class TableDataInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 列表数据
     */
    private List<T> list;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    @ApiModelProperty("当前页")
    @JsonProperty("current_page")
    private Long currentPage;
    @ApiModelProperty("每页大小")
    @JsonProperty("per_page")
    private Long perPage;
    @ApiModelProperty("总页数")
    @JsonProperty("page_count")
    private Long pageCount;
    @ApiModelProperty("总条数")
    @JsonProperty("total_count")
    private Long totalCount;

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPerPage() {
        return perPage;
    }

    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
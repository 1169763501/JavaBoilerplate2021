package com.nbnfsoft.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.KeySequence;
/**
 * <p>
 * 目录表
 * </p>
 *
 * @author ${author}
 * @since 2021-05-06
 */
@TableName("NFSUR_MENU")
@ApiModel(value="NfsurMenu对象", description="目录表")
@KeySequence("NFSUR_MENU_S")
public class NfsurMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId("ID")
    public Long id;

    @TableField("NAME")
    public String name;

    @TableField("PARENT_ID")
    public Long parent_id;

    @TableField("CREATE_ID")
    public Long create_id;

    @TableField("CREATE_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    public Date create_date;

    @TableField("LAST_UPDATE_ID")
    public Long last_update_id;

    @TableField("LAST_UPDATE_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    public Date last_update_date;

    @ApiModelProperty(value = "0,启用，-1停用")
    @TableField("STATUS")
    public Long status;

    @TableField("ORG_ID")
    public Long org_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getCreate_id() {
        return create_id;
    }

    public void setCreate_id(Long create_id) {
        this.create_id = create_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getLast_update_id() {
        return last_update_id;
    }

    public void setLast_update_id(Long last_update_id) {
        this.last_update_id = last_update_id;
    }

    public Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Long org_id) {
        this.org_id = org_id;
    }
}

package com.nbnfsoft.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.nbnfsoft.admin.common.constant.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 系统参数
 * </p>
 *
 * @author louyi
 * @since 2019-11-15
 */
@TableName("NFSYS_PARAMETERS")
@ApiModel(value = "NFSYS_PARAMETERS对象", description = "系统参数")
@KeySequence("NFSYS_PARAMETERS_S")
public class SysParameters implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "机构ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "应用编码")
    @TableField("app_code")
    private String appCode;

    @ApiModelProperty(value = "参数代码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "参数值")
    @TableField("VALUE")
    private String value;

    @ApiModelProperty(value = "使用标识，1：前端使用 0：前端不使用")
    @TableField("flag")
    private Long flag;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "创建用户ID")
    @TableField("CREATE_ID")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    @JsonFormat(pattern = Constants.yyyy_MM_dd_HH_mm_ss, locale = "zh", timezone = "GMT+8")
    private Date createDate;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SysParameters{" +
                "ID=" + id +
                ", ORG_ID=" + orgId +
                ", CODE=" + code +
                ", VALUE=" + value +
                ", DESCRIPTION=" + description +
                ", CREATE_ID=" + createId +
                ", CREATE_DATE=" + createDate +
                "}";
    }
}

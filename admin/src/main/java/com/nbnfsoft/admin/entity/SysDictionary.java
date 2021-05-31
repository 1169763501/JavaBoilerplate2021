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
 * 通用字典表
 * </p>
 *
 * @author louyi
 * @since 2019-11-15
 */
@TableName("NFSYS_DICTIONARY")
@ApiModel(value = "NFSYS_DICTIONARY对象", description = "通用字典表")
@KeySequence("NFSYS_DICTIONARY_S")
public class SysDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "机构ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "分类类型")
    @TableField("DIC_TYPE")
    private String dicType;

    @ApiModelProperty(value = "分类代码")
    @TableField("DIC_CODE")
    private String dicCode;

    @ApiModelProperty(value = "分类名称")
    @TableField("DIC_NAME")
    private String dicName;

    @ApiModelProperty(value = "排序号")
    @TableField("SEQ")
    private Long seq;

    @ApiModelProperty(value = "首拼码")
    @TableField("INPUTCODE1")
    private String inputcode1;

    @ApiModelProperty(value = "五笔码")
    @TableField("INPUTCODE2")
    private String inputcode2;

    @ApiModelProperty(value = "创建用户ID")
    @TableField("CREATE_ID")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    @JsonFormat(pattern = Constants.yyyy_MM_dd_HH_mm_ss, locale = "zh", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "禁用状态 1启用 99禁用")
    @TableField("FORBID")
    private Long forbid;

    public Long getForbid() {
        return forbid;
    }

    public void setForbid(Long status) {
        this.forbid = status;
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

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getInputcode1() {
        return inputcode1;
    }

    public void setInputcode1(String inputcode1) {
        this.inputcode1 = inputcode1;
    }

    public String getInputcode2() {
        return inputcode2;
    }

    public void setInputcode2(String inputcode2) {
        this.inputcode2 = inputcode2;
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

    @Override
    public String toString() {
        return "SysDictionary{" +
                "ID=" + id +
                ", ORG_ID=" + orgId +
                ", DIC_TYPE=" + dicType +
                ", DIC_CODE=" + dicCode +
                ", DIC_NAME=" + dicName +
                ", SEQ=" + seq +
                ", INPUTCODE1=" + inputcode1 +
                ", INPUTCODE2=" + inputcode2 +
                ", CREATE_ID=" + createId +
                ", CREATE_DATE=" + createDate +
                ", FORBID=" + forbid +
                "}";
    }
}

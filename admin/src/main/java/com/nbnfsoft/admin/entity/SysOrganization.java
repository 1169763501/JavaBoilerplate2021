package com.nbnfsoft.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 机构
 * </p>
 *
 * @author louyi
 * @since 2019-11-15
 */
@TableName("NFSYS_ORGANIZATION")
@ApiModel(value = "NFSYS_ORGANIZATION对象", description = "机构")
@KeySequence("NFSYS_ORGANIZATION_S")
public class SysOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "机构级别，1市卫生局/2区卫生局/3综合医院/4专科医院（区级医院）/5服务中心/6服务站点")
    @TableField("ORG_LEVEL")
    private String orgLevel;

    @ApiModelProperty(value = "机构代码")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "机构名称")
    @TableField("ORG_NAME")
    private String orgName;

    @ApiModelProperty(value = "appKey")
    @TableField("APP_KEY")
    private String appKey;

    @ApiModelProperty(value = "appSecret")
    @TableField("APP_SECRET")
    private String appSecret;

    @ApiModelProperty(value = "type 0,本地，1浙订，2，钉钉")
    @TableField("type")
    private Long type;

    @ApiModelProperty(value = "corpid")
    @TableField("CORPID")
    private String corpid;

    @ApiModelProperty(value = "agentid")
    @TableField("AGENTID")
    private String agentid;

    @ApiModelProperty(value = "abbr机构缩写前缀")
    @TableField("abbr")
    private String abbr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
}

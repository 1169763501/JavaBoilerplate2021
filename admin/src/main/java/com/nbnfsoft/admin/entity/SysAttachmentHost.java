package com.nbnfsoft.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 附件宿主
 * </p>
 *
 * @author louyi
 * @since 2019-11-15
 */
@TableName("NFSYS_ATTACHMENT_HOST")
@ApiModel(value="NFSYS_ATTACHMENT_HOST对象", description="附件宿主")
@KeySequence("NFSYS_ATTACHMENT_HOST_S")
public class SysAttachmentHost implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "宿主类型  在 Core/Models/Enums 中定义")
    @TableField("HOST_TYPE")
    private Long hostType;

    @ApiModelProperty(value = "宿主单据id")
    @TableField("HOST_ID")
    private Long hostId;

    @ApiModelProperty(value = "附件id")
    @TableField("ATTACH_ID")
    private Long attachId;

    @ApiModelProperty(value = "创建用户ID")
    @TableField("CREATE_ID")
    private Long createId;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createDate;

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

    public Long getHostType() {
        return hostType;
    }

    public void setHostType(Long hostType) {
        this.hostType = hostType;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
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
}

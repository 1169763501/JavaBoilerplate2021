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
 * 附件表
 * </p>
 *
 * @author louyi
 * @since 2019-11-15
 */
@TableName("NFSYS_ATTACHMENT")
@ApiModel(value = "NFSYS_ATTACHMENT对象", description = "附件表")
@KeySequence("NFSYS_ATTACHMENT_S")
public class SysAttachment implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    @TableField("FILE_PATH")
    private String filePath;

    @ApiModelProperty(value = "缩略图路径")
    @TableField("THUMB_PATH")
    private String thumbPath;

    @ApiModelProperty(value = "文件后缀名")
    @TableField("FILE_SUFFIX")
    private String fileSuffix;

    @ApiModelProperty(value = "文件大小")
    @TableField("FILE_SIZE")
    private Double fileSize;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
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

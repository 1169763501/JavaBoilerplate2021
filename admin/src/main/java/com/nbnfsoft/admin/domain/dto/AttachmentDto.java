package com.nbnfsoft.admin.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbnfsoft.admin.common.constant.Constants;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description: 附件传输类
 * <p>
 * Package com.nbnfsoft.hrp.domain.dto
 *
 * @author SailHe
 * @date 2019/12/27 14:43
 */
public class AttachmentDto {
    @ApiModelProperty(value = "主键")
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(value = "机构ID")
    @JsonProperty("org_id")
    private Long orgId;

    @ApiModelProperty(value = "文件名称")
    @JsonProperty("file_name")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    @JsonProperty("file_path")
    private String filePath;

    @ApiModelProperty(value = "文件后缀名")
    @JsonProperty("file_suffix")
    private String fileSuffix;

    @ApiModelProperty(value = "文件大小")
    @JsonProperty("file_size")
    private Double fileSize;

    @ApiModelProperty(value = "创建时间")
    @JsonProperty("create_date")
    @JsonFormat(pattern = Constants.yyyy_MM_dd_HH_mm_ss, locale = "zh", timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty(value = "缩略图文件路径")
    @JsonProperty("thumb_path")
    private String thumbPath;

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

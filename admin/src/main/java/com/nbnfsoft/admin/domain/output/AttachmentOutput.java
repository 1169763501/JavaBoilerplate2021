package com.nbnfsoft.admin.domain.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class AttachmentOutput {
    @ApiModelProperty("附件信息主键")
    @JsonProperty("attach_id")
    private Long attachId;

    @ApiModelProperty("附件信息Url")
    @JsonProperty("attach_url")
    private String attachUrl;

    @ApiModelProperty("附件信息名称")
    @JsonProperty("attach_name")
    private String attachName;

    @ApiModelProperty(value = "文件后缀名")
    @JsonProperty("file_suffix")
    private String fileSuffix;

    @ApiModelProperty(value = "文件大小")
    @JsonProperty("file_size")
    private Double fileSize;

    @ApiModelProperty(value = "缩略图文件路径")
    @JsonProperty("thumb_path")
    private String thumbPath;

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
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
}

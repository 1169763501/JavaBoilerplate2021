package com.nbnfsoft.admin.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 定时任务表
 *
 * @Author:louyi
 * @Description：
 * @Date:Create in 16:31 2020-12-28
 */
@TableName("NFSYS_JOB")
@ApiModel(value = "NFSYS_JOB对象", description = "后台工作")
@KeySequence("NFSYS_JOB_S")
public class SysJob {
    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "工作类型")
    @TableField("JOB_TYPE")
    private String jobType;

    @ApiModelProperty(value = "工作参数,JSON格式")
    @TableField("JOB_ARGS")
    private String jobArgs;

    @ApiModelProperty(value = "失败后尝试次数")
    @TableField("TRY_COUNT")
    private Long tryCount;

    @ApiModelProperty(value = "下一次尝试时间")
    @TableField("NEXT_TRYTIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date nextTrytime;

    @ApiModelProperty(value = "最后尝试时间")
    @TableField("LAST_TRYTIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date lastTrytime;

    @ApiModelProperty(value = "废弃否，Y/N")
    @TableField("IS_ABANDONED")
    private String isAbandoned;

    @ApiModelProperty(value = "优先级，值越大越高")
    @TableField("PRIORITY")
    private Long priority;

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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobArgs() {
        return jobArgs;
    }

    public void setJobArgs(String jobArgs) {
        this.jobArgs = jobArgs;
    }

    public Long getTryCount() {
        return tryCount;
    }

    public void setTryCount(Long tryCount) {
        this.tryCount = tryCount;
    }

    public Date getNextTrytime() {
        return nextTrytime;
    }

    public void setNextTrytime(Date nextTrytime) {
        this.nextTrytime = nextTrytime;
    }

    public Date getLastTrytime() {
        return lastTrytime;
    }

    public void setLastTrytime(Date lastTrytime) {
        this.lastTrytime = lastTrytime;
    }

    public String getIsAbandoned() {
        return isAbandoned;
    }

    public void setIsAbandoned(String isAbandoned) {
        this.isAbandoned = isAbandoned;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

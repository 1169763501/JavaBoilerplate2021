package com.nbnfsoft.admin.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author:louyi
 * @Description：医院列表
 * @Date:Create in 9:23 2020-12-14
 */
public class HospitalDto {
    @ApiModelProperty("唯一标识")
    @JsonProperty("id")
    private Long id;

    @ApiModelProperty("医院编码")
    @JsonProperty("hospital_code")
    private String hospitalCode;

    @ApiModelProperty("医院名称")
    @JsonProperty("hospital_name")
    private String hospitalName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}

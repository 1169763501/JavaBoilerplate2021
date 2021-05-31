package com.nbnfsoft.admin.framework.security;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbnfsoft.admin.domain.dto.HospitalDto;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录用户身份权限
 *
 * @author louyi
 */
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    /**
     * 用户唯一标识
     */
    private String token;
    /**
     * 登陆时间
     */
    private Long loginTime;
    /**
     * 过期时间
     */
    private Long expireTime;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户工号
     */
    private String empCode;
    /**
     * 用户名称
     */
    private String empName;
    /**
     * 密码
     */
    private String password;
    /**
     * 机构id
     */
    private Long orgId;
    /**
     * 医院id
     */
    private Long hospitalId;
    /**
     * openId
     */
    private String openId;
    /**
     * 机构名称
     */
    private String orgName;
    /**
     * 内置管理员
     */
    private String sysAdmin;

    @ApiModelProperty("医院列表")
    @JsonProperty("hospitals")
    private List<HospitalDto> hospitals;

    public String getSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(String sysAdmin) {
        this.sysAdmin = sysAdmin;
    }

    public List<HospitalDto> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<HospitalDto> hospitals) {
        this.hospitals = hospitals;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUser() {
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

}

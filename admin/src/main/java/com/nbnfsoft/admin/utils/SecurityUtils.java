package com.nbnfsoft.admin.utils;

import com.nbnfsoft.admin.common.constant.HttpStatus;
import com.nbnfsoft.admin.config.NFUPAuthenticationToken;
import com.nbnfsoft.admin.framework.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全服务工具类
 *
 * @author louyi
 */
public class SecurityUtils {
    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new FriendlyException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * 获取用户账户
     **/
    public static Long getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            throw new FriendlyException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new FriendlyException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }
    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static void hostSideOn()
    {
        ((NFUPAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).hostSide=true;
    }

    public static void hostSideOff()
    {
        ((NFUPAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).hostSide=false;
    }
}

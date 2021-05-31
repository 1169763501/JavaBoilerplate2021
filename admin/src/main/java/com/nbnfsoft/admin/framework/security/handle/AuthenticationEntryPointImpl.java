package com.nbnfsoft.admin.framework.security.handle;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.nbnfsoft.admin.common.constant.HttpStatus;
import com.nbnfsoft.admin.domain.model.JsonData;
import com.nbnfsoft.admin.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 认证失败处理类 返回未授权
 * 
 * @author louyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = HttpStatus.SUCCESS;
        ServletUtils.renderString(response, code,JSON.toJSONString(new JsonData(false, code, "会话已过期请重新登录")));
    }
}

package com.nbnfsoft.admin.framework.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.nbnfsoft.admin.domain.model.JsonData;
import com.nbnfsoft.admin.utils.ServletUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 自定义退出处理类 返回成功
 * spring security在实现注销功能时,流程如下
 1. 使得HTTP session失效(如果invalidate-session属性被设置为true);
 2. 清除SecurityContex(真正使得用户退出)
 3. 将页面重定向至logout-success-url指明的URL。
 * @author louyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        ServletUtils.renderString(response, JSON.toJSONString(new JsonData(true, null, "退出成功")));
    }
}

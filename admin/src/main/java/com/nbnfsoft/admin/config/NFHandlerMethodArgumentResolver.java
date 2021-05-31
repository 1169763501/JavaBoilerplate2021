package com.nbnfsoft.admin.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;

public class NFHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
       return methodParameter.getParameterType().toString().contains("com.nbnfsoft");
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        Iterator<String> parameterNames = nativeWebRequest.getParameterNames();

        JSONObject object=new JSONObject();

        while (parameterNames.hasNext())
        {
            String name = parameterNames.next();
            String value = nativeWebRequest.getParameter(name);
            if (!value.equals("null"))
                object.put(name, value);
        }

        String json=object.toString();
        Object o = JSONObject.parseObject(json,methodParameter.getParameterType());

        return o;
    }
}

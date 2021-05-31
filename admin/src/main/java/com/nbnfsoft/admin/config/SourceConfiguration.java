package com.nbnfsoft.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import java.util.List;

/**
 * Description: 配置静态资源映射
 * <p>
 * Package com.nbnfsoft.admin.common
 *
 * @author SailHe
 * @date 2019/12/19 15:25
 */
@Configuration
public class SourceConfiguration implements WebMvcConfigurer {

    @Value("${uploadFile.resourceHandler}")
    private String resourceHandler;

    @Value("${uploadFile.location}")
    private String location;

    /**
     * 配置静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //就是说 url 中出现 resourceHandler 匹配时，则映射到 location 中去,location 相当于虚拟路径
        //映射本地文件时，开头必须是 file:/// 开头，表示协议
        registry.addResourceHandler(resourceHandler).addResourceLocations("file:///" + location);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new NFHandlerMethodArgumentResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter=new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        //规则赋予转换对象
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(0,fastJsonHttpMessageConverter);
    }
}

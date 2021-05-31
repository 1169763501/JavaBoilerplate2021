package com.nbnfsoft.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 9:09 2020-09-01
 */
@Configuration
public class SpringEnv {

    @Value("${spring.profiles.active}")
    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}

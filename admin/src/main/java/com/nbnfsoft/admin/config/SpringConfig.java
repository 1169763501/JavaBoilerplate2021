package com.nbnfsoft.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 10:43 2019-11-15
 */
@Configuration
@EnableOpenApi
@EnableScheduling
@EnableAsync
@ComponentScan("com.nbnfsoft.admin")
public class SpringConfig {


    

}

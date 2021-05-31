package com.nbnfsoft.admin.controller;

import ch.qos.logback.classic.LoggerContext;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.nbnfsoft.admin.framework.aspectj.lang.annotation.AnonymousAccess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author:zhuli
 * @Description：日志管理
 * @Date:Create in 17:25 2019-11-15
 */
@RestController
@Api(tags = "日志管理")
@RequestMapping("/log")
@ApiSort(2)
public class LogbackController extends BaseController {
    /**
     * logback动态修改包名的日志级别
     *
     * @param level       日志级别
     * @param packageName 包名
     * @return 当前的日志级别
     * @throws Exception
     */
    @AnonymousAccess
    @ApiOperation(value = "设置日志级别")
    @GetMapping(value = "/setLevel")
    public String updateLogbackLevel(@RequestParam(value = "level") String level,
                                     @RequestParam(value = "packageName", defaultValue = "-1") String packageName) throws Exception {
        LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();

        Logger logger;
        if (packageName.equals("-1")) {
            // 默认值-1，更改全局日志级别；否则按传递的包名或类名修改日志级别。
            logger = loggerContext.getLogger("root");

        } else {
            logger = loggerContext.getLogger(packageName);
        }
        ((ch.qos.logback.classic.Logger) logger).setLevel(ch.qos.logback.classic.Level.toLevel(level));
        return ((ch.qos.logback.classic.Logger) logger).getLevel().levelStr;
    }

    /**
     * 获取当前日志级别
     */
    @AnonymousAccess
    @ApiOperation(value = "获取日志级别")
    @GetMapping(value = "/getLevel")
    public String getLogbackLevel(@RequestParam(value = "packageName", defaultValue = "-1") String packageName) throws Exception {
        LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();

        Logger logger;
        if (packageName.equals("-1")) {
            // 默认值-1，更改全局日志级别；否则按传递的包名或类名修改日志级别。
            logger = loggerContext.getLogger("root");

        } else {
            logger = loggerContext.getLogger(packageName);
        }
        return ((ch.qos.logback.classic.Logger) logger).getLevel().levelStr;
    }

}

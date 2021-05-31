package com.nbnfsoft.admin.common.constant;

/**
 * 通用常量信息
 *
 * @author louyi
 */
public class Constants {
    public static final String REDIS_PREFIX_KEY = "n:";
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "Session,c:";

    /**
     * 登录用户 redis key
     */
    public static final String GLOBAL_PARAM_CODE = "global:param:code:";

    public static final String SPLIT = "-";

    public static final String SLASH = "/";

    public static final String THUMB = "thumb";
    /**
     * 平台管理默认code
     */
    public static final String LOCAL_APP_CODE = "admin";
    /**
     * 默认最大尝试3次
     */
    public static final Long TASK_MAX_TRY_COUNT = 3L;
    /**
     * 默认优先级
     */
    public static final Long DEFAULT_LAST_DAY = 10L;

    public static final String OA_TYPE = "NFan.OAvNext.UserData, NFan.OAvNext.Core, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String HH_mm_ss = "HH:mm:ss";
    public static final String HH_mm = "HH:mm";
    public static final String yyyy_MM_dd_HH_mm_ss = yyyy_MM_dd + " " + HH_mm_ss;
    public static final String UNDEFINED = "未知";
}

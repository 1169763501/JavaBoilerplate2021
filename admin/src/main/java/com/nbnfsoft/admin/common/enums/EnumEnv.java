package com.nbnfsoft.admin.common.enums;

/**
 * 当前环境
 *
 * @author louyi
 */
public enum EnumEnv {
    TEST("test"), PRO("pro");

    private final String code;

    EnumEnv(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}

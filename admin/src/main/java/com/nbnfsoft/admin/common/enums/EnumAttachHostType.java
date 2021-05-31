package com.nbnfsoft.admin.common.enums;

/**
 * 组织类型
 *
 * @author louyi
 */
public enum EnumAttachHostType {
    EMP_ICON(0L, "用户头像");

    private final Long type;
    private final String code;

    EnumAttachHostType(Long type, String code) {
        this.code = code;
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    public String getCode() {
        return code;
    }
}

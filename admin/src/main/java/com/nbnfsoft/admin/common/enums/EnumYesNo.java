package com.nbnfsoft.admin.common.enums;

import java.util.Arrays;

import com.nbnfsoft.admin.common.constant.Constants;
import com.nbnfsoft.admin.utils.CommonUtil;

/**
 * Descriptions: 通用{是；否}状态<p>
 *
 * @author SailHe
 * @date 2020/3/13 16:49
 */
public enum EnumYesNo {
    NO(0L, "N", "否"), YES(1L, "Y", "是");

    private final Long value;
    private final String code;
    private final String info;

    EnumYesNo(Long value, String code, String info) {
        this.value = value;
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public Long getValue() {
        return value;
    }

    public static String findNameByCode(String code) {
        return CommonUtil.findField(Arrays.stream(values()), code, p -> p.getCode(), p -> p.getInfo(), Constants.UNDEFINED);
    }

    public static String findNameByValue(String valueStr) {
        return CommonUtil.findField(Arrays.stream(EnumYesNo.values()), valueStr,
                p -> p.getValue().toString(), p -> p.getInfo(), Constants.UNDEFINED);
    }
}

package com.nbnfsoft.admin.utils;

import org.springframework.util.DigestUtils;

public class SecureUtils {
    public static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
package com.nbnfsoft.admin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: liangjie
 * @Date: 2020/9/27 0027 9:19
 */
public class EncodeUtils {

    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static String encodeBySHA256(String str) {

        if (str == null) {

            return null;
        }

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());

        } catch (Exception e) {
            return "";
        }

    }

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f' };

    private static String getFormattedText(byte[] bytes) {

        int len = bytes.length;

        StringBuilder buf = new StringBuilder(len * 2);

        // 把密文转换成十六进制的字符串形式

        for (int j = 0; j < len; j++) {

            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);

            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);

        }

        return buf.toString();

    }
}

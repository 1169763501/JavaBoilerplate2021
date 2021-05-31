package com.nbnfsoft.admin.utils;

import java.math.BigDecimal;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 12:17 2019-11-22
 */
public class BigDecimalUtils {

    /**
     * BigDecimal 相加
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double add(double v1, double v2) {
        BigDecimal n1 = new BigDecimal(Double.toString(v1));
        BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.add(n2).doubleValue();
    }

    /**
     * BigDecimal 相减
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double subtract(double v1, double v2) {
        BigDecimal n1 = new BigDecimal(Double.toString(v1));
        BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.subtract(n2).doubleValue();
    }

    /**
     * BigDecimal 相乘
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double multiply(double v1, double v2) {
        BigDecimal n1 = new BigDecimal(Double.toString(v1));
        BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.multiply(n2).doubleValue();
    }

    /**
     * BigDecimal 相除
     *
     * @param v1
     * @param v2
     * @return double
     */
    public static Double divide(double v1, double v2) {
        BigDecimal n1 = new BigDecimal(Double.toString(v1));
        BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.divide(n2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 自定义小数点位
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static Double divide(double v1, double v2, int scale) {
        BigDecimal n1 = new BigDecimal(Double.toString(v1));
        BigDecimal n2 = new BigDecimal(Double.toString(v2));
        return n1.divide(n2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

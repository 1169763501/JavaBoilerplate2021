package com.nbnfsoft.admin.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nbnfsoft.admin.domain.input.PageInput;
import org.apache.commons.lang3.StringUtils;

/**
 * Descriptions: 通用<p>
 *
 * @author SailHe
 * @date 2018/11/04 16:45
 */
public class CommonUtil {
    public static <Type> boolean isNullOrEmpty(Type obj) {
        return obj == null || obj.toString().equals("");
    }

    public static <Type> Type defaultOrItself(Type obj, Type def) {
        return isNullOrEmpty(obj) ? def : obj;
    }

    public static <Type> Type firstOrDefault(Collection<Type> collection, Type def) {
        return isNullOrEmpty(collection) ? def : collection.isEmpty() ? def : collection.stream().findFirst().get();
    }

    /**
     * Descriptions: 搜索指定流中的指定域1 返回指定域2 若不存在则返回自定义默认值<p>
     *
     * @author SailHe
     * @date 2020/4/17 11:14
     */
    public static <FieldType, ElementType, ResType> ResType findField(
            Stream<ElementType> elementStream, FieldType fieldVal, Function<ElementType, FieldType> fieldSelector,
            Function<ElementType, ResType> resSelector, ResType def) {
        return CommonUtil.firstOrDefault(
                elementStream.filter(p -> fieldSelector.apply(p).equals(fieldVal))
                        .map(resSelector).collect(Collectors.toList()),
                def);
    }

    public static <EnumType> EnumType findEnum(Stream<EnumType> elementStream,
                                               Predicate<? super EnumType> predicate,
                                               String EnumName) {
        try {
            return elementStream.filter(predicate).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("指定" + EnumName + "不存在");
        }
    }

    /**
     * Descriptions: 迭代拼接字符串 中间使用sp做间隔符<p>
     *
     * @author SailHe
     * @date 2020/4/13 9:39
     */
    public static <T> String toString(Iterator<T> iterator, String sp) {
        String res = "";
        while (iterator.hasNext()) {
            res += (StringUtils.isEmpty(res) ? "" : sp) + iterator.next().toString();
        }
        return res;
    }

    public static Page toPage(PageInput input) {
        Page page = new Page();
        if (input.getCurrent_page() != null && input.getPer_page() != null) {
            page.setSize(input.getPer_page());
            page.setCurrent(input.getCurrent_page());
        }
        return page;
    }

    public static java.util.Date LocalDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date = java.util.Date.from(instant);
        return date;
    }

    /**
     * 生成随机数
     *
     * @param len
     * @return
     */
    public static String buildRandom(int len) {
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < len; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }
}

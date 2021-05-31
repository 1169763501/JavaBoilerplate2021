package com.nbnfsoft.admin.utils.sail;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.Collection;
import java.util.Date;

public class SailLamUpdWrapper<T> extends LambdaUpdateWrapper<T> implements IHandleTimestamp {

    public LambdaUpdateWrapper<T> takeone() {
        return take(1);
    }

    public LambdaUpdateWrapper<T> take(int num) {
        return apply(String.format("ROWNUM<=%s", num));
    }


    @Override
    public LambdaUpdateWrapper<T> apply(boolean condition, String applySql, Object... value) {

        if (applySql == null || applySql.equals("")) {
            return this;
        }

        return super.apply(condition, "(" + applySql + ")", value);
    }

    @Override
    public LambdaUpdateWrapper<T> notIn(boolean condition, SFunction<T, ?> column, Collection<?> coll) {

        if (coll == null || coll.size() == 0) {
            return this;
        }

        return super.notIn(condition, column, coll);
    }

    @Override
    public LambdaUpdateWrapper<T> in(boolean condition, SFunction<T, ?> column, Collection<?> coll) {

        if (coll == null || coll.size() == 0) {
            return apply(condition, "1=2");
        }

        return super.in(condition, column, coll);
    }


    @Override
    public LambdaUpdateWrapper<T> set(SFunction<T, ?> column, Object val) {
        return super.set(column, val == null ? "" : val);
    }

    @Override
    public LambdaUpdateWrapper<T> ne(boolean condition, SFunction<T, ?> column, Object val) {

        if(val==null)
            return isNotNull(condition,column);

        if (val instanceof Date)
            return apply(condition, String.format("%s <> %s", columnToString(column), toTimestamp((Date) val)));

        return super.ne(condition, column, val);
    }

    @Override
    public LambdaUpdateWrapper<T> eq(boolean condition, SFunction<T, ?> column, Object val) {

        if(val==null)
            return isNull(condition,column);

        if (val instanceof Date)
            return apply(condition, String.format("%s == %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);
    }

    @Override
    public LambdaUpdateWrapper<T> lt(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s < %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public LambdaUpdateWrapper<T> gt(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s > %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public LambdaUpdateWrapper<T> le(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s <= %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public LambdaUpdateWrapper<T> ge(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s >= %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }
}

package com.nbnfsoft.admin.utils.sail;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.Collection;
import java.util.Date;

public class SailLamQueWrapper<T> extends LambdaQueryWrapper<T> implements IHandleTimestamp {

    public LambdaQueryWrapper<T> takeone() {
        return take(1);
    }

    public LambdaQueryWrapper<T> take(int num) {
        return apply(String.format("ROWNUM<=%s", num));
    }


    @Override
    public LambdaQueryWrapper<T> notIn(boolean condition, SFunction<T, ?> column, Collection<?> coll) {

        if (coll == null || coll.size() == 0) {
            return this;
        }

        return super.notIn(condition, column, coll);
    }

    @Override
    public LambdaQueryWrapper<T> apply(boolean condition, String applySql, Object... value) {

        if (applySql == null || applySql.equals("")) {
            return this;
        }

        return super.apply(condition, "(" + applySql + ")", value);
    }

    @Override
    public LambdaQueryWrapper<T> in(boolean condition, SFunction<T, ?> column, Collection<?> coll) {

        if (coll == null || coll.size() == 0) {
            return apply(condition, "1=2");
        }

        return super.in(condition, column, coll);
    }

    @Override
    public LambdaQueryWrapper<T> ne(boolean condition, SFunction<T, ?> column, Object val) {

        if(val==null)
            return isNotNull(condition,column);

        if (val instanceof Date)
            return apply(condition, String.format("%s <> %s", columnToString(column), toTimestamp((Date) val)));

        return super.ne(condition, column, val);
    }

    @Override
    public LambdaQueryWrapper<T> eq(boolean condition, SFunction<T, ?> column, Object val) {

        if(val==null)
            return isNull(condition,column);

        if (val instanceof Date)
            return apply(condition, String.format("%s == %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);
    }

    @Override
    public LambdaQueryWrapper<T> lt(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s < %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public LambdaQueryWrapper<T> gt(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s > %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public LambdaQueryWrapper<T> le(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s <= %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public LambdaQueryWrapper<T> ge(boolean condition, SFunction<T, ?> column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s >= %s", columnToString(column), toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

}

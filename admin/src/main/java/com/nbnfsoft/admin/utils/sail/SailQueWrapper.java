package com.nbnfsoft.admin.utils.sail;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Collection;
import java.util.Date;

public class SailQueWrapper extends QueryWrapper implements IHandleTimestamp {

    public Object takeone() {
        return take(1);
    }

    public Object take(int num) {
        return apply(String.format("ROWNUM<=%s", num));
    }


    @Override
    public AbstractWrapper apply(boolean condition, String applySql, Object... value) {

        if (applySql == null || applySql.equals("")) {
            return this;
        }

        return super.apply(condition, "(" + applySql + ")", value);
    }

    @Override
    public AbstractWrapper notIn(boolean condition, Object column, Collection coll) {

        if (coll == null || coll.size() == 0) {
            return this;
        }

        return super.notIn(condition, column, coll);
    }

    @Override
    public AbstractWrapper in(boolean condition, Object column, Collection coll) {

        if (coll == null || coll.size() == 0) {
            return apply(condition, "1=2");
        }

        return super.in(condition, column, coll);
    }

    @Override
    public AbstractWrapper eq(boolean condition, Object column, Object val) {

        if(val==null)
            return isNull(condition,column);

        if (val instanceof Date)
            return apply(condition, String.format("%s == %s", column, toTimestamp((Date) val)));

        return super.eq(condition, column, val);
    }

    @Override
    public AbstractWrapper ne(boolean condition, Object column, Object val) {

        if(val==null)
            return isNotNull(condition,column);

        if (val instanceof Date)
            return apply(condition, String.format("%s <> %s", columnToString(column), toTimestamp((Date) val)));

        return super.ne(condition, column, val);
    }

    @Override
    public AbstractWrapper lt(boolean condition, Object column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s < %s", column, toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public AbstractWrapper gt(boolean condition, Object column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s > %s", column, toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }

    @Override
    public AbstractWrapper le(boolean condition, Object column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s <= %s", column, toTimestamp((Date) val)));

        return super.eq(condition, column, val);
    }

    @Override
    public AbstractWrapper ge(boolean condition, Object column, Object val) {

        if (val instanceof Date)
            return apply(condition, String.format("%s >= %s", column, toTimestamp((Date) val)));

        return super.eq(condition, column, val);

    }


}

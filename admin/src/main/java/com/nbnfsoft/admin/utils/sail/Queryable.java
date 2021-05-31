package com.nbnfsoft.admin.utils.sail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Collection;
import java.util.List;

public class Queryable<T> {

    private BaseMapper<T> baseMapper;

    public Queryable(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    SailLamUpdWrapper<T> uw = new SailLamUpdWrapper<>();
    SailLamQueWrapper<T> qw = new SailLamQueWrapper<>();

    protected Queryable<T> clone() {
        Queryable<T> newone = new Queryable<>(baseMapper);
        newone.uw = SerializationUtils.clone(uw);
        newone.qw = SerializationUtils.clone(qw);
        return newone;
    }

    public Queryable<T> orderByAsc(SFunction<T, ?>... columns) {
        Queryable<T> clone = this.clone();
        clone.uw.orderByAsc(columns);
        clone.qw.orderByAsc(columns);
        return clone;
    }

    public Queryable<T> groupBy(SFunction<T, ?>... columns) {
        Queryable<T> clone = this.clone();
        clone.uw.groupBy(columns);
        clone.qw.groupBy(columns);
        return clone;
    }

    public Queryable<T> orderByDesc(SFunction<T, ?>... columns) {
        Queryable<T> clone = this.clone();
        clone.uw.orderByDesc(columns);
        clone.qw.orderByDesc(columns);
        return clone;
    }

    public Queryable<T> take(int num) {
        Queryable<T> clone = this.clone();
        clone.uw.take(num);
        clone.qw.take(num);
        return clone;
    }

    public Queryable<T> select(SFunction<T, ?>... columns) {
        Queryable<T> clone = this.clone();
        clone.qw.select(columns);
        return clone;
    }

    public Queryable<T> like(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.like(column, val);
        clone.qw.like(column, val);
        return clone;
    }

    public Queryable<T> notLike(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.notLike(column, val);
        clone.qw.notLike(column, val);
        return clone;
    }

    public Queryable<T> setSql(String sql) {
        Queryable<T> clone = this.clone();
        clone.uw.setSql(sql);
        return clone;
    }

    public Queryable<T> apply(String applySql) {
        Queryable<T> clone = this.clone();
        clone.uw.apply(applySql);
        clone.qw.apply(applySql);
        return clone;
    }

    public Queryable<T> in(SFunction<T, ?> column, Collection<?> coll) {
        Queryable<T> clone = this.clone();
        clone.uw.in(column, coll);
        clone.qw.in(column, coll);
        return clone;
    }

    public Queryable<T> notIn(SFunction<T, ?> column, Collection<?> coll) {
        Queryable<T> clone = this.clone();
        clone.uw.notIn(column, coll);
        clone.qw.notIn(column, coll);
        return clone;
    }

    public Queryable<T> set(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.set(column, val);
        return clone;
    }

    public Queryable<T> eq(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.eq(column, val);
        clone.qw.eq(column, val);
        return clone;
    }

    public Queryable<T> ne(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.ne(column, val);
        clone.qw.ne(column, val);
        return clone;
    }


    public Queryable<T> le(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.le(column, val);
        clone.qw.le(column, val);
        return clone;
    }

    public Queryable<T> lt(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.lt(column, val);
        clone.qw.lt(column, val);
        return clone;
    }

    public Queryable<T> ge(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.ge(column, val);
        clone.qw.ge(column, val);
        return clone;
    }

    public Queryable<T> gt(SFunction<T, ?> column, Object val) {
        Queryable<T> clone = this.clone();
        clone.uw.gt(column, val);
        clone.qw.gt(column, val);
        return clone;
    }

    public Integer count() {
        return baseMapper.selectCount(qw);
    }

    public int delete() {
        return baseMapper.delete(qw);
    }

    public int update() {
        return baseMapper.update(null, uw);
    }

    public T first() {

        SailLamQueWrapper<T> clone = SerializationUtils.clone(qw);
        clone.takeone();

        return baseMapper.selectOne(clone);
    }

    public List<T> toList() {
        return baseMapper.selectList(qw);
    }

}


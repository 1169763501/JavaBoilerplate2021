package com.nbnfsoft.admin.utils.sail;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface ISailRepository<T> extends BaseMapper<T> {

    default Queryable<T> getAll() {
        return new Queryable<T>(this);
    }

}

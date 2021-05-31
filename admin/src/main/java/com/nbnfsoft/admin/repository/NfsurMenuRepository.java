package com.nbnfsoft.admin.repository;

import com.nbnfsoft.admin.entity.NfsurMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbnfsoft.admin.utils.sail.ISailRepository;
import org.springframework.stereotype.Component;
/**
 * <p>
 * 目录表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-05-06
 */
@Component(value = "NFSUR_MENU")
public interface NfsurMenuRepository extends BaseMapper<NfsurMenu> , ISailRepository<NfsurMenu> {

}

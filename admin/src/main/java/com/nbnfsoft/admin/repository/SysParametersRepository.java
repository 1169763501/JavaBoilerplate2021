package com.nbnfsoft.admin.repository;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbnfsoft.admin.entity.SysParameters;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统参数 Mapper 接口
 * </p>
 *
 * @author louyi
 * @since 2019-11-15
 */
@Repository
public interface SysParametersRepository extends BaseMapper<SysParameters> {
    /**
     * 全局参数
     *
     * @param code
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    @Select("select VALUE from NFSYS_PARAMETERS where org_id=0 and code=#{code}")
    String selectGlobalValueByCode(@Param("code") String code);
}

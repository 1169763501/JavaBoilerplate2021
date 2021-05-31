package com.nbnfsoft.admin.repository;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nbnfsoft.admin.entity.SysAttachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 附件表 Mapper 接口
 * </p>
 *
 * @author louyi
 * @since 2019-11-15
 */
@Repository
public interface SysAttachmentRepository extends BaseMapper<SysAttachment> {

    /**
     * 保存
     *
     * @param sysAttachment
     */
    @InterceptorIgnore(tenantLine = "true")
    void saveSysAttachment(@Param("item") SysAttachment sysAttachment);
}

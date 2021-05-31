package com.nbnfsoft.admin.manager;

import com.nbnfsoft.admin.entity.SysJob;
import com.nbnfsoft.admin.repository.SysJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 钉钉业务相关
 *
 * @Author:louyi
 * @Description：
 * @Date:Create in 9:12 2020-12-24
 */
@Service
public class SysJobManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysJobRepository sysJobRepository;

    /**
     * 保存定时任务
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(SysJob sysJob) {
        sysJobRepository.insert(sysJob);
    }
}

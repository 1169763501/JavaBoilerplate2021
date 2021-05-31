package com.nbnfsoft.admin.manager;

import com.nbnfsoft.admin.entity.SysAttachment;
import com.nbnfsoft.admin.repository.SysAttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 附件
 */
@Service
public class SysAttachmentManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysAttachmentRepository sysAttachmentRepository;

    /**
     * 保存
     *
     * @param input
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(SysAttachment input) {
        sysAttachmentRepository.saveSysAttachment(input);
    }
}

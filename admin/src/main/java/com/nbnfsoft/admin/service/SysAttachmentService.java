package com.nbnfsoft.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.nbnfsoft.admin.common.constant.ParameterConstants;
import com.nbnfsoft.admin.domain.dto.AttachmentDto;
import com.nbnfsoft.admin.domain.output.AttachmentOutput;
import com.nbnfsoft.admin.entity.SysAttachment;
import com.nbnfsoft.admin.manager.SysAttachmentManager;
import com.nbnfsoft.admin.utils.MimeTypeUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Descriptions: 附件相关<p>
 *
 * @author SailHe
 * @date 2019/12/19 13:38
 */
@Service
public class SysAttachmentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysAttachmentManager sysAttachmentManager;
    /**
     * 保存附件
     *
     * @param attachInfo
     * @return
     */
    public AttachmentOutput save(SysAttachment attachInfo) {
        sysAttachmentManager.save(attachInfo);
        AttachmentOutput output = new AttachmentOutput();
        output.setAttachId(attachInfo.getId());
        output.setAttachName(attachInfo.getFileName());
        output.setAttachUrl(attachInfo.getFilePath());
        output.setFileSize(attachInfo.getFileSize());
        output.setFileSuffix(attachInfo.getFileSuffix());
        output.setThumbPath(attachInfo.getThumbPath());
        return output;
    }
}

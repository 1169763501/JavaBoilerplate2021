package com.nbnfsoft.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.ArrayUtil;
import com.nbnfsoft.admin.common.constant.Constants;
import com.nbnfsoft.admin.common.constant.ParameterConstants;
import com.nbnfsoft.admin.domain.output.AttachmentOutput;
import com.nbnfsoft.admin.entity.SysAttachment;
import com.nbnfsoft.admin.utils.FileUtil;
import com.nbnfsoft.admin.utils.FriendlyException;
import com.nbnfsoft.admin.utils.MimeTypeUtils;
import com.nbnfsoft.admin.utils.PicUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 14:03 2021-02-20
 */
@Service
public class CommonService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${uploadFile.resourceHandler}")
    private String resourceHandler;

    @Value("${uploadFile.location}")
    private String location;

    @Autowired
    private SysParametersService sysParametersService;
    @Autowired
    private SysAttachmentService attachmentService;

    /**
     * 图片上传
     *
     * @param uploadFile
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AttachmentOutput upload(MultipartFile uploadFile) {
        if (uploadFile.isEmpty()) {
            throw new FriendlyException("上传文件不能为空");
        }
        try {
            FileUtil.assertAllowed(uploadFile, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            String fileDir = Paths.get(location).toAbsolutePath().normalize().toString();
            if (!fileDir.endsWith(String.valueOf(File.separatorChar))) {
                fileDir += File.separatorChar;
            }
            if (FileUtil.FileUtilFlag.FAILURE == FileUtil.createDir(fileDir)) {
                throw new IOException(FileUtil.getMsg());
            }
            String prefix = sysParametersService.selectGlobalValueByCode(ParameterConstants.UPLOAD_FILE_PREFIX, false);
            String fileName = ObjectId.next() + Constants.SPLIT + uploadFile.getOriginalFilename();
            File destFile = new File(fileDir + fileName);
            uploadFile.transferTo(destFile);
            //图片类型进行压缩
            if (ArrayUtil.contains(MimeTypeUtils.IMAGE, uploadFile.getContentType())) {
                //旋转处理
                PicUtils.getPictureByName(fileDir, fileName);
                Thumbnails.of(fileDir + fileName).scale(0.25f).toFile(fileDir + Constants.THUMB + Constants.SPLIT + fileName);
            }
            SysAttachment attachInfo = new SysAttachment();
            attachInfo.setFileName(fileName);
            attachInfo.setFilePath(prefix + resourceHandler.substring(0, resourceHandler.lastIndexOf(Constants.SLASH) + 1) + fileName);
            attachInfo.setFileSuffix(fileName.substring(fileName.lastIndexOf(".") + 1));
            attachInfo.setFileSize(Double.valueOf(uploadFile.getSize() / 1000.0));
            attachInfo.setThumbPath(prefix + resourceHandler.substring(0, resourceHandler.lastIndexOf(Constants.SLASH) + 1)
                    + Constants.THUMB + Constants.SPLIT + fileName);
            return attachmentService.save(attachInfo);
        } catch (IOException e) {
            logger.error("文件上传失败: " + e.toString(), e);
        }
        return null;
    }
}

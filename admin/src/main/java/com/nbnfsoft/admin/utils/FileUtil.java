package com.nbnfsoft.admin.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 文件系统工具类
 * <p>
 *
 * @author SailHe
 * @date 2018/02/19 07:19
 */
public class FileUtil {

    public enum FileUtilFlag {
        SUCCESS("成功"), FAILURE("失败"), NOT_PERFORMED("未执行操作");

        private final String name;

        FileUtilFlag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    private static String msg = "";

    public static String getMsg() {
        // @TODO synchronized CONTROL
        synchronized (msg) {
            return msg;
        }
    }

    private static void setMsg(String msg) {
        synchronized (FileUtil.msg) {
            FileUtil.msg = msg;
        }
    }

    public static FileUtilFlag createFile(String destFilePath) {
        File file = new File(destFilePath);
        if (file.exists()) {
            setMsg("未创建单个文件" + destFilePath + ": 目标文件已存在！");
            return FileUtilFlag.NOT_PERFORMED;
        }
        if (destFilePath.endsWith(File.separator)) {
            setMsg("创建文件" + destFilePath + "失败，目标文件不能为目录！");
            return FileUtilFlag.FAILURE;
        }
        //Stack<String> pathSt;
        // 判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            // 如果目标文件所在的目录不存在，则创建父目录
            if (!file.getParentFile().mkdirs()) {
                setMsg("创建目标文件所在目录失败！");
                return FileUtilFlag.FAILURE;
            }
        }
        // 创建目标文件
        try {
            if (file.createNewFile()) {
                setMsg("创建文件" + destFilePath + "成功！");
                return FileUtilFlag.SUCCESS;
            } else {
                setMsg("创建文件" + destFilePath + "失败！");
                return FileUtilFlag.FAILURE;
            }
        } catch (IOException e) {
            e.printStackTrace();
            setMsg("创建文件" + destFilePath + "失败！" + e.getMessage());
            return FileUtilFlag.FAILURE;
        }
    }

    public static FileUtilFlag eraseFile(String filePath) {
        File file = new File(filePath);
        if (file.delete()) {
            setMsg(file.getName() + " 文件已被删除！");
            return FileUtilFlag.SUCCESS;
        } else {
            setMsg("文件删除失败！");
            return FileUtilFlag.FAILURE;
        }
    }

    public static FileUtilFlag createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            setMsg("未创建目录" + destDirName + ": 目标目录已经存在");
            return FileUtilFlag.NOT_PERFORMED;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目录
        if (dir.mkdirs()) {
            setMsg("创建目录" + destDirName + "成功！");
            return FileUtilFlag.SUCCESS;
        } else {
            setMsg("创建目录" + destDirName + "失败！");
            return FileUtilFlag.FAILURE;
        }
    }

    public static String createTempFile(String prefix, String suffix, String dirName) {
        File tempFile;
        if (dirName == null) {
            try {
                // 在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                // 返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                setMsg("创建临时文件失败！" + e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            // 如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (FileUtil.createDir(dirName) != FileUtilFlag.SUCCESS) {
                    setMsg("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try {
                // 在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                setMsg("创建临时文件失败！" + e.getMessage());
                return null;
            }
        }
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     */
    public static void assertAllowed(MultipartFile file, String[] allowedExtension) {
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new FriendlyException("不支持的图片类型");
            }  else {
                throw new FriendlyException("不支持的文件类型");
            }
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static boolean isAllowedExtension(String extension, String[] allowedExtension)
    {
        for (String str : allowedExtension)
        {
            if (str.equalsIgnoreCase(extension))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static  String getExtension(MultipartFile file)
    {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension))
        {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }


    public static void main(String[] args) {
        //创建目录
        String dirName = "F:/Tmp/tmp/tempDir";
        FileUtil.createDir(dirName);
        //创建文件
        String fileName = dirName + "/subTempDir/tempFile.txt";
        FileUtil.createFile(fileName);
        //创建临时文件
        String prefix = "temp";
        String suffix = ".txt";
        int fileCnt = 10;
        for (int i = 0; i < fileCnt; i++) {
            setMsg("创建了临时文件：" + FileUtil.createTempFile(prefix, suffix, dirName));
        }
        //在默认目录下创建临时文件
        for (int i = 0; i < 10; i++) {
            setMsg("在默认目录下创建了临时文件：" + FileUtil.createTempFile(prefix, suffix, null));
        }
    }


}

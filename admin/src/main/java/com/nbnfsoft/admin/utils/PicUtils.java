package com.nbnfsoft.admin.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liangjie
 * @Date 2020/12/9 9:41
 * @Description
 */
public class PicUtils {

    private static Logger log = LoggerFactory.getLogger(PicUtils.class);

    public static void getPictureByName(String filePath, String name) {

        try {
            //name为前端请求图片名，如 a.jpg
            BufferedImage src = getPicture(filePath + name);
            //图片存在
            if (src != null) {
                //获取图片旋转角度
                int angel = getRotateAngleForPhoto(filePath + name);
                if (angel != 0) {
                    Image image = ImgUtil.rotate(ImageIO.read(FileUtil.file(filePath + name)), angel);
                    ImgUtil.write(image, FileUtil.file(filePath + name));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    /**
     * 读取指定图片
     */
    public static BufferedImage getPicture(String path) {
        BufferedImage bi = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                return null;
            }
            bi = ImageIO.read(file);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return bi;
    }


    /**
     * 图片翻转时，计算图片翻转到正常显示需旋转角度
     */
    public static int getRotateAngleForPhoto(String fileName) throws IOException, MetadataException, ImageProcessingException {
        File file = new File(fileName);
        int angel = 0;
        Metadata metadata;
        metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directories : metadata.getDirectories()) {
            for (Tag tag : directories.getTags()) {
                log.info(String.format("[%s] - %s = %s", directories.getName(), tag.getTagName(), tag.getDescription()));
                if ("Orientation".equals(tag.getTagName())) {
                    log.info("ios图片");
                    System.out.println("ios图片");
                    int orientation = directories.getInt(ExifIFD0Directory.TAG_ORIENTATION);
                    // 原图片的方向信息
                    if (6 == orientation) {
                        log.info("旋转90");
                        //6旋转90
                        angel = 90;
                    } else if (3 == orientation) {
                        log.info("旋转180");
                        //3旋转180
                        angel = 180;
                    } else if (8 == orientation) {
                        log.info("旋转270");
                        //8旋转90
                        angel = 270;
                    }
                }
            }
        }
        return angel;
    }
}


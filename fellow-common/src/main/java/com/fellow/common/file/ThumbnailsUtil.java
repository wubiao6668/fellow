package com.fellow.common.file;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.exception.BusinessException;
import com.fellow.domain.web.Response;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wubiao on 2016/9/2.
 */
public class ThumbnailsUtil {

    public static void uploadImg(MultipartFile file,String parentPath,String imageName,String imageExtend)throws Exception{
        BufferedImage bufreader = ImageIO.read(file.getInputStream());
        int width = bufreader.getWidth();
        int height = bufreader.getHeight();
        if (width <= 0 || height <= 0) {
            throw new BusinessException("不支持的图片类型");
        }
        if (width > SystemConstant.IMAGE_MAX_WIDTH) {
            width = SystemConstant.IMAGE_MAX_WIDTH;
        }
        if (height > SystemConstant.IMAGE_MAX_HEIGHT) {
            height = SystemConstant.IMAGE_MAX_HEIGHT;
        }
        long size = file.getSize();
        double scale = 1;
        if (size > SystemConstant.IMAGE_MAX_SIZE) {
            scale = BigDecimal.valueOf(SystemConstant.IMAGE_MAX_SIZE).divide(BigDecimal.valueOf(size), 2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        File parentDirectory = new File(parentPath);
        if (!parentDirectory.exists()){
            parentDirectory.mkdirs();
        }

        Thumbnails.of(file.getInputStream()) .outputQuality(scale).forceSize(width, height).outputFormat(imageExtend).toFile(parentPath + File.separator +  imageName);
    }

    public static final void main(String[] arg) throws Exception{

        Thumbnails.of("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg")
                .scale(1f)
                .outputFormat("jpg")
                .toFile("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips2.jpg");
    }

}

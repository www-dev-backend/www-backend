package com.www.backend.common.util;

import com.www.backend.config.AWSConfig;
import com.www.backend.domain.file.dto.Resolution;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

@Component
public final class MultipartUtils {
    private static String region;
    private static String bucket;

    private static final String BASE_DIR = "images";

    public static String getLocalHomeDirectory() {
        return System.getProperty("user.home");
    }

    public static String createFileId() {
        return UUID.randomUUID().toString();
    }

    public static String getFormat(String contentType) {
        if (StringUtils.hasText(contentType)) {
            return contentType.substring(contentType.lastIndexOf("/") + 1);
        }

        return null;
    }

    public static String createPath(String directory, String fileId, String format) {
        return String.format("%s/%s.%s", directory, fileId, format);
    }

    public static String createUrl(String directory, String fileId, String format) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s/%s.%s",bucket, region, directory, fileId, format);
    }

    public static String createFileType(String format) {
        String type = "";
        switch (format) {
            case "jpeg", "jpg", "png", "gif" -> type = "image";
            case "mp4" -> type = "video";
            default -> {
            }
        }

        return type;
    }

    public static Resolution getImageResolution(MultipartFile file) throws IOException {
        try{
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            return new Resolution(width, height);
        }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Resolution getVideoResolution(MultipartFile file) {
        try{
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            return new Resolution(width, height);
        }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Resolution getResolution(String fileType, MultipartFile file) {
        try {
            Resolution resolution = null;

            switch(fileType) {
                case "image" -> resolution = getImageResolution(file);
                // TODO: Implementation Video Resolution
                case "video" ->resolution = new Resolution(0, 0);
            }

            return resolution;
        }catch(IOException e) {
            return null;
        }
    }

    @Value("${cloud.aws.region.static}")
    public void setRegion(String value) {
        region = value;
    }

    @Value("${cloud.aws.s3.bucket}")
    public void setBucket(String value) {
        bucket = value;
    }
}

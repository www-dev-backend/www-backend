package com.www.backend.common.util;

import org.springframework.util.StringUtils;

import java.util.UUID;

public final class MultipartUtils {
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

    public static String createBaseDirectory(String format) {
        String dir = "";
        switch (format) {
            case "jpeg", "jpg", "png", "gif" -> dir = "images";
            case "mp4" -> dir = "videos";
            default -> {
            }
        }

        return dir;
    }
}

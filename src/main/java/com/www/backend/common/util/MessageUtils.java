package com.www.backend.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class MessageUtils {
    private static MessageSource messageSource;
    private static String language;

    public MessageUtils(MessageSource messageSource,
                       @Value("${spring.web.locale}") String language) {
        MessageUtils.messageSource = messageSource;
        MessageUtils.language = language;
    }

    public static String getMessageByKey(String key) {
        return messageSource.getMessage(key, null, new Locale(language));
    }
}

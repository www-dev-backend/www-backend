package com.www.backend.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    public static Map<String, String> pipeMessage(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return errors;
    }

    public static Map<String, String> pipeMessage(ConstraintViolationException e) {
        String fullMessage = e.getMessage();
        String[] messages = fullMessage.split(", ");

        Map<String, String> errors = new HashMap<>();

        for (String message : messages) {
            String[] fieldNameAndMessage = message.split(": ");
            String[] fieldName = fieldNameAndMessage[0].split("\\.");
            errors.put(fieldName[1], fieldNameAndMessage[1]);
        }

        return errors;
    }
}

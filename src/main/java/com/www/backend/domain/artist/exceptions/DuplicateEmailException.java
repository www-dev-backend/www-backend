package com.www.backend.domain.artist.exceptions;


import com.www.backend.common.exceptions.HttpException;
import com.www.backend.common.util.MessageUtils;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends HttpException {
    public DuplicateEmailException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

    public static DuplicateEmailException of(String messageKey) {
        String message = MessageUtils.getMessageByKey(messageKey);

        return new DuplicateEmailException(message);
    }
}

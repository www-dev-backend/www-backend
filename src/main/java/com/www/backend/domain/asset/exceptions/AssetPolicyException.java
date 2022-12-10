package com.www.backend.domain.asset.exceptions;

import com.www.backend.common.exceptions.HttpException;
import com.www.backend.common.util.MessageUtils;
import org.springframework.http.HttpStatus;

public class AssetPolicyException extends HttpException {
    public AssetPolicyException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public static AssetPolicyException of(String messageKey) {
        String message = MessageUtils.getMessageByKey(messageKey);

        return new AssetPolicyException(message);
    }
}

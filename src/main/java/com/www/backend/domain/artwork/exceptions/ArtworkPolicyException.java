package com.www.backend.domain.artwork.exceptions;

import com.www.backend.common.exceptions.HttpException;
import com.www.backend.common.util.MessageUtils;
import com.www.backend.domain.asset.exceptions.AssetPolicyException;
import org.springframework.http.HttpStatus;

public class ArtworkPolicyException extends HttpException {
    public ArtworkPolicyException() {
        super("Artwork 정책에 위배 되었습니다.", HttpStatus.BAD_REQUEST);
    }

    public ArtworkPolicyException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public static AssetPolicyException of(String messageKey) {
        String message = MessageUtils.getMessageByKey(messageKey);

        return new AssetPolicyException(message);
    }
}

package com.www.backend.common.aop;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.www.backend.common.exceptions.HttpException;
import com.www.backend.common.response.ErrorResponse;
import com.www.backend.common.response.ValidationResponse;
import com.www.backend.common.util.MessageUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                           @NotNull HttpHeaders headers,
                                                                           @NotNull HttpStatus status,
                                                                           @NotNull WebRequest request) {
        String message = MessageUtils.getMessageByKey("error.notReadableException");
        Throwable cause = ex.getCause();

        if (cause instanceof JsonParseException exception) {
            message = exception.getOriginalMessage();
        } else if (cause instanceof MismatchedInputException exception) {
            if (exception.getPath() != null && exception.getPath().size() > 0) {
                message = message + " [" + exception.getPath().get(0).getFieldName() + "]";
            }
        } else if (cause instanceof JsonMappingException exception) {
            if (exception.getPath() != null && exception.getPath().size() > 0) {
                message = message + " [" + exception.getPath().get(0).getFieldName() + "]";
            }
        }

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResource = new ErrorResponse(
                httpStatus.value(),
                request.getDescription(false).replace("uri=", ""),
                message);

        return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                           @NotNull HttpHeaders headers,
                                                                           @NotNull HttpStatus status,
                                                                           @NotNull WebRequest request) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationResponse resource = new ValidationResponse(
                httpStatus.value(),
                request.getDescription(false).replace("uri=", ""),
                MessageUtils.getMessageByKey("error.validation"),
                MessageUtils.pipeMessage(ex)
        );

        return new ResponseEntity<>(resource, httpStatus);
    }

//    @ExceptionHandler(value = BadCredentialsException.class)
//    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                httpStatus.value(),
//                request.getRequestURI(),
//                MessageUtils.getMessageByKey("error.credentialsFailed"));
//
//        return new ResponseEntity<>(errorResponse, httpStatus);
//    }


    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException e, HttpServletRequest request) {
        ErrorResponse errorResource = new ErrorResponse(
                e.getHttpStatus().value(),
                request.getRequestURI(),
                e.getMessage());

        return new ResponseEntity<>(errorResource, e.getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResource = new ErrorResponse(
                httpStatus.value(),
                request.getRequestURI(),
                MessageUtils.getMessageByKey("error.internalServer"),
                e.getMessage());

        return new ResponseEntity<>(errorResource, httpStatus);
    }
}

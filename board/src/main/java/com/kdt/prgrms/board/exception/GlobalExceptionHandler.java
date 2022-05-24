package com.kdt.prgrms.board.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.kdt.prgrms.board.exception.custom.AccessDeniedException;
import com.kdt.prgrms.board.exception.custom.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.kdt.prgrms.board.exception.ErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentValidException() {

        logger.debug("MethodArgumentValidException : {}", INVALID_INPUT_REQUEST.getMessage());

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {

        logger.debug("IllegalArgumentException : {}", INVALID_INPUT_REQUEST.getMessage());

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException() {

        logger.debug("NotFoundException : {}", REQUEST_NOT_FOUND.getMessage());

        return ErrorResponse.toResponseEntity(REQUEST_NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException() {

        logger.debug("AccessDeniedException : {}", ACCESS_DENIED.getMessage());

        return ErrorResponse.toResponseEntity(ACCESS_DENIED);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidException() {

        logger.debug("InvalidFormatException : {}", INVALID_INPUT_REQUEST);

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }


    //TODO : Excption , RuntimeExcpetion 막기
    //TODO : error 레벨 낮추기(400대는 DEBUG)
}

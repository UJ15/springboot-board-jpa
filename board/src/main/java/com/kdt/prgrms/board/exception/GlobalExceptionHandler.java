package com.kdt.prgrms.board.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.kdt.prgrms.board.exception.custom.AccessDeniedException;
import com.kdt.prgrms.board.exception.custom.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.kdt.prgrms.board.exception.ErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentValidException() {

        logger.warn("MethodArgumentValidException : {}", INVALID_INPUT_REQUEST.getMessage());

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {

        logger.warn("IllegalArgumentException : {}", exception.getMessage());

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {

        logger.warn("NotFoundException : {}", exception.getMessage());

        return ErrorResponse.toResponseEntity(exception.getErrorCode());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception) {

        logger.warn("AccessDeniedException : {}", exception.getMessage());

        return ErrorResponse.toResponseEntity(exception.getErrorCode());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidException() {

        logger.warn("InvalidFormatException : {}", INVALID_INPUT_REQUEST);

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException() {

        logger.error("RuntimeException : {}", "RuntimeException");

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException() {

        logger.error("Exception : {}", "Exception");

        return ErrorResponse.toResponseEntity(INVALID_INPUT_REQUEST);
    }

    //TODO : Excption , RuntimeExcpetion 막기
    //TODO : error 레벨 낮추기(400대는 DEBUG)
}

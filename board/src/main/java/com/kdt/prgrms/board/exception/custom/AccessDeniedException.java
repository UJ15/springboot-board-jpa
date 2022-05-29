package com.kdt.prgrms.board.exception.custom;

import com.kdt.prgrms.board.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import javax.servlet.annotation.ServletSecurity;

public class AccessDeniedException extends RuntimeException {

    private final ErrorCode errorCode;

    public AccessDeniedException(ErrorCode errorCode) {

        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {

        return errorCode.getMessage();
    }

    public ErrorCode getErrorCode() {

        return errorCode;
    }
}

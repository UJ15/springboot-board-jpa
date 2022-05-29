package com.kdt.prgrms.board.exception.custom;

import com.kdt.prgrms.board.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode) {

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

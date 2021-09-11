package com.anny.board.boardme.global.exception;

import com.anny.board.boardme.util.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

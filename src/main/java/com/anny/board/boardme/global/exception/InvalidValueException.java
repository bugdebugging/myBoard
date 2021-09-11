package com.anny.board.boardme.global.exception;

import com.anny.board.boardme.util.ErrorCode;

public class InvalidValueException extends BusinessException {
    public InvalidValueException(String message) {
        super(message, ErrorCode.INVALID_VALUE);
    }
}

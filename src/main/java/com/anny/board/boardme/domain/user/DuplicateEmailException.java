package com.anny.board.boardme.domain.user;

import com.anny.board.boardme.global.exception.BusinessException;
import com.anny.board.boardme.util.ErrorCode;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException(String message) {
        super(message, ErrorCode.EMAIL_DUPLICATION);
    }
}

package com.anny.board.boardme.global.exception;

import com.anny.board.boardme.global.exception.BusinessException;
import com.anny.board.boardme.util.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }
}

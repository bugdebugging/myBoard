package com.anny.board.boardme.util;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //common
    METHOD_ARGUMENT_NOT_VALID(400, "invalid input", "C001"),
    METHOD_ARGUMENT_TYPE_MISMATCH(400, "parameter type mismatch", "C002"),
    INVALID_VALUE(400, "invalid input", "C003"),
    ENTITY_NOT_FOUND(400, "entity not found", "C004"),

    //member
    EMAIL_DUPLICATION(400, "email is duplication", "M001");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}

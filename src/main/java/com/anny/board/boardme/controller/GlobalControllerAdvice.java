package com.anny.board.boardme.controller;

import com.anny.board.boardme.dto.ErrorResponse;
import com.anny.board.boardme.global.exception.BusinessException;
import com.anny.board.boardme.util.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.METHOD_ARGUMENT_NOT_VALID, e.getBindingResult());
        return ResponseEntity.status(ErrorCode.METHOD_ARGUMENT_NOT_VALID.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }
}

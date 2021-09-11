package com.anny.board.boardme.dto;

import com.anny.board.boardme.util.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private String message;
    private int status;
    private String code;
    private List<FieldError> fieldErrors;

    public ErrorResponse(final ErrorCode errorCode, List<FieldError> fieldErrors) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.fieldErrors = fieldErrors;
    }

    public ErrorResponse(final ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
        this.fieldErrors = new ArrayList<>();
    }

    public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
        return new ErrorResponse(errorCode, FieldError.of(bindingResult));
    }
    public static ErrorResponse of(final ErrorCode errorCode){
        return new ErrorResponse(errorCode);
    }
    public static ErrorResponse of(final ErrorCode errorCode,List<FieldError>fieldErrors){
        return new ErrorResponse(errorCode,fieldErrors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class FieldError {
        private String field;
        private String reason;
        private String value;

        public FieldError(final String field, final String reason, final String value) {
            this.field = field;
            this.reason = reason;
            this.value = value;
        }

        public static List<FieldError> of(BindingResult bindingResult) {
            return bindingResult.getFieldErrors().stream().map(error -> {
                return new FieldError(error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString());
            }).collect(Collectors.toList());
        }
    }
}

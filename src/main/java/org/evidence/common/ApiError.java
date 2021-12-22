package org.evidence.common;

import java.time.LocalDateTime;
import java.util.List;

import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class ApiError {

    @Getter
    private HttpStatus status;
    @Getter
    private String message;
    @Getter
    private String detail;
    @Getter
    private List<FieldError> fieldErrors;
    @Getter
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(HttpStatus status, String message, String detail) {
        super();
        this.status = status;
        this.message = message;
        this.detail = detail;
    }

    public ApiError(HttpStatus status, String message, String detail, List<FieldError> fieldErrors) {
        super();
        this.status = status;
        this.message = message;
        this.detail = detail;
        this.fieldErrors = fieldErrors;
    }
}

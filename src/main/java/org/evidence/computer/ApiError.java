package org.evidence.computer;

import java.util.Arrays;
import java.util.List;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
class ApiError {

    @Getter
    private HttpStatus status;
    @Getter
    private String message;
    @Getter
    private List<String> errors;

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}

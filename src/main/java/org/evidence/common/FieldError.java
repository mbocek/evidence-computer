package org.evidence.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FieldError {

    @Getter
    private String object;

    @Getter
    private String field;

    @Getter
    private String message;
}

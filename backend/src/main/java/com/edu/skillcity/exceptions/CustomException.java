package com.edu.skillcity.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private HttpStatus status = null;

    private Object data = null;
    private String error_cause = null;

    public CustomException() {
        super();
    }

    public CustomException(
            String message
    ) {
        super(message);
    }

    public CustomException(
            HttpStatus status,
            String message
    ) {
        this(message);
        this.status = status;
    }

    public CustomException(
            HttpStatus status,
            String message,
            Object data,
            String error_cause
    ) {
        this(
                status,
                message
        );
        this.data = data;
        this.error_cause = error_cause;
    }
}

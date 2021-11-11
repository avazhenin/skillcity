package com.edu.skillcity.errors;

import com.edu.skillcity.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(
            Exception e
    ) {
        // casting the generic Exception e to CustomErrorException
        CustomException customException = (CustomException) e;

        HttpStatus status = customException.getStatus();
        // converting the stack trace to String
        String stackTrace = new String();
        for (int i = 0; i < customException.getStackTrace().length; i++) {
            if (i >= 10) break;
            stackTrace += customException.getStackTrace()[i].toString() + "\n";
        }

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        stackTrace,
                        customException.getData(),
                        customException.getError_cause()
                ),
                status
        );
    }

    // fallback method
    @ExceptionHandler(Exception.class) // exception handled
    public ResponseEntity handleExceptions(
            Exception e
    ) {
        // ... potential custom logic
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(
                new ErrorResponse(
                        status,
                        e.getMessage(),
                        stackTrace // specifying the stack trace in case of 500
                ),
                status
        );
    }
}

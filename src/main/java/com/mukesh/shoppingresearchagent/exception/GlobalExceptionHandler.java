package com.mukesh.shoppingresearchagent.exception;

import com.mukesh.shoppingresearchagent.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException ex
    ) {

        ErrorResponse response =
                new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        404
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        String errorMessage = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ErrorResponse response =
                new ErrorResponse(
                        LocalDateTime.now(),
                        errorMessage,
                        400
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(GeminiServiceException.class)
    public ResponseEntity<ErrorResponse> handleGeminiException(
            GeminiServiceException ex
    ) {

        ErrorResponse response =
                new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        503
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }

}
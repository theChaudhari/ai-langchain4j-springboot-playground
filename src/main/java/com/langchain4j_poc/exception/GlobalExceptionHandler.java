package com.langchain4j_poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            ResponseStatusException ex) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", ex.getStatusCode().value());
        response.put("error", "Validation Failed");
        response.put("message", ex.getReason()); // ✅ getReason() gives our custom message

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", 500);
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}

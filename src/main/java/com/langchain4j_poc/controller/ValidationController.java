package com.langchain4j_poc.controller;

import com.langchain4j_poc.request.ProductRequest;
import com.langchain4j_poc.request.UserRegistrationRequest;
import com.langchain4j_poc.request.ValidationResult;
import com.langchain4j_poc.service.impl.AiValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ValidationController {

    private final AiValidator aiValidator;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {

        ValidationResult result = aiValidator.validateOrThrow("User Registration", request);

        return ResponseEntity.ok(Map.of("message", "User registered successfully!", "confidenceScore", result.getConfidenceScore(), "warnings", result.getWarnings()));
    }


    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        ValidationResult result = aiValidator.validateOrThrow("Product", request);
        return ResponseEntity.ok(Map.of("message", "Product created successfully!", "confidenceScore",
                result.getConfidenceScore(), "warnings", result.getWarnings()));
    }


    @PostMapping("/validate/user")
    public ResponseEntity<ValidationResult> validateUser(@RequestBody UserRegistrationRequest request) {
        ValidationResult result = aiValidator.validateOrThrow("User Registration", request);
        return ResponseEntity.ok(result);
    }
}
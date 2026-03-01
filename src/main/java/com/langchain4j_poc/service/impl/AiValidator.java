package com.langchain4j_poc.service.impl;


import com.langchain4j_poc.request.ValidationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@Component
public class AiValidator {

    private final AiValidationService aiValidationService;

    public ValidationResult validateOrThrow(String requestType, Object request) {
        ValidationResult result = aiValidationService.validate(requestType, request);

        if (!result.isValid()) {
            String errorMessage = "Validation Failed: " +
                    String.join(", ", result.getErrors());

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    errorMessage  // ✅ This becomes getReason() in handler
            );
        }

        if (result.getConfidenceScore() < 50) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Suspicious data detected. Confidence score too low: "
                            + result.getConfidenceScore() + "%"
            );
        }

        return result;
    }
}
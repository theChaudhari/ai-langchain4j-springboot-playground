package com.langchain4j_poc.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
    private boolean valid;
    private List<String> errors;
    private List<String> warnings;
    private String suggestion;
    private int confidenceScore; // 0-100
}

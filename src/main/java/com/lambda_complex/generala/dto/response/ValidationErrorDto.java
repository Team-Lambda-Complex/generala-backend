package com.lambda_complex.generala.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorDto {
    private String field;
    private String reason;
    private String value;
}

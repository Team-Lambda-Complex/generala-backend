package com.lambda_complex.generala.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto<T> {
    private boolean success;
    private String message;
    private T errors;
}

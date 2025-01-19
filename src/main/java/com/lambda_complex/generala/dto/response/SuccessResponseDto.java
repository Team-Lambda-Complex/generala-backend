package com.lambda_complex.generala.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponseDto <T> {
    private boolean ok;
    private String message;
    private T data;
}

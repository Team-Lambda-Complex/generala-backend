package com.lambda_complex.generala.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SuccessfullyResponseDto {
    private boolean ok;
    private String message;
    private Object data;
}

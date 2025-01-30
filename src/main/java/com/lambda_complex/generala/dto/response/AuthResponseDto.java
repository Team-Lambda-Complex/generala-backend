package com.lambda_complex.generala.dto.response;

import com.lambda_complex.generala.dto.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private boolean success;
    private PlayerDto player;
}

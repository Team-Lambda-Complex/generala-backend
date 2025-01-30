package com.lambda_complex.generala.services.interfaces;

import com.lambda_complex.generala.dto.request.AuthRequestDto;
import com.lambda_complex.generala.dto.response.AuthResponseDto;

public interface IAuthService {
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto);
}

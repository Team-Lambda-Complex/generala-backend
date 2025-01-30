package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.AuthRequestDto;
import com.lambda_complex.generala.dto.response.AuthResponseDto;
import com.lambda_complex.generala.services.interfaces.IAuthService;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private IPlayerService playerService;
    public AuthService (IPlayerService playerService){
        this.playerService = playerService;
    }

    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        PlayerDto player = playerService.findByEmailAndPassword(
                authRequestDto.getEmail(),
                authRequestDto.getPassword()
        );
        return new AuthResponseDto(true, player);
    }
}

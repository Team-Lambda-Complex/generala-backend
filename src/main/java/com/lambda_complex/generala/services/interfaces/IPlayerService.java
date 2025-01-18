package com.lambda_complex.generala.services.interfaces;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.dto.response.SuccessfullyResponseDto;

import java.util.List;

public interface IPlayerService {
    public PlayerDto createPlayer(ReqPlayerDto reqPlayerDto);
    public List<PlayerDto> findAll();
}

package com.lambda_complex.generala.services.interfaces;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;

import java.util.List;

public interface IPlayerService {
    public PlayerDto createPlayer(ReqPlayerDto reqPlayerDto);
    public List<PlayerDto> findAll();
    public PlayerDto findByEmailAndPassword(String email, String password);
}

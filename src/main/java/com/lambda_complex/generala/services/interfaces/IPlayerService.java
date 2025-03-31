package com.lambda_complex.generala.services.interfaces;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.dto.response.MatchOverviewDto;

import java.util.List;

public interface IPlayerService {
    PlayerDto createPlayer(ReqPlayerDto reqPlayerDto);
    List<PlayerDto> findAll();
    PlayerDto findByEmailAndPassword(String email, String password);
    PlayerDto findById(Long id);
    List<MatchOverviewDto> findMatchesPlayedIn(Long id);
}

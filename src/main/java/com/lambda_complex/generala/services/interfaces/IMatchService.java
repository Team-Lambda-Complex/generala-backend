package com.lambda_complex.generala.services.interfaces;

import com.lambda_complex.generala.dto.request.NewMatchDto;
import com.lambda_complex.generala.dto.response.MatchOverviewDto;

public interface IMatchService {
    MatchOverviewDto createMatch(NewMatchDto newMatchDto, Long ownerId);
    MatchOverviewDto endMatch(Long id, Long ownerId);
}

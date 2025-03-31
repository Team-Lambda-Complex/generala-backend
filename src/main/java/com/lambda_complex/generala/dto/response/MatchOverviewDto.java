package com.lambda_complex.generala.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchOverviewDto {

    private Long id;
    private List<ScoreDto> players;

}

package com.lambda_complex.generala.controllers;

import com.lambda_complex.generala.dto.request.NewMatchDto;
import com.lambda_complex.generala.dto.response.MatchOverviewDto;
import com.lambda_complex.generala.dto.response.SuccessResponseDto;
import com.lambda_complex.generala.services.interfaces.IMatchService;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {
    private IPlayerService playerService;
    private IMatchService matchService;
    public MatchController (IPlayerService playerService, IMatchService matchService){
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponseDto<List<MatchOverviewDto>>> getMatchHistory(@PathVariable Long id){
        return new ResponseEntity<>(
                new SuccessResponseDto<>(
                        true,
                        "Success",
                        playerService.findMatchesPlayedIn(id)
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<SuccessResponseDto<List<MatchOverviewDto>>> getOwnedMatches(@PathVariable Long id){
        return new ResponseEntity<>(
                new SuccessResponseDto<>(
                        true,
                        "Success",
                        playerService.findMatchesOwned(id)
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<SuccessResponseDto<MatchOverviewDto>> createMatch(@PathVariable Long id,
                                                                            @RequestBody NewMatchDto newMatchDto){
        return new ResponseEntity<>(
                new SuccessResponseDto<>(
                        true,
                        "Match successfully created",
                        matchService.createMatch(newMatchDto, id)
                ),
                HttpStatus.CREATED
        );
    }
}

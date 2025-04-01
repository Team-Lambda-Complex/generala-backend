package com.lambda_complex.generala.controllers;

import com.lambda_complex.generala.dto.response.MatchOverviewDto;
import com.lambda_complex.generala.dto.response.SuccessResponseDto;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {
    private IPlayerService playerService;
    public MatchController (IPlayerService playerService){
        this.playerService = playerService;
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
}

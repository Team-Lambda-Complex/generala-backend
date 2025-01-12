package com.lambda_complex.generala.controllers;

import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.dto.response.SuccessfullyResponseDto;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private IPlayerService playerService;
    public PlayerController (IPlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@Valid @RequestBody ReqPlayerDto reqPlayerDto){
        return new ResponseEntity<>(
                playerService.createPlayer(reqPlayerDto),
                HttpStatus.CREATED
        );
    }

}

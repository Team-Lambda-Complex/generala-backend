package com.lambda_complex.generala.controllers;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.dto.response.SuccessResponseDto;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private IPlayerService playerService;
    public PlayerController (IPlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponseDto<PlayerDto>> createPlayer(@Valid @RequestBody ReqPlayerDto reqPlayerDto){

        return new ResponseEntity<>(
                new SuccessResponseDto<>(
                        true,
                        "Player successfully created",
                        playerService.createPlayer(reqPlayerDto)
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDto<List<PlayerDto>>> getAllPlayers(){
        return new ResponseEntity<>(
                new SuccessResponseDto<>(
                        true,
                        "Success",
                        playerService.findAll()
                ),
                HttpStatus.OK
        );
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> test(@Nullable @RequestParam String email, @Nullable @RequestParam String password){
//        PlayerDto jugador = playerService.findByEmailAndPassword(email, password);
//
//        return new ResponseEntity<>(jugador, HttpStatus.OK);
//    }

}

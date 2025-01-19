package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.entities.Player;
import com.lambda_complex.generala.repositories.interfaces.IPlayerRepository;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private ModelMapper mapper;

    @Mock
    private IPlayerRepository playerRepository;
    // Mockeo el repositorio y lo inyecto en el service;
    @InjectMocks
    private PlayerService playerService;

    @Test
    @DisplayName("Player creation success")
    void createPlayerTest(){
        // Arrange
        String name = "Carlitos Testy";
        String email = "ct@test.com";
        String password = "qwerty";

        ReqPlayerDto exampleDto = new ReqPlayerDto(name, email, password);

        Player expectedPlayer = new Player(
                1L, // ID generado por la base de datos o mock
                name,
                email,
                password,
                false, // isRegistered
                null, // registrationDate
                null, // creationDate
                null, // modificationDate
                null, // matchesOwned
                null, // matchesPlayedIn
                null  // plays
        );

        PlayerDto exampleResultDto = new PlayerDto(
                null,
                name,
                email,
                false,
                null,
                null,
                null
        );

        when(mapper.map(exampleDto, Player.class)).thenReturn(expectedPlayer);
        when(mapper.map(expectedPlayer, PlayerDto.class)).thenReturn(exampleResultDto);
        when( playerRepository.save(expectedPlayer) ).thenReturn(expectedPlayer);

        // Act
        PlayerDto result = playerService.createPlayer(exampleDto);

        // Assert
        System.out.println(result);
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertFalse(result.getIsRegistered());
    }


}

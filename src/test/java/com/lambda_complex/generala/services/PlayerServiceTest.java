package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.entities.Player;
import com.lambda_complex.generala.helpers.TestSubjects;
import com.lambda_complex.generala.repositories.interfaces.IPlayerRepository;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    private ModelMapper modelMapper;

    @Mock
    private ModelMapper mapper;

    @Mock
    private IPlayerRepository playerRepository;
    // Mockeo el repositorio y lo inyecto en el service;
    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("Player creation success")
    void createPlayerTest(){
        // Arrange
        ReqPlayerDto exampleDto = new ReqPlayerDto(
                TestSubjects.player1.getName(),
                TestSubjects.player1.getEmail(),
                TestSubjects.player1.getPassword()
        );

        Player expectedPlayer = TestSubjects.player1;

        PlayerDto exampleResultDto = modelMapper.map(TestSubjects.player1, PlayerDto.class);

        when(mapper.map(exampleDto, Player.class)).thenReturn(expectedPlayer);
        when(mapper.map(expectedPlayer, PlayerDto.class)).thenReturn(exampleResultDto);
        when( playerRepository.save(expectedPlayer) ).thenReturn(expectedPlayer);

        // Act
        PlayerDto result = playerService.createPlayer(exampleDto);

        // Assert
        System.out.println(result);
        assertEquals(TestSubjects.player1.getName(), result.getName());
        assertEquals(TestSubjects.player1.getEmail(), result.getEmail());
        assertFalse(result.getIsRegistered());
    }

    @Test
    @DisplayName("Players successfully obtained")
    void getAllPlayersTest(){
        // Arrange
        List<Player> payersList = new ArrayList<>();
        payersList.add(TestSubjects.player1);
        payersList.add(TestSubjects.player2);
        payersList.add(TestSubjects.player3);

        List<PlayerDto> expectedPlayerDtoList = new ArrayList<>();
        expectedPlayerDtoList.add(modelMapper.map(TestSubjects.player1, PlayerDto.class));
        expectedPlayerDtoList.add(modelMapper.map(TestSubjects.player2, PlayerDto.class));
        expectedPlayerDtoList.add(modelMapper.map(TestSubjects.player3, PlayerDto.class));
        // Act
        when(mapper.map(payersList.get(0), PlayerDto.class)).thenReturn(expectedPlayerDtoList.get(0));
        when(mapper.map(payersList.get(1), PlayerDto.class)).thenReturn(expectedPlayerDtoList.get(1));
        when(mapper.map(payersList.get(2), PlayerDto.class)).thenReturn(expectedPlayerDtoList.get(2));

        when(playerRepository.findAll()).thenReturn(payersList);

        List<PlayerDto> resultPlayersList = playerService.findAll();
        // Assert
        assertEquals(resultPlayersList.size(), expectedPlayerDtoList.size());
        assertEquals(resultPlayersList.get(0), expectedPlayerDtoList.get(0));
        assertEquals(resultPlayersList.get(1), expectedPlayerDtoList.get(1));
        assertEquals(resultPlayersList.get(2), expectedPlayerDtoList.get(2));
    }


}

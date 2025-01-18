package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    IPlayerService playerService;

    @Test
    @DisplayName("Player creation success")
    void createPlayerTest(){
        // Arrange
        String name = "Carlitos Testy";
        String email = "ct@test.com";
        String password = "qwerty";
        ReqPlayerDto exampleDto = new ReqPlayerDto(name, email, password);
        // Act
        // falta el mock
        PlayerDto result = playerService.createPlayer(exampleDto);
        // Assert
        System.out.println(result);
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertNotNull(result.getCreationDate());
        assertNull(result.getRegistrationDate());
        assertNotNull(result.getModificationDate());
        assertFalse(result.getIsRegistered());
    }


}

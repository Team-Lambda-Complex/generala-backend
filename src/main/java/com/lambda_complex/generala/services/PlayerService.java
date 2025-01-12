package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.entities.Player;
import com.lambda_complex.generala.repositories.interfaces.IPlayerRepository;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService {
    private IPlayerRepository playerRepository;
    public PlayerService(IPlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerDto createPlayer(ReqPlayerDto reqPlayerDto) {

        Player savedPlayer = playerRepository.save(new Player(
                reqPlayerDto.getName(),
                reqPlayerDto.getEmail(),
                reqPlayerDto.getPassword()
        ));

        return new PlayerDto(
                savedPlayer.getId(),
                savedPlayer.getName(),
                savedPlayer.getEmail(),
                savedPlayer.getIsRegistered(),
                savedPlayer.getRegistrationDate(),
                savedPlayer.getCreationDate(),
                savedPlayer.getModificationDate()
        );
    }
}

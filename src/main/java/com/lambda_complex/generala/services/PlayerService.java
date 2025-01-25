package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.entities.Player;
import com.lambda_complex.generala.exceptions.EmailAlreadyExistsException;
import com.lambda_complex.generala.repositories.interfaces.IPlayerRepository;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {
    private IPlayerRepository playerRepository;
    public PlayerService(IPlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Autowired
    ModelMapper mapper;

    @Override
    public PlayerDto createPlayer(ReqPlayerDto reqPlayerDto) {

        Optional<Player> exists = playerRepository.findByEmail(reqPlayerDto.getEmail());

        if (exists.isPresent()) throw new EmailAlreadyExistsException("Email already in use", reqPlayerDto.getEmail());

        Player newPlayer = mapper.map(reqPlayerDto, Player.class);
        newPlayer.setIsRegistered(false);

        Player savedPlayer = playerRepository.save(newPlayer);

        return mapper.map(savedPlayer, PlayerDto.class);
    }

    @Override
    public List<PlayerDto> findAll() {
        List<Player> allPlayers = playerRepository.findAll();

        return allPlayers.stream().map( player ->  mapper.map(player, PlayerDto.class) ).toList();
    }
}

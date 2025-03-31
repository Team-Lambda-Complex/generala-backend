package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.PlayerDto;
import com.lambda_complex.generala.dto.request.ReqPlayerDto;
import com.lambda_complex.generala.dto.response.MatchOverviewDto;
import com.lambda_complex.generala.dto.response.ScoreDto;
import com.lambda_complex.generala.entities.MatchPlayer;
import com.lambda_complex.generala.entities.Player;
import com.lambda_complex.generala.exceptions.EmailAlreadyExistsException;
import com.lambda_complex.generala.exceptions.PlayerNotFoundException;
import com.lambda_complex.generala.repositories.interfaces.IMatchPlayerRepository;
import com.lambda_complex.generala.repositories.interfaces.IPlayerRepository;
import com.lambda_complex.generala.services.interfaces.IPlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements IPlayerService {
    private IPlayerRepository playerRepository;
    private IMatchPlayerRepository matchPlayerRepository;

    public PlayerService(IPlayerRepository playerRepository, IMatchPlayerRepository matchPlayerRepository){
        this.playerRepository = playerRepository;
        this.matchPlayerRepository = matchPlayerRepository;
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

    @Override
    public PlayerDto findByEmailAndPassword(String email, String password) {
        Optional<Player> exists = playerRepository.findByEmailAndPassword(email, password);

        if (exists.isEmpty()) throw new PlayerNotFoundException("email", email);

        return mapper.map(exists.get(), PlayerDto.class);
    }

    @Override
    public PlayerDto findById(Long id) {
        Optional<Player> exists = playerRepository.findById(id);

        if (exists.isEmpty()) throw new PlayerNotFoundException("id", id.toString());

        return mapper.map(exists.get(), PlayerDto.class);
    }

    @Override
    public List<MatchOverviewDto> findMatchesPlayedIn(Long id) {
        Optional<Player> exists = playerRepository.findById(id);

        if (exists.isEmpty()) throw new PlayerNotFoundException("id", id.toString());

        List<MatchPlayer> matchesPlayedIn = matchPlayerRepository.findByPlayer(exists.get());

        return matchesPlayedIn.stream().map(m -> {
            List<MatchPlayer> players = matchPlayerRepository.findByMatch(m.getMatch());
            List<ScoreDto> playerList = players.stream().map(p ->
                    new ScoreDto(p.getPlayer().getName(), p.getScore())).toList();
            return new MatchOverviewDto(m.getId(), playerList);
        }).toList();
    }
}

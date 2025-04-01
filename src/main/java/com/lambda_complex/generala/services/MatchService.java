package com.lambda_complex.generala.services;

import com.lambda_complex.generala.dto.request.NewMatchDto;
import com.lambda_complex.generala.dto.response.MatchOverviewDto;
import com.lambda_complex.generala.dto.response.ScoreDto;
import com.lambda_complex.generala.entities.Match;
import com.lambda_complex.generala.entities.MatchPlayer;
import com.lambda_complex.generala.entities.Player;
import com.lambda_complex.generala.exceptions.MatchNotFoundException;
import com.lambda_complex.generala.exceptions.OwnershipException;
import com.lambda_complex.generala.exceptions.PlayerNotFoundException;
import com.lambda_complex.generala.exceptions.PlayersNotFoundException;
import com.lambda_complex.generala.repositories.interfaces.IMatchPlayerRepository;
import com.lambda_complex.generala.repositories.interfaces.IMatchRepository;
import com.lambda_complex.generala.repositories.interfaces.IPlayerRepository;
import com.lambda_complex.generala.services.interfaces.IMatchService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService implements IMatchService {
    IMatchRepository matchRepository;
    IMatchPlayerRepository matchPlayerRepository;
    IPlayerRepository playerRepository;

    public MatchService(IMatchRepository matchRepository,
                        IMatchPlayerRepository matchPlayerRepository,
                        IPlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.matchPlayerRepository = matchPlayerRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public MatchOverviewDto createMatch(NewMatchDto newMatchDto, Long ownerId) {
        Optional<Player> oExists = playerRepository.findById(ownerId);

        if (oExists.isEmpty()) throw new PlayerNotFoundException("id", ownerId.toString());

        Match match = new Match();
        match.setIsActive(true);
        match.setOwner(oExists.get());

        Match createdMatch = matchRepository.save(match);

        List<String> notFoundEmails = new ArrayList<>();
        List<MatchPlayer> playerList = new ArrayList<>();
        newMatchDto.getEmails().forEach(p -> {
            Optional<Player> exists = playerRepository.findByEmail(p);

            if (exists.isEmpty()) notFoundEmails.add(p);
            if (exists.isPresent()) {
                MatchPlayer player = new MatchPlayer();
                player.setPlayer(exists.get());
                player.setMatch(createdMatch);
                playerList.add(player);
            }
        });

        if (!notFoundEmails.isEmpty()) throw new PlayersNotFoundException(notFoundEmails);

        createdMatch.setPlayers(new HashSet<>(playerList));

        matchRepository.save(createdMatch);

        List<MatchPlayer> players = matchPlayerRepository.findByMatch(createdMatch);
        List<ScoreDto> scoreList = players.stream()
                .map(p ->
                        new ScoreDto(p.getPlayer().getName(), p.getScore()))
                .toList();

        return new MatchOverviewDto(createdMatch.getId(), createdMatch.getIsActive(), scoreList);
    }

    @Override
    public MatchOverviewDto endMatch(Long id, Long ownerId) {
        Optional<Match> mExists = matchRepository.findById(id);
        Optional<Player> oExists = playerRepository.findById(ownerId);

        if (mExists.isEmpty()) throw new MatchNotFoundException("id", id.toString());
        if (oExists.isEmpty()) throw new PlayerNotFoundException("id", ownerId.toString());
        if (!mExists.get().getOwner().getId().equals(ownerId)) throw new OwnershipException();

        Match endedMatch = mExists.get();
        endedMatch.setIsActive(false);
        matchRepository.save(endedMatch);

        List<MatchPlayer> players = matchPlayerRepository.findByMatch(endedMatch);
        List<ScoreDto> playerList = players.stream()
                .map(p ->
                        new ScoreDto(p.getPlayer().getName(), p.getScore()))
                .toList();

        return new MatchOverviewDto(endedMatch.getId(), endedMatch.getIsActive(), playerList);
    }
}

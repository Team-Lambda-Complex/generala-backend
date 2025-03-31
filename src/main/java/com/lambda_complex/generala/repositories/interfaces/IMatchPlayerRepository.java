package com.lambda_complex.generala.repositories.interfaces;

import com.lambda_complex.generala.entities.Match;
import com.lambda_complex.generala.entities.MatchPlayer;
import com.lambda_complex.generala.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMatchPlayerRepository extends JpaRepository<MatchPlayer, Long> {

    List<MatchPlayer> findByPlayer(Player player);
    List<MatchPlayer> findByMatch(Match match);
}

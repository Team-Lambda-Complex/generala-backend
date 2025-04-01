package com.lambda_complex.generala.repositories.interfaces;

import com.lambda_complex.generala.entities.Match;
import com.lambda_complex.generala.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByOwner(Player owner);
}

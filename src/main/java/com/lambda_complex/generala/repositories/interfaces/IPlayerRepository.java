package com.lambda_complex.generala.repositories.interfaces;

import com.lambda_complex.generala.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByEmail(String email);
    Optional<Player> findByEmailAndPassword(String email, String password);
}

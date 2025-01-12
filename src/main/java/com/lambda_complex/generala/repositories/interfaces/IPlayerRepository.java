package com.lambda_complex.generala.repositories.interfaces;

import com.lambda_complex.generala.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Long> {}

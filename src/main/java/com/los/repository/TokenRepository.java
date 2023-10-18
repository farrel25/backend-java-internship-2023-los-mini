package com.los.repository;

import com.los.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    @Query(
            value = "SELECT t " +
                    "FROM Token t " +
                    "JOIN t.masterUser mu " +
                    "WHERE mu.id = :userId " +
                    "AND t.isRevoked = false"
    )
    List<Token> findAllValidTokenByUserId(Long userId);
}

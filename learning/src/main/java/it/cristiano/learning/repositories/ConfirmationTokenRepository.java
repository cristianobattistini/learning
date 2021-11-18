package it.cristiano.learning.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.cristiano.learning.entities.ConfirmationToken;



@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    
    Optional<ConfirmationToken> findByToken(String token);
    
}

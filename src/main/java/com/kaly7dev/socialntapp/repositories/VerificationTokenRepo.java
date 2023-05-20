package com.kaly7dev.socialntapp.repositories;

import com.kaly7dev.socialntapp.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByToken(String token);
}

package com.kaly7dev.socialntapp.repositories;

import com.kaly7dev.socialntapp.entities.SubsocialNt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubsocialNtRepo extends JpaRepository<SubsocialNt, Long> {
    Optional<SubsocialNt> findByName(String SubsocialNtName);
}

package com.kaly7dev.socialntapp.repositories;

import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.User;
import com.kaly7dev.socialntapp.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VoteRepo extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);
}

package com.kaly7dev.socialntapp.repositories;

import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import com.kaly7dev.socialntapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllBySubsocialNt(SubsocialNt subsocialNt);
    List<Post> findAllByUser(User user);
}

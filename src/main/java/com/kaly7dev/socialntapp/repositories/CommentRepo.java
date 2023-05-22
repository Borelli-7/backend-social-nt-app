package com.kaly7dev.socialntapp.repositories;

import com.kaly7dev.socialntapp.entities.Comment;
import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);
}

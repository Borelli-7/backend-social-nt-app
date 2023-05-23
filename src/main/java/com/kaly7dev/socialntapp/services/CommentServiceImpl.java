package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.CommentsDto;
import com.kaly7dev.socialntapp.coreapi.exceptions.UserNotFoundException;
import com.kaly7dev.socialntapp.coreapi.mappers.CommentMapper;
import com.kaly7dev.socialntapp.coreapi.exceptions.PostNotFoundException;
import com.kaly7dev.socialntapp.entities.Comment;
import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.User;
import com.kaly7dev.socialntapp.model.NotificationEmail;
import com.kaly7dev.socialntapp.repositories.CommentRepo;
import com.kaly7dev.socialntapp.repositories.PostRepo;
import com.kaly7dev.socialntapp.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private static final String POST_URL = "";
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public void createComment(CommentsDto commentsDto) {
        Post post= postRepo.findById(commentsDto.getPostId())
                .orElseThrow(()-> new PostNotFoundException(
                        "Post Not found with Id: "+commentsDto.getPostId().toString()));
        Comment comment= commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepo.save(comment);
        /**
         * To checking on this line instruction
         * "post.getUser().getUsername()" or " comment.getUser().getUsername()"
         */
        String message= mailContentBuilder.build(
                post.getUser().getUsername() + "posted a comment on your post. " + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentsDto> getCommentListForPost(Long postId) {
        Post post= postRepo.findById(postId)
                .orElseThrow(()->new PostNotFoundException("Post not found with ID: "+postId.toString()));
        return commentRepo.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentsDto> getCommentListForUser(String username) {
        User user= userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with this username: "+username));
        return commentRepo.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(
                new NotificationEmail(
                        user.getUsername() + " commented on your post", user.getEmail(), message));
    }
}

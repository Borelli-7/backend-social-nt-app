package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.CommentsDto;

import java.util.List;

public interface CommentService {
    void createComment(CommentsDto commentsDto);

    List<CommentsDto> getCommentListForPost(Long postId);

    List<CommentsDto> getCommentListForUser(String username);
}


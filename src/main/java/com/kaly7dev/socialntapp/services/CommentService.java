package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.CommentsDto;

public interface CommentService {
    void createComment(CommentsDto commentsDto);
}


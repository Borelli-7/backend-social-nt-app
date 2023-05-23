package com.kaly7dev.socialntapp.controllers;

import com.kaly7dev.socialntapp.coreapi.dtos.CommentsDto;
import com.kaly7dev.socialntapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/create")
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto){
        commentService.createComment(commentsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

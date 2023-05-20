package com.kaly7dev.socialntapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaly7dev.socialntapp.coreapi.dtos.PostRequest;
import com.kaly7dev.socialntapp.services.PostService;


@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PostRequest postRequest){
        postService.create(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

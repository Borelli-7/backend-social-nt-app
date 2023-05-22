package com.kaly7dev.socialntapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kaly7dev.socialntapp.coreapi.dtos.PostRequest;
import com.kaly7dev.socialntapp.coreapi.dtos.PostResponse;
import com.kaly7dev.socialntapp.services.PostService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


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
    @GetMapping("/lists")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.status(OK).body(postService.getAllPosts());
    }
}

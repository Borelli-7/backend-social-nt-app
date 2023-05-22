package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.PostRequest;
import com.kaly7dev.socialntapp.coreapi.dtos.PostResponse;

import java.util.List;

public interface PostService {
    void create(PostRequest postRequest);

    List<PostResponse> getAllPosts();

    PostResponse getPost(Long id);
}

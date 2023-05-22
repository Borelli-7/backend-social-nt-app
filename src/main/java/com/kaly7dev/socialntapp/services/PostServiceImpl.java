package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.PostRequest;
import com.kaly7dev.socialntapp.coreapi.dtos.PostResponse;
import com.kaly7dev.socialntapp.coreapi.exceptions.SubsocialNtNotFoundException;
import com.kaly7dev.socialntapp.coreapi.mappers.PostMapper;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import com.kaly7dev.socialntapp.repositories.PostRepo;
import com.kaly7dev.socialntapp.repositories.SubsocialNtRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final SubsocialNtRepo subsocialNtRepo;
    private final PostMapper postMapper;
    private final AuthService authService;
    @Override
    public void create(PostRequest postRequest) {
        SubsocialNt subsocialNt= subsocialNtRepo.findByName(postRequest.getSubsocialntName())
                .orElseThrow(()->new SubsocialNtNotFoundException(postRequest.getSubsocialntName()));
        postRepo.save(postMapper.map(postRequest, subsocialNt, authService.getCurrentUser()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepo.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .toList();
    }
}

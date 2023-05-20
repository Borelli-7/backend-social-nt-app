package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.PostRequest;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import com.kaly7dev.socialntapp.repositories.PostRepo;
import com.kaly7dev.socialntapp.repositories.SubsocialNtRepo;
import com.kaly7dev.socialntapp.coreapi.exceptions.SubsocialNtNotFoundException;
import com.kaly7dev.socialntapp.coreapi.mappers.PostMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

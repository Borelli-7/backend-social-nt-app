package com.kaly7dev.socialntapp.controllers;

import com.kaly7dev.socialntapp.coreapi.dtos.VoteDto;
import com.kaly7dev.socialntapp.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;
    @PostMapping
    public ResponseEntity<Void> vote(VoteDto voteDto){
        voteService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

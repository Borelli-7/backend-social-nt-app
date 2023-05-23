package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.VoteDto;

public interface VoteService {
    void vote(VoteDto voteDto);
}

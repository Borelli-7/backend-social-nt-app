package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.VoteDto;
import com.kaly7dev.socialntapp.coreapi.exceptions.PostNotFoundException;
import com.kaly7dev.socialntapp.coreapi.exceptions.SocialNtException;
import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.Vote;
import com.kaly7dev.socialntapp.repositories.PostRepo;
import com.kaly7dev.socialntapp.repositories.VoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kaly7dev.socialntapp.coreapi.enums.VoteType.UPVOTE;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final PostRepo postRepo;
    private final VoteRepo voteRepo;
    private final AuthService authService;
    @Override
    @Transactional
    public void vote(VoteDto voteDto) {
        Post post= postRepo.findById(voteDto.getPostId())
                .orElseThrow(()->new PostNotFoundException("Post not found with ID:"+voteDto.getPostId().toString()));
        Optional<Vote> voteByPostAndUser=
                voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if(voteByPostAndUser.isPresent()
                && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())){
            throw new SocialNtException(" You have already"+voteDto.getVoteType()+"'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())){
            post.setVoteCount(post.getVoteCount() +1);
        }else {
            post.setVoteCount(post.getVoteCount() -1);
        }
        voteRepo.save(mapToVote(voteDto, post));
        postRepo.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}

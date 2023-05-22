package com.kaly7dev.socialntapp.coreapi.mappers;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.kaly7dev.socialntapp.coreapi.dtos.PostRequest;
import com.kaly7dev.socialntapp.coreapi.dtos.PostResponse;
import com.kaly7dev.socialntapp.coreapi.enums.VoteType;
import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import com.kaly7dev.socialntapp.entities.User;
import com.kaly7dev.socialntapp.entities.Vote;
import com.kaly7dev.socialntapp.repositories.CommentRepo;
import com.kaly7dev.socialntapp.repositories.VoteRepo;
import com.kaly7dev.socialntapp.services.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.kaly7dev.socialntapp.coreapi.enums.VoteType.DOWNVOTE;
import static com.kaly7dev.socialntapp.coreapi.enums.VoteType.UPVOTE;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private AuthService authService;
    @Autowired
    private VoteRepo voteRepo;

    @Mapping(target= "createdDate", expression= "java(java.time.Instant.now())")
    @Mapping(target = "description", source= "postRequest.description")
    @Mapping(target = "subsocialNt", source = "subsocialNt")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source="user")
    public abstract Post map(PostRequest postRequest, SubsocialNt subsocialNt, User user);
    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subsocialNtName", source = "subsocialNt.name")
    @Mapping(target = "userName", source ="user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post){
        return commentRepo.findByPost(post).size();
    }
    String getDuration(Post post){
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
    boolean isPostUpVoted(Post post){
        return checkVoteType(post, UPVOTE);
    }
    boolean isPostDownVoted(Post post){
        return checkVoteType(post, DOWNVOTE);
    }
    private boolean checkVoteType(Post post, VoteType voteType) {
        if(authService.isLonggedIn()){
            Optional<Vote> voteForPostByUser=
                    voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());
            return voteForPostByUser.filter(vote ->vote.getVoteType().equals(voteType)).isPresent();
        }
        return false;
    }

}

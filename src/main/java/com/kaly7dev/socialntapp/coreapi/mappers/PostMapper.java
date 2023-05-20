package com.kaly7dev.socialntapp.coreapi.mappers;

import com.kaly7dev.socialntapp.coreapi.dtos.PostRequest;
import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import com.kaly7dev.socialntapp.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Mapping(target= "createdDate", expression= "java(java.time.Instant.now())")
    @Mapping(target = "description", source= "postRequest.description")
    @Mapping(target = "subsocialNt", source = "subsocialNt")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source="user")
    public abstract Post map(PostRequest postRequest, SubsocialNt subsocialNt, User user);
}

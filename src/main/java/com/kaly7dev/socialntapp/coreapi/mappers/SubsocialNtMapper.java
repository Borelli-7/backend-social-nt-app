package com.kaly7dev.socialntapp.coreapi.mappers;

import com.kaly7dev.socialntapp.coreapi.dtos.SubsocialNtDto;
import com.kaly7dev.socialntapp.entities.Post;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsocialNtMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subsocialNt.getPostList()))")
    SubsocialNtDto mapToDto(SubsocialNt subsocialNt);

    default Integer mapPosts(List<Post> numberOfPosts){
        return numberOfPosts.size();
    }
    @InheritInverseConfiguration
    @Mapping(target = "postList", ignore = true)
    SubsocialNt mapToSubsocialNt(SubsocialNtDto subsocialNtDto);
}

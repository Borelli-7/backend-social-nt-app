package com.kaly7dev.socialntapp.coreapi.dtos;

import com.kaly7dev.socialntapp.coreapi.enums.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}

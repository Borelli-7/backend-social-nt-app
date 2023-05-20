package com.kaly7dev.socialntapp.coreapi.enums;

import com.kaly7dev.socialntapp.coreapi.exceptions.SocialNtException;

import java.util.Arrays;
public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;
    private int direction;
    VoteType(int direction) {
    }
    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new SocialNtException("Vote not Found"));
    }
    private Integer getDirection() {
        return direction;
    }
}

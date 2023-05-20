package com.kaly7dev.socialntapp.coreapi.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    private Long postId;
    @NotNull(message = "Post Name cannot be empty or Null ")
    private String postName;
    private String subsocialntName;
    private String url;
    private String description;
}

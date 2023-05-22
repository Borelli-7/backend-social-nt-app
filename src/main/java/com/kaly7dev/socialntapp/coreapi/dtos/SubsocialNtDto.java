package com.kaly7dev.socialntapp.coreapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubsocialNtDto {
    private Long id;
    @NotBlank(message = "The Community name is required")
    private String name;
    @NotBlank(message = "The community description is required")
    private String description;
    private Integer numberOfPosts;
}

package com.kaly7dev.socialntapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubsocialNt {
    @Id
    @GeneratedValue(strategy= IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(fetch = LAZY)
    private List<Post> postList;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    private User user;
}

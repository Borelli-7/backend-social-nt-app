package com.kaly7dev.socialntapp.controllers;

import com.kaly7dev.socialntapp.coreapi.dtos.PostResponse;
import com.kaly7dev.socialntapp.services.PostService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest {
    @MockBean
    private PostService postService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName(" Should list all posts when making a get request to endpoint - /api/post/lists")
    @WithMockUser(username = "test")
    void create() throws Exception {
        PostResponse postResponse1 = new PostResponse(1L, "Post Name", "http://url.site1",
                "description", "User 1", "Subsocialnt name", 0,
                0, " 1 day ago", false, false);
        PostResponse postResponse2 = new PostResponse(2L, "Post Name2", "http://url.site2",
                "description", "User 2", "Subsocialnt name", 0,
                0, " 2 day ago", false, false);

        Mockito.when(postService.getAllPosts())
                .thenReturn(asList(postResponse1, postResponse2));

        mockMvc.perform(get("/api/post/lists"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$.[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.[0].postName", Matchers.is("Post Name")))
                .andExpect(jsonPath("$.[0].url", Matchers.is("http://url.site1")))
                .andExpect(jsonPath("$.[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$.[1].postName", Matchers.is("Post Name2")))
                .andExpect(jsonPath("$.[1].url", Matchers.is("http://url.site2")));

    }

    @Test
    void getAllPosts() {
    }

    @Test
    void getPost() {
    }

    @Test
    void getPostListBySubsocialNt() {
    }

    @Test
    void getPostListByUsername() {
    }
}
package com.example.questapp.controllers;

import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.requests.CreatePostRequest;
import com.example.questapp.business.requests.UpdatePostRequest;
import com.example.questapp.business.responses.CreatePostResponse;
import com.example.questapp.business.responses.GetAllPostResponse;
import com.example.questapp.business.responses.GetPostByIdResponse;
import com.example.questapp.business.responses.UpdatePostResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @GetMapping
    public List<GetAllPostResponse> getAll(@RequestParam Optional<Long> userId) {
        return this.postService.getAll(userId);
    }

    @GetMapping("/{id}")
    public GetPostByIdResponse getById(@PathVariable Long id) {
        return this.postService.getPostById(id);
    }

    @PostMapping
    public CreatePostResponse createPost(@RequestBody CreatePostRequest createPostRequest) {
        return this.postService.createPost(createPostRequest);
    }

    @PutMapping("/{id}")
    public UpdatePostResponse updatePost(@RequestBody UpdatePostRequest updatePostRequest, @PathVariable Long id) {
        return this.postService.updatePost(updatePostRequest, id);
    }

    @DeleteMapping("/{id}")
    public Long deletePost(@PathVariable Long id) {
        return this.postService.deletePost(id);
    }
}

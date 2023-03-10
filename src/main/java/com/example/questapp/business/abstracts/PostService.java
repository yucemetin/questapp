package com.example.questapp.business.abstracts;

import com.example.questapp.business.requests.CreatePostRequest;
import com.example.questapp.business.requests.UpdatePostRequest;
import com.example.questapp.business.responses.post.CreatePostResponse;
import com.example.questapp.business.responses.post.GetAllPostResponse;
import com.example.questapp.business.responses.post.GetPostByIdResponse;
import com.example.questapp.business.responses.post.UpdatePostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<GetAllPostResponse> getAll(Optional<Long> userId);

    GetPostByIdResponse getPostById(Long id);

    CreatePostResponse createPost(CreatePostRequest createPostRequest);

    UpdatePostResponse updatePost(UpdatePostRequest updatePostRequest, Long id);

    Long deletePost(Long id);
}

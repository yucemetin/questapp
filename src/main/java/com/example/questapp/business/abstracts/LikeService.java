package com.example.questapp.business.abstracts;

import com.example.questapp.business.requests.CreateLikeRequest;
import com.example.questapp.business.responses.like.CreateLikeResponse;
import com.example.questapp.business.responses.like.GetAllLikeResponse;
import com.example.questapp.business.responses.like.GetLikeByIdResponse;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<GetAllLikeResponse> getAll(Optional<Long> userId, Optional<Long> postId);

    GetLikeByIdResponse getById(Long id);

    CreateLikeResponse createLike(CreateLikeRequest createLikeRequest);

    Long deleteLike(Long id);
}

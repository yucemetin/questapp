package com.example.questapp.controllers;

import com.example.questapp.business.abstracts.LikeService;
import com.example.questapp.business.requests.CreateLikeRequest;
import com.example.questapp.business.responses.like.CreateLikeResponse;
import com.example.questapp.business.responses.like.GetAllLikeResponse;
import com.example.questapp.business.responses.like.GetLikeByIdResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/likes")
public class LikeController {
    private LikeService likeService;

    @GetMapping
    List<GetAllLikeResponse> getAll(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return this.likeService.getAll(userId, postId);
    }

    @GetMapping("/{id}")
    GetLikeByIdResponse getById(@PathVariable Long id) {
        return this.likeService.getById(id);
    }

    @PostMapping
    CreateLikeResponse createLike(@RequestBody CreateLikeRequest createLikeRequest) {
        return this.likeService.createLike(createLikeRequest);
    }

    @DeleteMapping("/{id}")
    Long deleteLike(@PathVariable Long id) {
        return this.likeService.deleteLike(id);
    }
}

package com.example.questapp.business.concretes;

import com.example.questapp.business.abstracts.LikeService;
import com.example.questapp.business.requests.CreateLikeRequest;
import com.example.questapp.business.responses.like.CreateLikeResponse;
import com.example.questapp.business.responses.like.GetAllLikeResponse;
import com.example.questapp.business.responses.like.GetLikeByIdResponse;
import com.example.questapp.core.utilities.mappers.ModelMapperService;
import com.example.questapp.dataAccess.LikeRepository;
import com.example.questapp.entities.Like;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeManager implements LikeService {
    private LikeRepository likeRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllLikeResponse> getAll(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            List<Like> likes = this.likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
            List<GetAllLikeResponse> getAllLikeResponses = likes.stream().map(like -> this.modelMapperService.forResponse().map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
            return getAllLikeResponses;
        } else if (userId.isPresent()) {
            List<Like> likes = this.likeRepository.findByUserId(userId.get());
            List<GetAllLikeResponse> getAllLikeResponses = likes.stream().map(like -> this.modelMapperService.forResponse().map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
            return getAllLikeResponses;
        } else if (postId.isPresent()) {
            List<Like> likes = this.likeRepository.findByPostId(postId.get());
            List<GetAllLikeResponse> getAllLikeResponses = likes.stream().map(like -> this.modelMapperService.forResponse().map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
            return getAllLikeResponses;
        } else {
            List<Like> likes = this.likeRepository.findAll();
            List<GetAllLikeResponse> getAllLikeResponses = likes.stream().map(like -> this.modelMapperService.forResponse().map(like, GetAllLikeResponse.class)).collect(Collectors.toList());
            return getAllLikeResponses;
        }
    }

    @Override
    public GetLikeByIdResponse getById(Long id) {
        Like like = this.likeRepository.findById(id).orElseThrow();
        return this.modelMapperService.forResponse().map(like, GetLikeByIdResponse.class);
    }

    @Override
    public CreateLikeResponse createLike(CreateLikeRequest createLikeRequest) {
        Like like = this.modelMapperService.forRequest().map(createLikeRequest, Like.class);
        this.likeRepository.save(like);
        return this.modelMapperService.forResponse().map(like, CreateLikeResponse.class);
    }

    @Override
    public Long deleteLike(Long id) {
        Like like = this.likeRepository.findById(id).orElseThrow();
        this.likeRepository.deleteById(id);
        return like.getId();
    }
}

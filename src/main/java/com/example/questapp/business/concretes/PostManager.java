package com.example.questapp.business.concretes;

import com.example.questapp.business.abstracts.PostService;
import com.example.questapp.business.requests.CreatePostRequest;
import com.example.questapp.business.requests.UpdatePostRequest;
import com.example.questapp.business.responses.post.CreatePostResponse;
import com.example.questapp.business.responses.post.GetAllPostResponse;
import com.example.questapp.business.responses.post.GetPostByIdResponse;
import com.example.questapp.business.responses.post.UpdatePostResponse;
import com.example.questapp.core.utilities.mappers.ModelMapperService;
import com.example.questapp.dataAccess.PostRepository;
import com.example.questapp.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostManager implements PostService {
    private PostRepository postRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllPostResponse> getAll(Optional<Long> userId) {
        if (userId.isPresent()) {
            List<Post> posts = this.postRepository.findByUserId(userId.get());
            List<GetAllPostResponse> getAllPostResponses = posts.stream().map(post -> this.modelMapperService.forResponse().map(post, GetAllPostResponse.class)).collect(Collectors.toList());
            return getAllPostResponses;
        } else {
            List<Post> posts = this.postRepository.findAll();
            List<GetAllPostResponse> getAllPostResponses = posts.stream().map(post -> this.modelMapperService.forResponse().map(post, GetAllPostResponse.class)).collect(Collectors.toList());
            return getAllPostResponses;
        }
    }

    @Override
    public GetPostByIdResponse getPostById(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow();
        return this.modelMapperService.forResponse().map(post, GetPostByIdResponse.class);
    }

    @Override
    public CreatePostResponse createPost(CreatePostRequest createPostRequest) {
        Post post = this.modelMapperService.forRequest().map(createPostRequest, Post.class);
        this.postRepository.save(post);
        CreatePostResponse postResponse = this.modelMapperService.forResponse().map(post, CreatePostResponse.class);
        return postResponse;
    }

    @Override
    public UpdatePostResponse updatePost(UpdatePostRequest updatePostRequest, Long id) {
        Post oldPost = this.postRepository.findById(id).orElseThrow();
        Post post = this.modelMapperService.forRequest().map(updatePostRequest, Post.class);
        post.setUser(oldPost.getUser());
        this.postRepository.save(post);
        return this.modelMapperService.forResponse().map(post, UpdatePostResponse.class);
    }

    @Override
    public Long deletePost(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow();
        this.postRepository.deleteById(id);
        return post.getId();
    }
}

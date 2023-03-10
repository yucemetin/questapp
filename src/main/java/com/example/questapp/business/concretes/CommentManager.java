package com.example.questapp.business.concretes;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.business.requests.CreateCommentRequest;
import com.example.questapp.business.requests.UpdateCommentRequest;
import com.example.questapp.business.responses.comment.CreateCommentResponse;
import com.example.questapp.business.responses.comment.GetAllCommentsResponse;
import com.example.questapp.business.responses.comment.GetCommentByIdResponse;
import com.example.questapp.business.responses.comment.UpdateCommentResponse;
import com.example.questapp.core.utilities.mappers.ModelMapperService;
import com.example.questapp.dataAccess.CommentRepository;
import com.example.questapp.entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private CommentRepository commentRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllCommentsResponse> getAll(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            List<Comment> comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
            List<GetAllCommentsResponse> getAllCommentsResponses = comments.stream().map(comment -> this.modelMapperService.forResponse().map(comment, GetAllCommentsResponse.class)).collect(Collectors.toList());
            return getAllCommentsResponses;
        } else if (userId.isPresent()) {
            List<Comment> comments = commentRepository.findByUserId(userId.get());
            List<GetAllCommentsResponse> getAllCommentsResponses = comments.stream().map(comment -> this.modelMapperService.forResponse().map(comment, GetAllCommentsResponse.class)).collect(Collectors.toList());
            return getAllCommentsResponses;
        } else if (postId.isPresent()) {
            List<Comment> comments = commentRepository.findByPostId(postId.get());
            List<GetAllCommentsResponse> getAllCommentsResponses = comments.stream().map(comment -> this.modelMapperService.forResponse().map(comment, GetAllCommentsResponse.class)).collect(Collectors.toList());
            return getAllCommentsResponses;
        } else {
            List<Comment> comments = commentRepository.findAll();
            List<GetAllCommentsResponse> getAllCommentsResponses = comments.stream().map(comment -> this.modelMapperService.forResponse().map(comment, GetAllCommentsResponse.class)).collect(Collectors.toList());
            return getAllCommentsResponses;
        }

    }

    @Override
    public GetCommentByIdResponse getById(Long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow();
        return this.modelMapperService.forResponse().map(comment, GetCommentByIdResponse.class);
    }

    @Override
    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest) {
        Comment comment = this.modelMapperService.forRequest().map(createCommentRequest, Comment.class);
        this.commentRepository.save(comment);
        return this.modelMapperService.forResponse().map(comment, CreateCommentResponse.class);
    }

    @Override
    public UpdateCommentResponse updateComment(UpdateCommentRequest updateCommentRequest, Long id) {
        Comment oldComment = this.commentRepository.findById(id).orElseThrow();
        Comment comment = this.modelMapperService.forRequest().map(updateCommentRequest, Comment.class);
        comment.setUser(oldComment.getUser());
        comment.setPost(oldComment.getPost());
        this.commentRepository.save(comment);
        return this.modelMapperService.forResponse().map(comment, UpdateCommentResponse.class);
    }

    @Override
    public Long deleteComment(Long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow();
        this.commentRepository.deleteById(id);
        return comment.getId();
    }
}

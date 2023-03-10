package com.example.questapp.business.abstracts;

import com.example.questapp.business.requests.CreateCommentRequest;
import com.example.questapp.business.requests.UpdateCommentRequest;
import com.example.questapp.business.responses.comment.CreateCommentResponse;
import com.example.questapp.business.responses.comment.GetAllCommentsResponse;
import com.example.questapp.business.responses.comment.GetCommentByIdResponse;
import com.example.questapp.business.responses.comment.UpdateCommentResponse;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<GetAllCommentsResponse> getAll(Optional<Long> userId,Optional<Long> postId);

    GetCommentByIdResponse getById(Long id);

    CreateCommentResponse createComment(CreateCommentRequest createCommentRequest);

    UpdateCommentResponse updateComment(UpdateCommentRequest updateCommentRequest, Long id);

    Long deleteComment(Long id);

}

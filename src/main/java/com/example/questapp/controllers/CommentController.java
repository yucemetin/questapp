package com.example.questapp.controllers;

import com.example.questapp.business.abstracts.CommentService;
import com.example.questapp.business.requests.CreateCommentRequest;
import com.example.questapp.business.requests.UpdateCommentRequest;
import com.example.questapp.business.responses.comment.CreateCommentResponse;
import com.example.questapp.business.responses.comment.GetAllCommentsResponse;
import com.example.questapp.business.responses.comment.GetCommentByIdResponse;
import com.example.questapp.business.responses.comment.UpdateCommentResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private CommentService commentService;

    @GetMapping
    public List<GetAllCommentsResponse> getAll(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return this.commentService.getAll(userId, postId);
    }

    @GetMapping("/{id}")
    public GetCommentByIdResponse getById(@PathVariable Long id) {
        return this.commentService.getById(id);
    }

    @PostMapping
    public CreateCommentResponse createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        return this.commentService.createComment(createCommentRequest);
    }

    @PutMapping("/{id}")
    public UpdateCommentResponse updateComment(@RequestBody UpdateCommentRequest updateCommentRequest, @PathVariable Long id) {
        return this.commentService.updateComment(updateCommentRequest, id);
    }

    @DeleteMapping("/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return this.commentService.deleteComment(id);
    }
}

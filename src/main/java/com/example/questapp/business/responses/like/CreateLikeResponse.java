package com.example.questapp.business.responses.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLikeResponse {
    private Long id;
    private Long postId;
    private Long userId;

}

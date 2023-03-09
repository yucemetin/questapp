package com.example.questapp.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCommentsResponse {
    private Long id;
    private Long userId;
    private Long postId;
    private String text;
}

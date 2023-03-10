package com.example.questapp.business.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponse {
    private Long id;
    private Long userId;
    private String title;
    private String text;
}

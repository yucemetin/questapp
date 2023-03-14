package com.example.questapp.business.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostResponse {
    private Long id;
    private Long userId;
    private String title;
    private String text;
}

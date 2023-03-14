package com.example.questapp.business.responses.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPostByIdResponse {

    private Long id;
    private Long userId;
    private String title;
    private String text;
    private Date createdOn;
    private Date updatedOn;
}

package com.example.questapp.business.responses.post;

import com.example.questapp.business.responses.like.GetAllLikeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPostResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;
    private List<GetAllLikeResponse> postLikes;
    private Date createdOn;
    private Date updatedOn;
}

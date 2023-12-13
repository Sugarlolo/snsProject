package com.example.snsProject.model.DTO;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private String follow_member_id;
    private String member_id;
    private String url;
    int postCount = 0;
    int postFollows = 0;
    int postFollowers = 0;
}

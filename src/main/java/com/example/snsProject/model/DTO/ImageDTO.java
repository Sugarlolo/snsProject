package com.example.snsProject.model.DTO;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private int id;
    private int postId;
    private String img_url;
    private String img_uuid;

}

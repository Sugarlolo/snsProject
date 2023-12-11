package com.example.snsProject.model.DAO;

import com.example.snsProject.model.DTO.PostImageDTO;
import com.example.snsProject.model.DTO.PostLikeDTO;
import com.example.snsProject.model.DTO.PostTagDTO;
import com.example.snsProject.model.DTO.PostViewDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface PostDAO {
    List<PostViewDTO> getPosts(String userIds, int start, int cnt);
    List<PostImageDTO> getPostImages(Long postId);
    List<PostLikeDTO> getPostLikes(Long postId);
    List<PostTagDTO> getPostTags(Long postImageId);
    int likePost(Long postId, Long userId);
    boolean registerLike(Long postId, Long userId);
    boolean cancelLike(Long postId, Long userId);
    List<HashMap<?,?>> getImagesUrl();

}
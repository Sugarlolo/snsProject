package com.example.snsProject.service;


import com.example.snsProject.model.DAO.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostDAO postDAO;

    public boolean registerLike(String memberId, String postId) {
        return postDAO.registerLike(Long.parseLong(postId), Long.parseLong(memberId));
    }

    public boolean cancelLike(String memberId, String postId) {
        return postDAO.cancelLike(Long.parseLong(postId), Long.parseLong(memberId));
    }
    public List<HashMap<?,?>> getImagesUrl(){
        return postDAO.getImagesUrl();
    }

    public boolean registerPost(String userId, String postText, String postImageUrl) {
        boolean result = false;
        result =  postDAO.registerPost(userId, postText);
        if (result) {
            long id = postDAO.getRegisterPost(userId);
            result = postDAO.registerPostImage(id, postImageUrl);
        }
        return result;
    }

}

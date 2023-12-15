package com.example.snsProject.service;


import com.example.snsProject.model.DAO.ProfileDAO;
import com.example.snsProject.model.DTO.PostImageDTO;
import com.example.snsProject.model.DTO.PostLikeDTO;
import com.example.snsProject.model.DTO.PostTagDTO;
import com.example.snsProject.model.DTO.PostViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileDAO profileDAO;

    public long CountPosts(String id){

        return profileDAO.CountPosts(Long.parseLong(id)) ;
    }
    public long CountFollows(String id){
        return profileDAO.CountFollows(Long.parseLong(id)) ;

    }
    public long CountFollowers(String id){

        return profileDAO.CountFollowers(Long.parseLong(id)) ;
    }

    public  List<Map<String,Object>> getProfileInfo(long member_id){
        List<Map<String,Object>> getProfileImg_result = null;
        try {
            getProfileImg_result = profileDAO.getProfileInfo(member_id);
            if(getProfileImg_result.get(0).get("url") == null)
                getProfileImg_result.get(0).put("url","");
            if(getProfileImg_result.get(0).get("introduce") == null)
                getProfileImg_result.get(0).put("introduce","");
        }catch (Exception e){
            e.printStackTrace();
        }
        return getProfileImg_result;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<PostViewDTO> getPostsBookmark(String userIds){
        List<PostViewDTO> result= null;
        try {
            result = profileDAO.getPostsBookmark(userIds);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<PostViewDTO> getPosts(String userIds){       // 자신의 게시물을 몇개까지 가져올 건지
        List<PostViewDTO> result= null;
        try {
            result = profileDAO.getPosts(userIds);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    List<PostImageDTO> getPostImages(Long postId){                            // postimage table
        List<PostImageDTO> result= null;
        try {
            result = profileDAO.getPostImages(postId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    List<PostLikeDTO> getPostLikes(Long postId){                             // postlike table
        List<PostLikeDTO> result= null;
        try {
            result = profileDAO.getPostLikes(postId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    List<PostTagDTO> getPostTags(Long postImageId){                         // posttag table
        List<PostTagDTO> result= null;
        try {
            result = profileDAO.getPostTags(postImageId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    int likePost(Long postId, Long userId){                                 // 좋아요 수 가져오기
        int result = 0;
        try {
            result = profileDAO.likePost(postId, userId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    boolean registerLike(Long postId, Long userId){                        // 자신의 좋아요 on
        boolean result = false;
        try {
            result = profileDAO.registerLike(postId, userId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    boolean cancelLike(Long postId, Long userId){                           // 자신의 좋아요 off
        boolean result = false;
        try {
            result = profileDAO.cancelLike(postId, userId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    List<HashMap<?,?>> getImagesUrl(){                                      // post_image post_id
        List<HashMap<?,?>> result= null;
        try {
            result = profileDAO.getImagesUrl();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
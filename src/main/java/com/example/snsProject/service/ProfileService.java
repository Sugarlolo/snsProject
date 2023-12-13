package com.example.snsProject.service;

import com.example.snsProject.model.DAO.ProfileDAO;
import com.example.snsProject.model.DAO.UserDAO;
import com.example.snsProject.model.DTO.ProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileDAO profileDAO;

    public int CountPosts(String id){
        return profileDAO.CountPosts(Long.parseLong(id)) ;
    }
    public int CountFollows(String id){
        return profileDAO.CountFollows(Long.parseLong(id)) ;

    }
    public int CountFollowers(String id){
        return profileDAO.CountFollowers(Long.parseLong(id)) ;
    }


    public  List<Map<String,Object>> getProfileInfo(long member_id){
        List<Map<String,Object>> getProfileImg_result = null;
        try {
            getProfileImg_result = profileDAO.getProfileInfo(member_id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("getProfileImg 에러!!!!");
        }
        return getProfileImg_result;
    }
}

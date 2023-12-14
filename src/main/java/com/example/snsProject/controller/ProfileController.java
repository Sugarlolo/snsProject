package com.example.snsProject.controller;

import com.example.snsProject.model.DTO.PostAllDTO;
import com.example.snsProject.service.ProfileDetailService;
import com.example.snsProject.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileDetailService profileDetailService;
    private List<PostAllDTO> postAllDTO;

    private List<PostAllDTO> postAllBookmarkDTO;
    @GetMapping("/view/profile")
    public String Profile(@AuthenticationPrincipal UserDetails user, Model model) {
        postAllDTO = profileDetailService.getPosts(user.getUsername()); // 뒤에 숫자 2개는 안먹히게 해놨음.
        postAllBookmarkDTO = profileDetailService.getPostsBookmark(user.getUsername());

        List<Map<String,Object>> profileInfo = null;
        profileInfo = profileService.getProfileInfo(Long.parseLong(user.getUsername()));
        long CountPosts = profileService.CountPosts(user.getUsername()); //게시물 수
        long CountFollows = profileService.CountFollows(user.getUsername());; // 팔로우 수
        long CountFollowers = profileService.CountFollowers(user.getUsername());; // 팔로워 수

        model.addAttribute("CountPosts",CountPosts);
        model.addAttribute("CountFollows",CountFollows);
        model.addAttribute("CountFollowers",CountFollowers);
        model.addAttribute("profileInfo",profileInfo);

        model.addAttribute("postssizes", postAllDTO.size());
        model.addAttribute("posts", postAllDTO);


        model.addAttribute("postbookmarkssizes", postAllBookmarkDTO.size());
        model.addAttribute("postbookmarks", postAllBookmarkDTO);
        System.out.println(postAllBookmarkDTO.size());
        System.out.println(postAllBookmarkDTO);
        

        return "response/profile";
    }

}

package com.example.snsProject.controller;

import com.example.snsProject.service.ProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProfileController {

        private final ProfileService profileService;

        @GetMapping("/view/Profile")
        public String Profile(@AuthenticationPrincipal UserDetails user, Model model) {
            List<Map<String,Object>> profileInfo = null;
            profileInfo = profileService.getProfileInfo(Long.parseLong(user.getUsername()));
            System.out.println("profileInfo : "+ profileInfo);
            model.addAttribute("profileInfo",profileInfo);

            model.addAttribute("test","이것이 바로 테스트");

            return "response/profile";
        }
/*
        @GetMapping("/ProfileCount")
        @ResponseBody
        public int CountUsers(@RequestParam String id) {
            return profileService.CountPosts(id);
        }

 */

    }


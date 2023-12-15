package com.example.snsProject.controller;

import com.example.snsProject.service.Profile_chService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class Profile_chController {
    private final Profile_chService profile_chService;

    @RequestMapping("/view/profile_ch")
    public String profile_ch1(@AuthenticationPrincipal UserDetails user,
                              Model model){
        List<Map<String, Object>> result = null;
        try {
            result = profile_chService.getAllUserInfo(user.getUsername());
            model.addAttribute("result",result);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("get에러!");
        }
        return "response/profile_change";
    }

    @PostMapping("/view/profile_ch2")
    public String profile_ch2(@AuthenticationPrincipal UserDetails user,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("introduce") String introduce,
                              @RequestParam("gender") int gender,
                              Model model){
        if (!file.isEmpty()) {
            try {
                String uploadDir = "C:\\snsProject-main\\src\\main\\resources\\static\\img\\";
                String selectDir = "/img/";
                File dest = new File(uploadDir + file.getOriginalFilename());
                File dest2 = new File(file.getOriginalFilename());
                System.out.println("파일 경로: " + dest.getAbsolutePath());
                System.out.println("소개글"+introduce);
                System.out.println("성별"+gender);
                file.transferTo(dest);
                profile_chService.updateUserInfo(dest2.getName(),introduce,gender,user.getUsername());
                model.addAttribute("message", "파일 업로드 성공!");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "파일이 비어있습니다.");
        }
       return "response/home";
    }
}

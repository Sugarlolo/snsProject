package com.example.snsProject.controller;

import com.example.snsProject.model.DTO.UserDTO;
import com.example.snsProject.service.MemberService;
import com.example.snsProject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/registPost")
@RequiredArgsConstructor
public class RegistPostController {
    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/getPostUser")
    public ResponseEntity<Map<String, Object>> getPostUser(@AuthenticationPrincipal UserDetails user) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserDTO userDTO = memberService.findUser(user.getUsername());
            response.put("userId", user.getUsername());
            response.put("url", userDTO.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> handleFileUpload(
            @AuthenticationPrincipal UserDetails user,
            @RequestPart("file") MultipartFile file,
            @RequestPart("text") String text,
            @RequestPart("file_name") String file_name){
        Map<String, Object> response = new HashMap<>();
        file_name = file_name + System.currentTimeMillis() + ".png";
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        // 파일 및 텍스트 데이터 처리 로직 작성s
        if (!file.isEmpty()) {
            try {
                // 저장할 경로 설정 (원하는 경로로 수정)
                /*String uploadDir = "C:\\Users\\dita810\\Desktop\\snsProject\\src\\main\\resources\\static\\img\\";*/
                String uploadDir = "C:\\spring\\snsProject-main\\snsProject\\src\\main\\resources\\static\\img\\";
                File dest = new File(uploadDir + file_name);
                System.out.println("파일 경로: " + dest.getAbsolutePath());
                if (postService.registerPost(user.getUsername(), text, "/img/" + file_name)) {
                    // 파일 저장
                    file.transferTo(dest);

                    response.put("success", true);
                }
            } catch (IOException e) {
                e.printStackTrace();
                response.put("success", false);
            }
        } else {
            response.put("success", false);
        }
        // 처리 결과에 따라 적절한 응답 반환
        return ResponseEntity.ok().headers(headers).body(response);
    }
}

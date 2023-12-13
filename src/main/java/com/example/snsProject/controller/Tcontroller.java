package com.example.snsProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class Tcontroller {

    @GetMapping("/testupload")
    public String showForm() {
        return "response/testhtml"; // upload.html 페이지를 보여줍니다.
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        System.out.println("test1231");
        if (!file.isEmpty()) {
            try {
                // 저장할 경로 설정 (원하는 경로로 수정)
                String uploadDir = "C:\\spring\\snsProject-main\\snsProject\\src\\main\\resources\\static\\img\\";
                File dest = new File(uploadDir + file.getOriginalFilename());
                System.out.println("파일 경로: " + dest.getAbsolutePath());
                System.out.println("받긴 함");
                // 파일 저장
                file.transferTo(dest);
                System.out.println("성공");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            model.addAttribute("message", "파일이 비어있습니다.");
        }

        return "/testupload"; // 업로드 결과를 보여주는 페이지로 이동
    }
}

package com.example.snsProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/requestView")
    public String responseView(Model model){
        model.addAttribute("data", "hello 테스트입니다.");
        return "LoginTest";
    }
}

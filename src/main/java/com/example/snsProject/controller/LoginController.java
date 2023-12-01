package com.example.snsProject.controller;

import com.example.snsProject.model.DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/restAPI")
@RequiredArgsConstructor
public class LoginController {
    @RequestMapping("/requestView")
    public String responseView(Model model) {
        model.addAttribute("data", "hello 테스트입니다.");
        return "LoginTest";
    }
    @RequestMapping("/auth/login")
    public String responseViewLogin(){
        return "HTML/home";
    }

    @RequestMapping("/loginProc")
    public String responseLoginProc() {
        return "HTML/index";
    }

}

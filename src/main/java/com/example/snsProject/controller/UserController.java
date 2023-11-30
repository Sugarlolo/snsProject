package com.example.snsProject.controller;

import com.example.snsProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(value="/restAPI")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping (value="/getCountAllMember", method = RequestMethod.GET)
    public ResponseEntity<?> getCountAllUser() {
        int countAllMember = 0;
        try {
            countAllMember = userService.getCountAllMember();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ResponseEntity.ok(countAllMember);
    }
}
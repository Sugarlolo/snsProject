package com.example.snsProject.controller;

import com.example.snsProject.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/explore")
public class ExploreController {
    private final ImageService imageService;

//    @GetMapping("/")
//    public String responseLoadedGallery() {
//
//        return "";
//    }

    @PostMapping("/{postId}")
    public String responsePostView(@PathVariable(value="postId") int postId, Model model) {
        return "/post/postView";
    }

    @RequestMapping("")
    public String responseViewExplore() {
        return "explore";
    }

    @ResponseBody
    @PostMapping(value = "/")
    public ResponseEntity<?> responseImgUrl() {
        List<HashMap<?,?>> urlMap;
        urlMap = imageService.getImagesUrlExplore();
        System.out.println("touched Explore");
        return ResponseEntity.ok(urlMap);
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String responseLoadedContent(Model model) {
        List<HashMap<?, ?>> urlMap;
        urlMap = imageService.getPostListExplore();
        model.addAttribute("result", urlMap);
        return "/explore/LoadContent";
    }
}

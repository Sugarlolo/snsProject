package com.example.snsProject.service;

import com.example.snsProject.model.DAO.ImageDAO;
import com.example.snsProject.model.DTO.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageDAO imageDAO;
    public List<HashMap<?,?>> getImagesUrlExplore(){
        System.out.println(imageDAO.getImagesUrlExplore());
        return imageDAO.getImagesUrlExplore();
    }

    public List<ImageDTO> getImagesUrlList(){
        return imageDAO.getImagesUrlList();
    }

    public List<HashMap<?,?>> getPostListExplore() {
        return imageDAO.getPostListExplore();
    }
}

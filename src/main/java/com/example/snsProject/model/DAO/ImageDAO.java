package com.example.snsProject.model.DAO;

import com.example.snsProject.model.DTO.ImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
@Mapper
public interface ImageDAO {
    List<HashMap<?,?>> getImagesUrlExplore();
    List<ImageDTO> getImagesUrlList();

    List<HashMap<?,?>> getPostListExplore();
}

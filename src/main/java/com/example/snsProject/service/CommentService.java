package com.example.snsProject.service;


import com.example.snsProject.model.DAO.CommentDAO;
import com.example.snsProject.model.DTO.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDAO commentDAO;

    public List<CommentDTO> commentList(String postId) {
        List<CommentDTO> commentList = commentDAO.commentList(Long.parseLong(postId));
        return commentList;
    }

    public int commentListSize(String postId) {
        int size = 0;
        size = commentDAO.commentListSize(Long.parseLong(postId));
        return size;
    }

    public boolean registerComment(CommentDTO comment) {
        boolean result = false;
        result = commentDAO.registerComment(comment);
        return result;
    }
}

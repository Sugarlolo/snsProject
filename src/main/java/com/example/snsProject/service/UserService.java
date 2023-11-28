package com.example.snsProject.service;

import com.example.snsProject.model.DAO.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    public int getCountAllMember() {
        return userDAO.getCountAllMember();
    }
}

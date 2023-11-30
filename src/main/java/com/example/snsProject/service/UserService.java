package com.example.snsProject.service;

import com.example.snsProject.model.DAO.UserDAO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserDAO userDAO;
    public int getCountAllMember() {
        return userDAO.getCountAllMember();
    }

    public boolean getAccountInfo(@Param("userid") String userid) { return userDAO.getAccountInfo(); }
}

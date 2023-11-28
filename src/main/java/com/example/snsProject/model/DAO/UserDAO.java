package com.example.snsProject.model.DAO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    int getCountAllMember();
}

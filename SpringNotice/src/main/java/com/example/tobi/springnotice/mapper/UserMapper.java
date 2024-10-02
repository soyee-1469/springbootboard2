package com.example.tobi.springnotice.mapper;

import com.example.tobi.springnotice.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    User findById(String userid, String password);
}

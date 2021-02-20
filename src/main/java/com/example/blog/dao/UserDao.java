package com.example.blog.dao;

import com.example.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}

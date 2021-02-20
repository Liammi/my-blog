package com.example.blog.service.serviceImpl;

import com.example.blog.dao.UserDao;
import com.example.blog.pojo.User;
import com.example.blog.service.UserService;
import com.example.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User CheckUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}

package com.example.blog.service;

import com.example.blog.pojo.Type;

import java.util.List;

public interface TypeService {

    Type getType(Long id);

    Type getTypeByName(String name);

    List<Type> listType();

    List<Type> listTypeAndBlog();

    int saveType(Type type);

    int updateType(Type type);

    int deleteType(Long id);

}

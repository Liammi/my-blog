package com.example.blog.dao;

import com.example.blog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> listType();

    List<Type> listTypeAndBlog();

    int saveType(Type type);

    int updateType(Type type);

    int deleteType(Long id);

}

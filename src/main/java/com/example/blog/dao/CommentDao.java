package com.example.blog.dao;

import com.example.blog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

    //查询所有的父级目录
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId);

    //查询不为空的第二级目录
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    //查询二级回复
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId, @Param("childId") Long childId);

    int saveComment(Comment comment);

    int deleteComment(Long id);

}

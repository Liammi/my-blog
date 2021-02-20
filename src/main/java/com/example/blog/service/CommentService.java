package com.example.blog.service;

import com.example.blog.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

    int deleteComment(Long id);
}

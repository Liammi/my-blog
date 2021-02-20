package com.example.blog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Comment {

    private Long id;

    private String picture;

    private String content;

    private Date createTime;

    private String email;

    private String nickName;

    //多对一关系
    private Long blogId;

    private boolean adminComment;

    private Long parentCommentId;

    private String parentNickName;

    private Comment parentComment;

    private List<Comment> replyComments = new ArrayList<>();

}

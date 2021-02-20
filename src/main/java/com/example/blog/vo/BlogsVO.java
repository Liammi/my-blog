package com.example.blog.vo;

import com.example.blog.pojo.Comment;
import com.example.blog.pojo.Type;
import com.example.blog.pojo.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BlogsVO {

    private Long id;

    private Date createTime;

    private Date updateTime;

    private String title;

    private Integer views;

    //在mybatis中进行连接查询
    private Long typeId;

    private Long userId;

    private String description;

    private boolean original;

    private boolean commented;

    private boolean published;

    private boolean recommend;

    //与type多对一关系，由本类维护
    private Type type;
    //与user多对一关系，由本类维护
    private User user;
    //与Comment一对多关系,由comment类维护
    private List<Comment> commentList = new ArrayList<>();

}

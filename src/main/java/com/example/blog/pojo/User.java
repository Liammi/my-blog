package com.example.blog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {

    private Long id;//用户id
    private String nickName;//昵称
    private String userName;//用户名
    private String password;//密码
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    //与blog一对多关系，由本类维护
    private List<Blog> blogs = new ArrayList<>();

}
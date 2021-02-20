package com.example.blog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Blog {

    private Long id;

    private Date createTime;

    private Date updateTime;

    private String title;

    private Integer views;

    //在mybatis中进行连接查询
    private Long typeId;

    private Long userId;

    private String description;
    //TODO:这里使用基本类型因为基本类型的初值为false，如果前端的表单不传递，数据库的值也为false,如果为Boolean包装类型则为null，也可以使用动态sql。
    private boolean original;

    private boolean commented;

    private boolean published;

    private boolean recommend;

    private String content;

    //与type多对一关系，由本类维护
    private Type type;
    //与user多对一关系，由本类维护
    private User user;
    //与Comment一对多关系,由comment类维护
    private List<Comment> commentList = new ArrayList<>();

}

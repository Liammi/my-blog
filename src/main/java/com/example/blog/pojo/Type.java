package com.example.blog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Type {

    private Long id;

    private String name;
    //一对多关系，由本类维护
    private List<Blog> blogList = new ArrayList<>();

}

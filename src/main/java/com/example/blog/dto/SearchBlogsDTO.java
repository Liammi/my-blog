package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor                 //无参构造
@AllArgsConstructor                //有参构造
public class SearchBlogsDTO {
    private String title;
    private Long typeId;
    private Boolean recommend;

    //boolean类型会生成isrecommend()方法
    //1.Boolean类型,生成的get方法是get开头的(建议使用这个).
    //2.boolean类型,生成的get方法是is开头的
}

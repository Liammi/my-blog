package com.example.blog.controller;

import com.example.blog.service.BlogService;
import com.example.blog.vo.BlogsVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam("query") String query,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        List<BlogsVO> blogsVOList = blogService.listBlogByBlogSearch(query, pageNum, 0);//TODO:这里分页方式有问题
        PageInfo<BlogsVO> pageInfo = new PageInfo<>(blogsVOList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

}

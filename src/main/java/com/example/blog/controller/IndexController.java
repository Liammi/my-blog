package com.example.blog.controller;

import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.vo.BlogsVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/home")
    public String home(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        List<BlogsVO> blogsVOList = blogService.listBlog(pageNum, 10);
        PageInfo<BlogsVO> pageInfo = new PageInfo<>(blogsVOList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.listTypeAndBlog().subList(0, 10));
        model.addAttribute("recommendBlog", blogService.listRecommendBlog(10));
        return "index";
    }

}

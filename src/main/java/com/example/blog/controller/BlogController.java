package com.example.blog.controller;

import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        blogService.updateBlogView(id);
        model.addAttribute("blog", blogService.getBlogAndConvert(id));
        return "blog";
    }

}

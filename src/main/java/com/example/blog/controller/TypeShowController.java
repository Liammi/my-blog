package com.example.blog.controller;

import com.example.blog.pojo.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.vo.BlogsVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/type/{id}")
    public String type(@PathVariable Long id, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        List<Type> types = typeService.listTypeAndBlog();
        List<BlogsVO> blogsVOList = blogService.listBlogByTypeId(id, pageNum, 0);//TODO:这里分页方式有问题
        PageInfo<BlogsVO> pageInfo = new PageInfo<>(blogsVOList);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

    @GetMapping("/types")
    public String types(Model model) {
        List<Type> types = typeService.listTypeAndBlog();
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", new PageInfo<BlogsVO>());
        model.addAttribute("activeTypeId", 0);
        /*        model.addAttribute("")*/
        return "types";
    }
}

package com.example.blog.controller.admin;

import com.example.blog.annotation.AutoIdempotent;
import com.example.blog.dto.SearchBlogsDTO;
import com.example.blog.pojo.Blog;
import com.example.blog.pojo.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.TokenService;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/blogs")
    //倒序方式来查找所有博客,并显示type数据下拉框
    public String blogs(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("blogs", blogService.listBlog());
        return "admin/blogs";
    }

    //ajax搜索按钮来查找,这里使用了thymeleaf模板的ajax方法，
    @PostMapping("/blogs/search")
    public String search(SearchBlogsDTO searchBlogsDTO, Model model) {
        model.addAttribute("blogs", blogService.listBlogByBlogQuery(searchBlogsDTO));
        return "admin/blogs :: blogList";
    }

    //点击新增按钮，跳转到博客编辑页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("token", tokenService.createToken());
        model.addAttribute("blog", new Blog());//为页面不报错添加对象
        return "admin/blogs-input";
    }

    //点击修改按钮，跳转到博客编辑页面
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("token", tokenService.createToken());
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog", blog);
        return "admin/blogs-input";
    }

    //提交博客保存，重定向到blogs页面
    @PostMapping("/blogs")
    @AutoIdempotent
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        int column;
        //判断该方法为增加还是删除
        if (blog.getId() == null) {//如果没有id说明为新增
            blog.setUserId(((User) session.getAttribute("user")).getId());//向blog中添加user的值
            column = blogService.saveBlog(blog);
        } else {
            column = blogService.updateBlog(blog);
        }
        //判断返回值的类型来判断是否操作成功
        if (column == 0) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}

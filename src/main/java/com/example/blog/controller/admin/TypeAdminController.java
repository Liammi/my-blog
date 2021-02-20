package com.example.blog.controller.admin;

import com.example.blog.annotation.AutoIdempotent;
import com.example.blog.pojo.Type;
import com.example.blog.service.TokenService;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeAdminController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TokenService tokenService;

    //查询所有分类
    @GetMapping("/types")//查询type分类
    public String list(Model model) {
        model.addAttribute("types", typeService.listType());//TODO:管理员界面types没有添加分页
        return "admin/types";
    }

    //点击添加按钮，跳转到type添加页面
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());//此处添加type为了模版引擎不出错，因为修改与添加公用一个页面
        model.addAttribute("token", tokenService.createToken());
        return "admin/types-input";
    }

    //点击修改按钮，跳转到type添加页面
    @GetMapping("/types/{id}/input")//修改方法，跳转到修改页面
    public String editInput(@PathVariable long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        model.addAttribute("token", tokenService.createToken());
        return "admin/types-input";
    }

    //添加新增数据，重定向到types页面
    @PostMapping("/types")//types-input页面post表单提交type
    @AutoIdempotent
    public String add(@Valid Type type, BindingResult bindingResult, RedirectAttributes attributes) {
        int column;
        //判断name是否存在
        if (typeService.getTypeByName(type.getName()) != null) {
            attributes.addFlashAttribute("message", "添加失败");
        } else {
            //判断为新增操作或者是更新操作
            if (type.getId() == null) {
                column = typeService.saveType(type);
            } else {
                column = typeService.updateType(type);
            }
            //判断返回结果
            if (column != 1 || bindingResult.hasErrors()) {//添加失败
                attributes.addFlashAttribute("message", "添加失败");
            } else {
                attributes.addFlashAttribute("message", "添加成功");
            }
        }
        return "redirect:/admin/types";
    }

    //删除方法
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        if (typeService.deleteType(id) == 1) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/types";
    }

}

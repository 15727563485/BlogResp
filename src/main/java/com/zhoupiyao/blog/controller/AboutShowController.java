package com.zhoupiyao.blog.controller;

import com.zhoupiyao.blog.po.Blog;
import com.zhoupiyao.blog.service.BlogService;
import com.zhoupiyao.blog.service.TagService;
import com.zhoupiyao.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("types",typeService.listAllType());
        model.addAttribute("tags",tagService.listAllTag());
        return "about";
    }
}

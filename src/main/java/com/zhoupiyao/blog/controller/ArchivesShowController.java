package com.zhoupiyao.blog.controller;

import com.zhoupiyao.blog.po.Blog;
import com.zhoupiyao.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesShowController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        Map<String, List<Blog>> archiveBlogs = blogService.archiveBlog();
        model.addAttribute("archiveBlogs",archiveBlogs);
        model.addAttribute("blogCounts",blogService.blogCount());
        return "archives";
    }
}

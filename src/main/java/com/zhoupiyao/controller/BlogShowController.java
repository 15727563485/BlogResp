package com.zhoupiyao.controller;

import com.alibaba.fastjson.JSON;
import com.zhoupiyao.po.Blog;
import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BlogShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}/blog")
    public String blog(@PathVariable Long id, Model model) {
        blogService.updateViews(id);
        model.addAttribute("blog", blogService.getAndConvert(id));
        model.addAttribute("comments", commentService.listCommentByBlogId(id));
        return "blog";
    }

    //接口
    @RequestMapping("/json")
    @ResponseBody
    public String getJson(Pageable pageable) {
        Page<Blog> page = blogService.listBlog(pageable);
        List<Blog> blogs = page.getContent();
        return JSON.toJSONString(blogs);
    }


}

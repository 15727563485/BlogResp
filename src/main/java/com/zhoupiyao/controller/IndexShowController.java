package com.zhoupiyao.controller;

import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.TagService;
import com.zhoupiyao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(Model model) {
        //每页显示的博客条数
        model.addAttribute("page", blogService.listPublishedBlog(3, 0));
        //每页显示推荐框(最新推荐)的博客条数
        model.addAttribute("recommends", blogService.listRecommendTop(4));
        //每页显示分类框的分类数
        model.addAttribute("topTypes", typeService.listTypeTop(3));
        //每页显示标签框的标签数
        model.addAttribute("topTags", tagService.listTagTop(3));
        return "index";
    }

    @PostMapping("/index/page")
    public String page(Model model, @RequestParam String page) {
        Integer pageNumber = Integer.parseInt(page);
        //每页显示的博客条数
        model.addAttribute("page", blogService.listPublishedBlog(3, pageNumber));
        //每页显示推荐框(最新推荐)的博客条数
        model.addAttribute("recommends", blogService.listRecommendTop(4));
        //每页显示分类框的分类数
        model.addAttribute("topTypes", typeService.listTypeTop(3));
        //每页显示标签框的标签数
        model.addAttribute("topTags", tagService.listTagTop(3));
        return "index :: blogs";
    }

    @GetMapping("/footer/newblog")
    public String newBlog(Model model) {
        model.addAttribute("recommends", blogService.listnewBlogs(3));
        return "fragments ::  newblogs";
    }

}

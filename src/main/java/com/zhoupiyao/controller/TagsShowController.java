package com.zhoupiyao.controller;

import com.zhoupiyao.po.Tag;
import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TagsShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model, @PageableDefault(size = 2, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        List<Tag> tags = new ArrayList<>();
        tags = tagService.listTagTop(10000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(pageable, id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}

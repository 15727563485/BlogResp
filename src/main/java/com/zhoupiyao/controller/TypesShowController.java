package com.zhoupiyao.controller;

import com.zhoupiyao.po.Type;
import com.zhoupiyao.service.BlogService;
import com.zhoupiyao.service.TypeService;
import com.zhoupiyao.vo.BlogQuery;
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
public class TypesShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, Model model, @PageableDefault(size = 3, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        List<Type> types = new ArrayList<>();
        types = typeService.listTypeTop(10000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}

package com.zhoupiyao.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
    @GetMapping("/adminIndex")
    public String blogsPost() {
        return "admin/index";
    }

}
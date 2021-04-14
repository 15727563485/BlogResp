package com.zhoupiyao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicController {
    @GetMapping("/music")
    public String musicPage() {
        return "music";
    }
}

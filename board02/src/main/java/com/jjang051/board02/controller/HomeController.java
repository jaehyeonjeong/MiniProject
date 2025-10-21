package com.jjang051.board02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/","/main","/index"})
    public String index(Model model){
        return "index/index";
    }
}

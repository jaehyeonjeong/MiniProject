package com.jjang051.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class HomeController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";  //templates/+hello+.html
    }
    @GetMapping({"/","/home","/index"})
    public String home() {
        return "home";
    }
    @GetMapping("/request")
    public String request() {
        return "request";
    }
    @GetMapping("/result")
    public String result(
            @RequestParam(name = "userID",required = false, defaultValue = "") String userID,
            @RequestParam(name = "userPW",required = false,
            defaultValue = "") String userPW,
            @RequestParam(name = "userName",required = false,
            defaultValue = "") String userName,
            Model model
    ) {
        System.out.println("userID ==="+userID);
        System.out.println("userPW ==="+userPW);
        //Map<String,String> map = new HashMap<>();

        if(userID.equals("jjang051")&& userPW.equals("1234")){
            model.addAttribute("userName",userName);
            return "login-ok";
        }
        //String이 아닌 객체를 응답하면 json으로 변환되어서 결과를 return 한다.
        model.addAttribute("userName","실패");
        return "login-fail";
    }

}

package com.jjang051.ajax.controller;

import com.jjang051.ajax.dto.JoinDto;
import com.jjang051.ajax.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    @GetMapping({"/","/home","/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/ajax")
    public String ajax(){
        return "ajax";
    }
    @PostMapping("/ajax")
    @ResponseBody
    public String ajaxPost(
            @RequestParam(value = "userID") String userID,
            @RequestParam(value = "userPW") String userPW){
        System.out.println("userID==="+userID+
                ",userPW=="+userPW);
        return "userID==="+userID+",userPW=="+userPW;
    }
    @PostMapping("/ajax02")
    @ResponseBody
    public String ajaxPost02(@RequestBody Map<String,String> receiveMap){
        String userID = receiveMap.get("userID");
        String userPW = receiveMap.get("userPW");
        int age = Integer.parseInt(receiveMap.get("age"));
        System.out.println(userID+","+userPW+","+age);
        return "여기는 출력안됨";
    }
    @PostMapping("/ajax03")
    @ResponseBody
    public String ajaxPost03(@RequestBody JoinDto joinDto){
        System.out.println(joinDto.getUserID()+","+joinDto.getUserPW()+","+joinDto.getAge());
        return "응답 response";
    }
    @PostMapping("/ajax04")
    @ResponseBody
    public Map<String,Boolean> ajaxPost04(@RequestBody JoinDto joinDto){
        System.out.println(joinDto.getUserID()+","+joinDto.getUserPW()+","+joinDto.getAge());
        Map<String,Boolean> responseMap = new HashMap<>();
        //db에 입력하고 결과를 받는다.
        responseMap.put("isJoin",true);
        return responseMap;
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Boolean> loginProcess(@RequestBody LoginDto loginDto){
        Map<String,Boolean> responseMap = new HashMap<>();
        System.out.println(loginDto.getUserID()+","+loginDto.getUserPW());
        if(loginDto.getUserID().equals("jjang051") && loginDto.getUserPW().equals("1234")){
            responseMap.put("isLogin",true);
        } else {
            responseMap.put("isLogin", false);
        }
        return responseMap;
    }


}

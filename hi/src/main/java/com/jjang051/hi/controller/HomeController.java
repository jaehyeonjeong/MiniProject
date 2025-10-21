package com.jjang051.hi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


//di dependency injection (의존성 주입) IoC (Inversion of Control)
//@Controller 라는 annotation을 달면 scan 을 해서 스프링 컨테이너에 담아둔다.
//URI매핑
@Controller
public class HomeController {
    @GetMapping("/home") //trigger
    @ResponseBody
    public String home() {
        return "home";
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String gugudan(@RequestParam(value = "dan", required = true, defaultValue = "2") String dan) {
        System.out.println("dan : " + dan);
        int myDan = Integer.parseInt(dan);
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>");
        sb.append(myDan);
        sb.append("을 출력합니다.</h1>");
        sb.append("<ul>");
        for(int i=1;i<=9;i++) {
            sb.append("<li>" + dan + " x "+i+" = "+myDan*i+ "</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
    @GetMapping("/gugudan02")
    public String gugudan02(
            @RequestParam(value = "dan", required = true, defaultValue = "2")
            String paramDan,
            Model model
            ) {
        //정수가 아닐때 처리해보기....
        int dan;
        try {
            dan = Integer.parseInt(paramDan);
        } catch (NumberFormatException e) {
            dan = 2;
        }
        model.addAttribute("dan", dan);
        return "gugudan02";
    }

    @GetMapping("/send222")
    public String send() {
        return "send";
    }
    @GetMapping("/form-result")
    //@ResponseBody
    public String formResult(
            @RequestParam(value = "userID", required = false)  String userID,
            @RequestParam(value = "userPW", required = false)  String userPW,
            @RequestParam(value = "favorite", required = false) List<String> favorite,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "country", required = false) String country,
            Model model

            )
    {
        System.out.println("userID = "+userID);
        System.out.println("userPW = "+userPW);
        System.out.println("favorite = "+String.join(",",favorite));
        System.out.println("gender = "+gender);
        System.out.println("country = "+country);
        model.addAttribute("userID",userID);
        model.addAttribute("userPW",userPW);
        model.addAttribute("gender",gender);
        model.addAttribute("favorite",favorite);
        model.addAttribute("country",country);
        return "form-result";
    }
    @GetMapping("/consult")
    public String consult() {
        return "consult";
    }
    @GetMapping("/consult-result")
    @ResponseBody
    public String consultResult(
            @RequestParam(value = "region", required = true, defaultValue = "gangnam") String  region,
            @RequestParam(value = "userName", required = true, defaultValue = "") String userName,
            @RequestParam(value = "tel01", required = true, defaultValue = "010") String tel01,
            @RequestParam(value = "tel02", required = true, defaultValue = "") String tel02,
            @RequestParam(value = "tel03", required = true, defaultValue = "") String tel03,
            @RequestParam(value = "favorite", required = true, defaultValue = "") String favorite,
            @RequestParam(value = "content", required = true, defaultValue = "") String content,
            @RequestParam(value = "privacy", required = true, defaultValue = "off") String privacy
            )
    {
        System.out.println("region = "+region);
        System.out.println("userName = "+userName);
        //System.out.println("tel01 = "+tel01);
        //System.out.println("tel02 = "+tel02);
        //System.out.println("tel03 = "+tel03);
        String tel = tel01+tel02+tel03;
        System.out.println(tel);
        System.out.println("favorite = "+favorite);
        System.out.println("content = "+content);
        System.out.println("privacy = "+privacy);
        if(!privacy.equals("on")){
            return "<script>alert('개인장보 동의하셔야 합니다.');history.back();</script>";
        }
        return "consult-result";
    }

}

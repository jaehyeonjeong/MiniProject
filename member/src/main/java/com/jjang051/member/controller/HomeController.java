package com.jjang051.member.controller;

import com.jjang051.member.dto.JoinDto;
import com.jjang051.member.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//등록이 된다.
//  HomeController.java  ===> HomeController.class
//  new HomeController(); 자바 생성자가 있어야 실행 가능
//  IoC Inversion of Control  DI
@Controller
public class HomeController {

    @GetMapping({"/", "/main", "/index", "/home"})
    public String index() {
        return "index";
    }

    @GetMapping("/request")
    public String request(
                          @RequestParam(value = "userID",required = true,defaultValue = "noname") String userID,
                          @RequestParam(value = "userPW",required = true,defaultValue = "1234") String userPw,
                          @RequestParam(value="userName",required = true,defaultValue = "홍길동") String userName,
                          @RequestParam(value="userAge",required = false,defaultValue = "7") int userAge,
                          Model model
                          ) {
        //userName은 필수 요소
        //userAge int 필수 요소는 아니다.
        System.out.println(userID + "====" + userPw+"===="+userName+"===="+userAge);
        model.addAttribute("userID",userID);
        //값을 내려 보낼때
        return "request";
    }
    @GetMapping("/request02")
    public String request02(@ModelAttribute JoinDto joinDto,Model model) {
        //model.addAttribute("joinDto",joinDto);
        //@ModelAttribute 는 프론트에서 넘어오는 값을 받을 쓰고 내려보낼때도 쓴다.
        System.out.println(joinDto);
        return "request02";
    }

    @GetMapping("/request03/{idx}")
    public String request03(
                            @PathVariable("idx") int idx,
                            Model model) {
        List<MemberDto> memberDtoList=new ArrayList<>();
        memberDtoList.add(new MemberDto(1,"jjang051","1234","장성호"));
        memberDtoList.add(new MemberDto(2,"hong","2222","홍길동"));
        memberDtoList.add(new MemberDto(3,"kim","3333","김유신"));
        MemberDto memberDto = memberDtoList.get(idx-1);
        model.addAttribute("memberDto",memberDto);
        return "request03";
    }
}











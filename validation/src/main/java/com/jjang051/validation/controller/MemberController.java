package com.jjang051.validation.controller;

import com.jjang051.validation.dto.LoginDto;
import com.jjang051.validation.dto.MemberDto;
import com.jjang051.validation.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("memberDto",new MemberDto());
        return "member/add";
    }
    @PostMapping("/add")
    public String addProcess(@Valid @ModelAttribute MemberDto memberDto,
                             BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            System.out.println(memberDto);
            //model.addAttribute("msg",true);
            return "member/add";
        }
        System.out.println(memberDto);
        return "redirect:member/list";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable("id") Integer id){
        return "member/detail";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginDto",new LoginDto());
        return "member/login";
    }
    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute LoginDto loginDto,
                               BindingResult bindingResult,
                               Model model){
        System.out.println(loginDto.toString());
        if (bindingResult.hasErrors()){
            return "member/login";
        }
        MemberDto loginMember = memberRepository.findById(loginDto);
        //System.out.println(loginMember.toString());
        if(loginMember==null){
            bindingResult.reject("loginFail","아이디 또는 패스워드가 맞지 않습니다.");
            return "member/login";
        }
        return "redirect:member/list";
    }


}

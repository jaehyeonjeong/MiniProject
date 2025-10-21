package com.jjang051.db02.controller;

import com.jjang051.db02.dto.Member;
import com.jjang051.db02.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository  memberRepository;
    @GetMapping("/member/list")
    public String memberList(Model model){
        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList",memberList);
        return "member/list";
    }
    @GetMapping("/member/detail/{id}")
    public String memberList(@PathVariable("id") int id, Model model){
        Member findedMember = memberRepository.findById(id);
        model.addAttribute("findedMember",findedMember);
        return "member/detail";
    }
    @GetMapping("/member/add")
    public String memberAdd(Model model){
        return "member/add";
    }

    @PostMapping("/member/add")
    //@ResponseBody
    public String memberAdd(@ModelAttribute("member") Member member){
        //System.out.println(member.getUserID()+"==="+member.getUserEmail()+"==="+member.getUserName());
        int result = memberRepository.save(member);
        return "redirect:../member/list";
    }
    @GetMapping("/member/{id}/edit")
    public String memberEdit(@PathVariable("id") int id, Model model){
        Member findedMember = memberRepository.findById(id);
        model.addAttribute("findedMember",findedMember);
        return "member/edit";
    }
    @PostMapping("/member/edit")
    public String memberEdit(@ModelAttribute("member") Member member){
        int result = memberRepository.update(member);
        System.out.println(result);
        return "redirect:../member/list";
    }
    @GetMapping("/member/{id}/delete")
    public String memberDelete(@PathVariable("id") int id, Model model){
        Member findedMember = memberRepository.findById(id);
        model.addAttribute("findedMember",findedMember);
        return "member/delete";
    }
    @PostMapping("/member/delete")
    public String memberDeleteProcess(@ModelAttribute Member member){
        int result = memberRepository.delete(member);
        return "redirect:/member/list";
    }
}

package com.jjang051.board02.controller;

import com.jjang051.board02.dao.BoardDao;
import com.jjang051.board02.dao.MemberDao;
import com.jjang051.board02.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    //수절

    private final MemberDao memberDao;
    // 아마도 BoardDao에서 정의된 findAll함수를 이용해서 가져오면 될 것 같은데
    private final BoardDao boardDao;
    // 대신에 준이가 새로 만들어준 쿼리를 통해 Mapper에 등록을 하고 새로 함수를 가져와야 할 듯

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute MemberDto memberDto,
                         BindingResult bindingResult,
                         Model model) {
        if(bindingResult.hasErrors()){
            return "member/signup";
        }

        //result는 정수 반환  (insert,delete,update 등은 반환된 row를 리턴한다.)
        int duplicateUserID  =  memberDao.existsUserId(memberDto.getUserID()); //userID가 있는지 따져보기
        int duplicateUserEmail  =  memberDao.existsEmail(memberDto.getUserEmail()); //userID가 있는지 따져보기
        System.out.println("duplicateUserEmail==="+duplicateUserEmail);
        if(duplicateUserID > 0 ){
            bindingResult.rejectValue("userID","duplicateID","사용할 수 없는 ID입니다.");
            return "member/signup";
        }
        if(duplicateUserEmail > 0){
            bindingResult.rejectValue("userEmail","duplicateEmail","사용할 수 없는 Email입니다.");
            return "member/signup";
        }
        if(!memberDto.getUserPW().equals(memberDto.getUserPWConfirm())){
            bindingResult.rejectValue("userPWConfirm","confirmPassword","패스워드가 일치하지 않습니다.");
            return "member/signup";
        }
        int result = memberDao.signup(memberDto);
        if(result>0) {
            return "redirect:/member/login";
        } else{
            return "member/signup";
        }
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public Map<String, Boolean> idCheck(@RequestBody MemberDto memberDto) {
        int result = memberDao.existsUserId(memberDto.getUserID());
        Map<String, Boolean> map = new HashMap<>();
        map.put("isDuplicate", result > 0);
        return map;
    }

    // 리스트에서 로그인 시 리스트로 가게 수정
    @GetMapping("/login")
    public String loginForm(Model model, HttpSession session, HttpServletRequest request) {
        model.addAttribute("loginDto", new LoginDto());
        String referer = request.getHeader("Referer");
        if (referer != null && !referer.contains("/login")) {
            session.setAttribute("prevPage", referer);
        }
        return "member/login";
    }
    @PostMapping("/login")
    public String loginProcess(@Valid @ModelAttribute LoginDto loginDto,
                               BindingResult bindingResult,
                               HttpSession session,
                               Model model) {

        if(bindingResult.hasErrors()){
            return "member/login";
        }

        MemberDto loggedMemberDto = memberDao.login(loginDto);
        session.setAttribute("loggedMember", loggedMemberDto);

        // 이전 페이지 가져오기
        String prevPage = (String) session.getAttribute("prevPage");
        if(prevPage != null) {
            session.removeAttribute("prevPage"); // 사용 후 제거
            return "redirect:" + prevPage;
        }

        return "redirect:/"; // 이전 페이지 없으면 루트로
    }

    @GetMapping("/info")
    public String info(Model model,
                       HttpSession session) {
        //model.addAttribute("loginDto", new LoginDto());
        // 서로 다른 컨트롤러에서 board 데이터를 가져오는 방법???

//        List<BoardDto> boardList = boardDao.findAll(pageDto);
        // 로그인 시 로그인 userid 정보를 취득하기 위한 코드
        MemberDto memberDto = (MemberDto) session.getAttribute("loggedMember");
        String memberUserID = memberDto.getUserID();
        System.out.println("memberUserID==="+memberUserID);

        List<BoardWithMemberDto> boardUserIDList = boardDao.findAllUserID(memberUserID);
        System.out.println("boardUserIDList==="+boardUserIDList);
        model.addAttribute("boardUserIDList", boardUserIDList);
        return "member/info";
    }
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/delete")
    public String delete() {
        return "member/delete";
    }
    @PostMapping("/delete")
    public String deleteProcess(@RequestParam("userPW") String userPW,
                                HttpSession session,
                                Model model) {
        //session.getAttribute("loggedMember");
        MemberDto memberDto = (MemberDto) session.getAttribute("loggedMember");
        LoginDto loginDto = new  LoginDto();
        loginDto.setUserID(memberDto.getUserID());
        loginDto.setUserPW(userPW);
        int result = memberDao.deleteMember(loginDto);
        System.out.println("result===="+result);

        if(result>0) {
            return "redirect:/";
        }
        model.addAttribute("error", "패스워드 확인해주세요");
        return "member/delete";
    }
}

package com.jjang051.board02.controller;

import com.jjang051.board02.dao.BoardDao;
import com.jjang051.board02.dao.MemberDao;
import com.jjang051.board02.dto.*;
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

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
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
        MemberDto  loggedMemberDto = memberDao.login(loginDto);
        //redirect에서는 유효하지 않다...
        //pageContext, request, (session, application 번역 변수로 인식된다.)
        session.setAttribute("loggedMember", loggedMemberDto);
        //session은 로그아웃 하기 전까지 서버에 값을 가지고 있다.
        return "redirect:/";
    }
    @GetMapping("/info")
    public String info(Model model,
                       HttpSession session,
                       @ModelAttribute("pageDto") PageDto pageDto,
                       @RequestParam(value = "move", required = false, defaultValue = "0") int move) {

        MemberDto memberDto = (MemberDto) session.getAttribute("loggedMember");
        if (memberDto == null) {
            return "redirect:/member/login";
        }

        String memberUserID = memberDto.getUserID();

        // ✅ 기본 페이지 및 크기 설정
        int currentPage = pageDto.getPage() + move;
        if (currentPage < 1) currentPage = 1;
        int size = (pageDto.getSize() == 0) ? 10 : pageDto.getSize();

        // ✅ 전체 게시글 개수 가져오기 (총 페이지 계산)
        int totalBoard = boardDao.countUserPosts(memberUserID);
        int totalPages = (int) Math.ceil((double) totalBoard / size);
        if (currentPage > totalPages && totalPages > 0) currentPage = totalPages;

        pageDto.setPage(currentPage);
        pageDto.setSize(size);

        // ✅ 쿼리에 전달할 맵 구성
        Map<String, String> map = new HashMap<>();

        int offset = pageDto.getOffset();


        map.put("userID", memberUserID);
        map.put("offset", String.valueOf(offset));
        map.put("size", String.valueOf(pageDto.getSize()));

        // ✅ 해당 페이지 게시글 조회
        List<BoardWithMemberDto> boardUserIDList = boardDao.findAllUserID(map);

        // ✅ 모델에 담기
        model.addAttribute("boardUserIDList", boardUserIDList);
        model.addAttribute("pageDto", pageDto);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);

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

package com.jjang051.board02.controller;

import com.jjang051.board02.dao.BoardDao;
import com.jjang051.board02.dto.BoardDto;
import com.jjang051.board02.dto.MemberDto;
import com.jjang051.board02.dto.PageDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardDao boardDao;

    @GetMapping("/list")
    public String list(Model model,
                       @ModelAttribute("pageDto")  PageDto pageDto
                       )
    {
        int page =  pageDto.getPage();
        int size =  pageDto.getSize();
        int totalBoard =  boardDao.totalBoard(pageDto); //전체 게시물 수  33 /10
        int totalPages =  (int)Math.ceil((double)totalBoard/size);
        if(totalBoard==0) {
            model.addAttribute("boardList",List.of());
            PageDto responsePageDto = PageDto.builder()
                    .page(page)
                    .size(size)
                    .keyword(pageDto.getKeyword())
                    .type(pageDto.getType())
                    .total(totalBoard)
                    .totalPages(1)
                    .hasPrev(false)
                    .hasNext(false)
                    .build();
            model.addAttribute("responsePageDto",responsePageDto);
            return "/board/list";
        }
        if(page < 1) {
            page = 1;
            return "redirect:/board/list?page="+page+"&size="+size;
        }  //0보다 작아지지 않게....
        if(page > totalPages) {
            page = totalPages;
            return "redirect:/board/list?page="+page+"&size="+size;
        } // 마지막 보다 커지지 않게...
        System.out.println("pageDto==="+pageDto);
        List<BoardDto> boardList = boardDao.findAll(pageDto);
        System.out.println("페이지 = "+boardList.size());
        model.addAttribute("boardList", boardList);
        PageDto responsePageDto = PageDto.builder()
                .page(page)
                .size(size)
                .keyword(pageDto.getKeyword())
                .type(pageDto.getType())
                .total(totalBoard)
                .totalPages(totalPages)
                .hasPrev(page>1)
                .hasNext(page<totalPages)
                .build();

        model.addAttribute("responsePageDto",responsePageDto);
        return "board/list";
    }

    @GetMapping("/write")
    public String write(Model model, HttpSession session) {
        //로그인한 사용자면 이름을 넣어서 넘겨주고 아니면 빈 dto내려보내기
        MemberDto loggedMember = (MemberDto)session.getAttribute("loggedMember");
        BoardDto boardDto = new BoardDto();
        if(loggedMember!=null){
            boardDto.setWriter(loggedMember.getUserName());
        }
        model.addAttribute("boardDto", boardDto);
        return "board/write";
    }
    @PostMapping("/write")
    public String writeProcess(@Valid BoardDto boardDto,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession session,
                               @RequestParam String secretPW) {
        if (bindingResult.hasErrors()) {
            return "board/write";
        }

        // 체크박스 처리: 체크 안 했으면 공개글
        if (boardDto.getSecretValue() == null) {
            boardDto.setSecretValue("N");
        }

        // 로그인 사용자 이름과 작성자 이름 동기화
        MemberDto loginUser = (MemberDto) session.getAttribute("loggedMember");
        if (loginUser != null) {
            boardDto.setWriter(loginUser.getUserName());
        }

        // 비밀글일 경우 비밀번호가 없으면 생성/저장
        if ("Y".equals(boardDto.getSecretValue())) {
            boardDto.setSecretPW(secretPW); // 사용자가 입력한 비밀번호로 저장
        }

        int result = boardDao.writeBoard(boardDto);
        if(result > 0) {
            return "redirect:/board/list";
        }

        return "board/write";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable("id") int id,
                         @RequestParam(value = "pw", required = false) String pw,
                         Model model,
                         HttpSession session) {
        BoardDto boardDto = boardDao.findById(id);
        BoardDto prevBoardDto = boardDao.findPrev(id);
        BoardDto nextBoardDto = boardDao.findNext(id);

        MemberDto loginUser = (MemberDto) session.getAttribute("loggedMember");

        // 비밀글 접근 제한
        if ("Y".equals(boardDto.getSecretValue())) {
            boolean isWriter = loginUser != null && loginUser.getUserID().equals(boardDto.getWriter());
            boolean pwCorrect = pw != null && pw.equals(boardDto.getSecretPW());

            if (!isWriter && !pwCorrect) {
                model.addAttribute("boardDto", boardDto);
                model.addAttribute("error", "비밀글입니다. 비밀번호를 입력해주세요.");
                return "board/secret-check"; // 비밀번호 입력 페이지
            }
        }

        // boardDto와 인접글 DTO 추가
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("prevBoardDto", prevBoardDto);
        model.addAttribute("nextBoardDto", nextBoardDto);
        return "board/detail";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Boolean> delete(@RequestBody BoardDto boardDto) {
        System.out.println("boardDto==="+boardDto);
        int result = boardDao.deleteBoard(boardDto);
        Map<String, Boolean> map = new HashMap<>();

        if(result > 0) {
            map.put("success", true);
        } else {
            map.put("success", false);
        }
        return map;
    }
    @GetMapping("/search")
    public String search(
            @RequestParam(value = "keyword",defaultValue = "") String keyword,
            @RequestParam(value = "type",defaultValue = "title") String type,
            Model model) {
        System.out.println("keyword==="+keyword);
        List<BoardDto> searchList = boardDao.search(keyword, type);
        model.addAttribute("searchList", searchList);
        return "board/search-list";
    }
    // edit를 page확인을 위한 임시 controller
    @GetMapping("/edit")
    public String edit(Model model, HttpSession session) {
        MemberDto loggedMember = (MemberDto)session.getAttribute("loggedMember");
        BoardDto boardDto = new BoardDto();
        if(loggedMember!=null){
            boardDto.setWriter(loggedMember.getUserName());
        }
        model.addAttribute("boardDto", boardDto);
        return "board/edit";
    }
    @PostMapping("/edit")
    public String editProcess() {
        return "board/edit";
    }
}
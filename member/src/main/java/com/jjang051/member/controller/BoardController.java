package com.jjang051.member.controller;

import com.jjang051.member.dto.BoardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {
    List<BoardDto> boardDtoList = new ArrayList<>();
    @GetMapping("/board/detail/{idx}")
    public String detail(@PathVariable("idx") int idx, Model model){
        //값을 뿌려보기
        //idx,title,regdate, hit, writer를 속성으로 하는 dto 만들어서
        //해당하는 번호의 boardDto를 detail에 출력해보기...
        //db가 없으므로 List를 이용하기
        boardDtoList.add(new BoardDto(
                1,
                "1번 게시글",
                LocalDateTime.of(2025,10,13,14,6,0),
                1,
                "장성호"
        ));
        BoardDto boardDto02 = BoardDto.builder()
                .idx(2)
                .writer("정형돈")
                .title("졸리는 시간입니다.")
                .hit(5)
                .regdate(LocalDateTime.of(2025,10,13,14,6,0))
                .build();
        BoardDto boardDto03 = BoardDto.builder()
                .idx(3)
                .writer("유재석")
                .title("자다가 걸리면 때립니다.")
                .hit(10)
                .regdate(LocalDateTime.of(2025,10,12,14,6,0))
                .build();
        boardDtoList.add(boardDto02);
        boardDtoList.add(boardDto03);
        BoardDto findedBoardDto = boardDtoList.get(idx - 1);
        model.addAttribute("findedBoardDto",findedBoardDto);
        return "detail";
    }
}

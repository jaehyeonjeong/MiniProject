package com.jjang051.board02.dao;

import com.jjang051.board02.dto.BoardDto;
import com.jjang051.board02.dto.BoardWithMemberDto;
import com.jjang051.board02.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDao {
    int writeBoard(BoardDto boardDto);
    List<BoardDto> findAll(PageDto pageDto);

    BoardDto findById(int id);

    int deleteBoard(BoardDto boardDto);
    int totalBoard(PageDto pageDto);
    List<BoardDto> search(@Param("keyword") String keyword,
                          @Param("type") String type);

    BoardDto findPrev(int id);
    BoardDto findNext(int id);

    // 추가
    int updateBoard(BoardDto boardDto);

    // 비밀글 비번 조회(xml에 있음)
    String secretPW(BoardDto boardDto);

    // mapper로 join한 board리스트 함수 생성
    List<BoardWithMemberDto> findAllUserID(Map<String, String> map);

    int countUserPosts(String memberUserID);
}

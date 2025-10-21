package com.jjang051.board02.dao;

import com.jjang051.board02.dto.LoginDto;
import com.jjang051.board02.dto.MemberDto;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int signup(MemberDto memberDto);
    int idCheck(MemberDto memberDto);
    int existsUserId(String userID);
    int existsEmail(String userEmail);
    MemberDto login(LoginDto loginDto);
    int deleteMember(LoginDto loginDto);
}

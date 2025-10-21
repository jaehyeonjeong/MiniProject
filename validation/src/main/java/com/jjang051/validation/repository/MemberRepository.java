package com.jjang051.validation.repository;

import com.jjang051.validation.dto.LoginDto;
import com.jjang051.validation.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    //생성자 주입방식
    private final JdbcTemplate jdbcTemplate;

    public MemberDto findById(LoginDto loginDto){
        String sql = "select * from member where userid = ? and userpw = ?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(MemberDto.class),
                    loginDto.getUserID(),
                    loginDto.getUserPW());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

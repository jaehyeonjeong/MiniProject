package com.jjang051.db02.repository;

import com.jjang051.db02.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    //세터 주입 방식,필드 주입 장식
    //@Autowired
    private final JdbcTemplate jdbcTemplate;

    //생성자 주입방식
    /*
    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
     */
    public List<Member> findAll(){
        String sql = "select * from member order by id desc";
        //여러 건 조회
        List<Member> memberList =
                jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Member.class));
        return memberList;
    }
    public Member findById(int id){
        String sql = "select * from member where id=?";
        //단건 조회
        Member findedMember = jdbcTemplate.queryForObject
                (sql,new BeanPropertyRowMapper<>(Member.class),id);
        return findedMember;
    }
    //int
    public int save(Member member) {
        //PrepareStatement
        String sql = "insert into  member (id,userID,userName,userEmail) values (member_seq.nextval,?,?,?)";
        return jdbcTemplate.update(sql,member.getUserID(), member.getUserName(), member.getUserEmail());
    }
    public int update(Member member) {
        System.out.println(member);
        String sql = "update member set userName=?,userEmail=? where id=?";
        return jdbcTemplate.update(sql,member.getUserName(),member.getUserEmail(),member.getId());
    }
    public int delete(Member member) {
        String sql = "delete from member where userID=? and userPW = ?";
        return jdbcTemplate.update(sql,member.getUserID(),member.getUserPW());
    }
}

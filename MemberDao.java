package com.project.blog_project.dao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
    @Autowired
    JdbcTemplate jt;
    public void insert(
        String memberId,
        String memberPw,
        String memberNm,
        String memberEmail
    ){
        String sqlStmt = "insert into member(member_id,member_pw,member_nm,member_email) values(?,?,?,?)";
        jt.update(sqlStmt,memberId,memberPw,memberNm,memberEmail);
    }
    public int dupIdCheck(String memberId){
        String sqlStmt = "select count(*) from member where member_id = ?";
        return jt.queryForObject(sqlStmt,Integer.class,memberId);
    }
    public List<Map<String,Object>> memberSelect(){
        String sqlStmt = "select member_id, member_nm, reg_dt,member_email ,member_level, del_fg from member where member_level != 2";
        return jt.queryForList(sqlStmt);
    }
    public void update(String memberId){
        String sqlStmt = "update member set member_level = 1 where member_id = ?";
        jt.update(sqlStmt,memberId);
    }
    public void delete(String memberId){
        String sqlStmt = "update member set del_fg = 1 where member_id = ?";
        jt.update(sqlStmt,memberId);
    }
    public List<Map<String,Object>> checkMember(String memberId, String memberPw){
        String condition = "where member_id = '"+memberId+"' and member_pw = '"+memberPw+"'";
        String sqlStmt = "select member_id,member_nm, member_level from member "+condition;
        return jt.queryForList(sqlStmt); 
    }
    public String getMemberLevel(String memberId, String memberPw){
        String sqlStmt = "select member_level from member where member_id = ? and member_pw = ?";
        return jt.queryForObject(sqlStmt,String.class,memberId,memberPw);
    }

}

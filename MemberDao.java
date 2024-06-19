package com.project.blog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemberDao {
    @Autowired
    JdbcTemplate jt;
    public List<Map<String,Object>> selectMemberList(){
        // memberList메소드가 원하는 쿼리문
        String sqlStmt = "select  seq, id, pw, name, ";
               sqlStmt += "birth_date as birthDate, email, grade, ";
               sqlStmt += "reg_dt as regDt, del_fg as delFg ";
               sqlStmt += "from tb_member_mst";
        return jt.queryForList(sqlStmt);
    }
    public void insertMemberRegisterAction(String id,
                                           String pw,
                                           String name,
                                           String birthDate,
                                           String email){
        String sqlStmt = "insert into tb_member_mst( ";
               sqlStmt += "id,pw,name,birth_date,email) values(?,?,?,?,?)";
               jt.update(sqlStmt,id,pw,name,birthDate,email);

    }
    public int depIdCheck(String id){
        String sqlStmt = "select count(*) from tb_member_mst where id = ?";
        return jt.queryForObject(sqlStmt,Integer.class,id); //integer타입으로 가져오기 위해
    }

}

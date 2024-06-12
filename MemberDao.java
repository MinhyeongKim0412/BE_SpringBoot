package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    @Autowired
    private JdbcTemplate jt;

    public int checkMember(String userId, String userPw) {
        String sql = "SELECT COUNT(*) FROM member WHERE user_id = ? AND user_pw = ?"; // 수정: user_pw 조건 추가
        return jt.queryForObject(sql, Integer.class, userId, userPw);
    }
}

package com.project.blog_project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class TestDao {
    @Autowired
    JdbcTemplate jt;
    public List<Map<String, Object>> select(){
        String sqlStmt = "select * from blog";
        return jt.queryForList(sqlStmt);
    }
}

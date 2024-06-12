package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UploadFileDao {
    @Autowired
    JdbcTemplate jt;
    public void insert(String writer,
                     String desc,
                     String originalFileName, 
                     String filePath, 
                     String fileName) {
        String sqlStmt = "insert into upload_file(writer,descript,origin_file_name, file_location, file_name) values(?,?,?,?,?)";
        jt.update(sqlStmt, writer,desc,originalFileName, filePath, fileName);
    }
    public List<Map<String, Object>> select() {
        String sqlStmt = "select seq,writer,descript,reg_dt,origin_file_name,file_location,file_name from upload_file";
        return jt.queryForList(sqlStmt);
    }
}

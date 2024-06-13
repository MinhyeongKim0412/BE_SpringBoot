package com.project.blog_project.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.project.blog_project.dao.TestDao;

@Controller
public class HtmlController {
    @Autowired
    TestDao testDao;
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        List<Map<String, Object>> resultSet = testDao.select();
        int result = resultSet.size(); 
        return result+"건";
    }
}

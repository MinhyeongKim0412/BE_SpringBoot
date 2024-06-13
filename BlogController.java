package com.project.blog_project.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("blog/insert")
    public String blogInsert(){
        return "blog/insert";
    }
}

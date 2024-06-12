package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.MemberDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {

    @Autowired
    private MemberDao memberDao;

    @GetMapping("/")
    public String blogList() {
        return "html/blog_list"; // 수정: 상대 경로로 변경
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String userPw,
                        HttpSession session,
                        Model model) {
        int cnt = memberDao.checkMember(userId, userPw); // 수정: 인스턴스 메서드 호출
        if (cnt > 0) {
            model.addAttribute("userId", userId);
            session.setAttribute("member", userId); // 수정: userId를 session에 저장
            return "html/blog_list"; // 수정: 상대 경로로 변경
        } else {
            model.addAttribute("message", "사용자 이름 또는 비밀번호를 재확인해주세요.");
            return "login";
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session) {
        session.invalidate();
        return "로그아웃 완료.";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}

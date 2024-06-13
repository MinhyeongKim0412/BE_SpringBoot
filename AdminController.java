package com.project.blog_project.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog_project.dao.MemberDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.*;

@Controller
public class AdminController {
    @Autowired
    MemberDao memberDao;
    @GetMapping("admin/member")
    public String memberList(Model model, HttpSession session, HttpServletRequest request){
        if(session.getAttribute("memberLevel") == null){
            session.setAttribute("memberLevel", "-1");
        }

        if(session.getAttribute("memberLevel").equals("2")){
            List<Map<String,Object>> resultSet = memberDao.memberSelect();
            model.addAttribute("resultSet", resultSet);
            return "admin/member_list";
        }else {
            return "redirect:/member/login";
        }
  
    }
    @GetMapping("admin/member/update")
    public String memberUpdate(@RequestParam String memberId){
        memberDao.update(memberId);
        return "redirect:/admin/member";
    }
    @GetMapping("admin/member/delete")
    public String memberDelete(@RequestParam String memberId){
        memberDao.delete(memberId);
        return "redirect:/admin/member"; 
        //delete from member where member_id = ? 1, update member set del_fg = 1 where member_id = ?
    }
}

package com.project.blog_project.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.blog_project.dao.MemberDao;
import java.util.*;
import jakarta.servlet.http.HttpSession;
import com.project.blog_project.model.Member;

@Controller
public class MemberController {
    @Autowired
    MemberDao memberDao;
    @GetMapping("member/insert") // member가입 페이지로 이동
    public String memberRegisterForm(){
        return "member/member_register";
    }
    @PostMapping("member/insert/action") // member 가입페이지에서 보내는 값을 처리
    @ResponseBody
    public String memberRegisterAction(
        @RequestParam String memberId,
        @RequestParam String memberPw,
        @RequestParam String memberNm,
        @RequestParam String memberEmail
    ){
        int dup = memberDao.dupIdCheck(memberId);
        if(dup > 0){
            String result = "회원 아이디가 중복되었습니다<br>";
            result += "회원가입으로 이동하여 주시기 바랍니다<br>";
            result += "<a href='/member/insert'>회원가입</a>";
            return result;
        } else {
            memberDao.insert(memberId,memberPw,memberNm,memberEmail); // id 중복체크 이후 실행
            return "회원가입이 완료되었습니다 <a href='/member/login'>로그인 페이지로 이동바랍니다</a>";
        }
        
    }
    @GetMapping("member/login")
    public String loginGet(){
        return "member/login";
    }
    @PostMapping("member/login")
    public String loginPost(@RequestParam String memberId, 
                            @RequestParam String memberPw,
                            Member member,
                            HttpSession session){
        
        List<Map<String,Object>> resultSet = memberDao.checkMember(memberId,memberPw);
        String memberLevel = memberDao.getMemberLevel(memberId,memberPw);
        if( resultSet.size() > 0 ){
            session.setAttribute("member",resultSet.get(0));
            session.setAttribute("memberLevel", memberLevel); 
            return "redirect:/admin/member";
        }else {
            return "redirect:/member/login";
        }
    }
    @GetMapping("member/logout")
    @ResponseBody
    public String logout(HttpSession session){
        session.invalidate();
        return "logout 되었습니다";
    }
}

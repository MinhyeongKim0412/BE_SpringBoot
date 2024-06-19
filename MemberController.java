package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.dao.MemberDao;
import java.util.*;

@Controller
public class MemberController {
    @Autowired
    MemberDao memberDao;
    @GetMapping("member")
    public String memberList(Model model){
        //1. database 가져오는 데이터 --> tb_member_mst가져오는 쿼리 
        //2. Data Access Object로 만들어 줘야 됨 --> dao를 객체를 만들어 1번에서 생각한 쿼리를 실행
        //3. dao로 부터 가져오도록 dao메소드를 만들어 줘야 함 --> 메소드에 쿼리를 작성 
        //4. 메소드를 실행시켜 프로그램에서 원하는 형태로 담아야 함 --> 메소드에서 리턴하는 데이터 타입으로 생성
        //5. 모델에 값을 담기 --> model.addAttribute()

       //   seq bigint primary key auto_increment,
       //  id varchar(20) not null,
       //  pw varchar(17) not null,
        //  name varchar(20) not null,
        //  birth_date varchar(9) not null,
        //  email varchar(30) not null,
        //  grade int not null default 0,
        //  reg_dt datetime default current_timestamp,
        //  del_fg varchar(2) default 0
        // select seq, id, pw, name, birth_date, email, grade,reg_dt, del_fg from tb_member_mst;

        // dao폴더 작성, MemberDao.java파일 작성
        // import com.project.blog.dao.MemberDao;
        
        // MemberDao에 가져오는 메소드생성 -- dao.MemberDao.java파일에서  selectMemberList 메소드를 작성 
        // 메소드 실행
        List<Map<String,Object>> resultSet = memberDao.selectMemberList();

        //모델에 값을 담기 --> model.addAttribute()
        
        model.addAttribute("resultSet", resultSet);

        return "member/list";
    }
    @GetMapping("member/register")
    public String memberRegisterForm(){
        return "member/register";
    }
    @PostMapping("member/register/action")
    public String memberRegisterAction(@RequestParam String id,
                                       @RequestParam String pw,
                                       @RequestParam String name,
                                       @RequestParam String year,
                                       @RequestParam String month,
                                       @RequestParam String day,
                                       @RequestParam String email1,
                                       @RequestParam String email2){
        if(month.length()<2){
            month = '0'+month;
        }
        if(day.length()<2){
            day = '0'+day;
        }
        String birthDate = year+month+day;
        String email = email1+"@"+email2;
        memberDao.insertMemberRegisterAction(id,pw,name,birthDate,email);
        return "redirect:/member";
    }
    @GetMapping("/member/dupcheck")
    public String memberDupCheck(@RequestParam String id, Model model){
        int resultSet = memberDao.depIdCheck(id);
        if(resultSet >0 ){
            model.addAttribute("result", id+"는  사용불가능한 아이디입니다");
            model.addAttribute("fg", "1");
        }else{
            model.addAttribute("result", id+"는 사용가능한 아이디 입니다");
            model.addAttribute("fg","0");
        }
        model.addAttribute("id", id);
        return "member/dupcheck";
    }
}

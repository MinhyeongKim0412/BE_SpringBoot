package com.example.basic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.basic.model.User; //모델 package에서 호출



@Controller
public class HtmlController {
    @GetMapping("/") //접속 url 
    public String html(){
        return "html/string"; // resource/templates/html/string.html
    }
    @GetMapping("html/void") // 접속 url - resource/templates/html/void.html
    public void htmlVoid(){
    }
    @GetMapping("html/map") // 접속 url - resource/templates/html/map.html
    public Map<String, Object> htmlMap(Map<String,Object> map){
        Map<String, Object> map2 = new HashMap<String, Object>();
        return map2;

    }
    @GetMapping("html/model")  // 접속 url - resource/templates/html/model.html
    public Model htmlModel(Model model){
        return model;
    }
    @GetMapping("html/model_and_view") // 접속 url 
    public ModelAndView htmlModelAndView(){
        ModelAndView mav2 = new ModelAndView();
        mav2.setViewName("html/model_and_view"); // - resource/templates/html/model_and_view.html
        return mav2;
    }
   
    @GetMapping("html/object")  // 접속 url - resource/templates/html/object.html
    public User htmlObject(){ // Object는 User.java - com.example.basic.model 위치에서 작성
        User user = new User();
        user.setUserName("kangwooklee");
        user.setAge(45);
        user.setHobby("nothing");
        return user;
    }

}

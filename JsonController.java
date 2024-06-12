package com.example.basic.controller;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.basic.model.Member;

@Controller
public class JsonController {
    @GetMapping("json/string")
    @ResponseBody
    public String json(){
        String html = """
            <html>
            <h1> json String </h1>
            <hr>
            <h2>test</h2>
            </html>
                """;
        return html;
    }
    @GetMapping("json/map")
    @ResponseBody
    public Map<String,Object> jsonMap(Map<String,Object> map){
        Map<String, Object> map2 = new HashMap<String,Object>();
        map2.put("key1","value");
        map2.put("key2",2324);
        map2.put("key3",true);
        System.out.println(map2.toString());
        return map2; 
    }
    @GetMapping("json/object")
    @ResponseBody
    public Member jsonObject(){
        Member member = new Member();
        member.setName("kangwook");
        member.setUserId("allomyrin");
        member.setPassWord("1234");
        return member;
    }
    @GetMapping("json/list")
    @ResponseBody
    public List<Integer> jsonList(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<15;i++){
            list.add(i);
        }
        return list;
    }
    @GetMapping("json/exam")
    @ResponseBody
    public Map<String, Object> jsonExam(Map<String,Object> map){
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<>();
        map2.put("name","가");
        map2.put("userId","A");
        map2.put("passWord","1");
        map3.put("name","나");
        map3.put("userId","B");
        map3.put("passWord","2");
        list.add(map2);
        list.add(map3);
        map1.put("count",2);
        map1.put("list",list);
        return map1;
    }

}
